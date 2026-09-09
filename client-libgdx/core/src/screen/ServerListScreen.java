package screen;

import CLib.mGraphics;
import com.badlogic.gdx.Gdx;
import com.teamobi.mobiarmy2.GameMidlet;
import coreLG.CCanvas;
import coreLG.HybridConfig;
import model.CRes;
import model.Font;
import model.GetString;
import model.IAction;
import model.Language;
import network.Command;
import network.GameService;
import network.Session_ME;

public class ServerListScreen extends CScreen {
   public static String[] nameServer;
   public static String[] address;
   public static boolean[] newServer;
   public static short[] port;
   int selected;
   int yPaint = 0;

   public ServerListScreen() {
      this.indexScreen = 1;
      this.nameCScreen = " ServerListScreen screen!";
      this.center = new Command(Language.select(), new IAction() {
         public void perform() {
            String name = ServerListScreen.nameServer[ServerListScreen.this.selected] + ":" + ServerListScreen.address[ServerListScreen.this.selected] + ":" + ServerListScreen.port[ServerListScreen.this.selected];
            ServerListScreen.this.OnConnectToServer(name);
            CCanvas.loginScr = new LoginScr();
            CCanvas.loginScr.show();
            Session_ME.gI().start = false;
         }
      });
      this.left = new Command(Language.update(), new IAction() {
         public void perform() {
            ServerListScreen.nameServer = null;
            GameMidlet.doUpdateServer();
         }
      });
      this.right = new Command(Language.exit(), new IAction() {
         public void perform() {
            Gdx.app.exit();
            System.exit(-1);
         }
      });
   }

   private void OnConnectToServer(String stringConnect) {
      stringConnect.trim();
      String[] svConfig = stringConnect.split(":");
      String nameSv = svConfig[0].trim().toLowerCase();
      String strIPConnect = svConfig[1];
      String strPortConnect = svConfig[2];
      GameMidlet.IP = strIPConnect;
      GameMidlet.PORT = Short.parseShort(strPortConnect);
      GameMidlet.linkReg = HybridConfig.registrationUrlFor(strIPConnect);
      Session_ME.gI().connect(GameMidlet.IP, GameMidlet.PORT);
      GameMidlet.serverName = svConfig[0];
      if (GameMidlet.isTeamClient) {
         GameService.gI().setProvider(GameMidlet.PROVIDER);
         new GetString();
         GameService.gI().getString("abc");
         GameService.gI().platform_request();
      } else {
         GameMidlet.PROVIDER = (byte)CRes.loadRMSInt("provider");
         GameMidlet.AGENT = CRes.loadRMS_String("agent");
         if (GameMidlet.AGENT == null) {
            GameMidlet.AGENT = "";
         }

         if (GameMidlet.PROVIDER != -1) {
            GameService.gI().setProvider(GameMidlet.PROVIDER);
            GameService.gI().getString(GameMidlet.AGENT);
         }
      }

   }

   public void paint(mGraphics g) {
      g.setColor(7852799);
      g.fillRect(0, 0, w, h, false);
      if (nameServer != null) {
         this.yPaint = CCanvas.hieght / 2 - ITEM_HEIGHT;
         g.setColor(16767817);
         g.fillRect(0, this.yPaint + this.selected * 20 - 3, CCanvas.width, ITEM_HEIGHT, true);
         Font.borderFont.drawString(g, Language.chonmaychu(), CCanvas.width / 2, this.yPaint - ITEM_HEIGHT - 5, 3);

         for(int i = 0; i < nameServer.length; ++i) {
            if (nameServer[i] != null) {
               Font.normalFont.drawString(g, nameServer[i], CCanvas.width / 2, this.yPaint + i * 20, 2);
            }
         }
      }

      super.paint(g);
   }

   public void update() {
      super.update();
      if (nameServer == null || nameServer.length == 0) {
         return;
      }

      // Keyboard navigation must be handled on the normal update tick.
      // Previously this lived in onPointerReleased(), so W/S and arrow keys
      // did nothing until a mouse/touch release happened.
      if (CCanvas.keyPressed[8] || keyDown) {
         CCanvas.keyPressed[8] = false;
         keyDown = false;
         ++this.selected;
         if (this.selected >= nameServer.length) {
            this.selected = 0;
         }
      } else if (CCanvas.keyPressed[2] || keyUp) {
         CCanvas.keyPressed[2] = false;
         keyUp = false;
         --this.selected;
         if (this.selected < 0) {
            this.selected = nameServer.length - 1;
         }
      }

      syncSelectedServer();
   }

   private void syncSelectedServer() {
      // Hybrid server uses the classic 2.3 LOGIN_SUCCESS packet layout.
      // Do NOT force server=2 merely because this desktop source reports 2.4.x:
      // server=2 tells MessageHandler to read an extra UTF username before user_id,
      // while the Hybrid server sends user_id immediately. That misaligns the packet
      // and leaves the client stuck on "Đang đăng nhập".
      GameMidlet.server = (byte)(this.selected >= 3 ? -1 : this.selected);
   }

   public void onPointerPressed(int x, int y2, int index) {
      super.onPointerPressed(x, y2, index);
   }

   public void onPointerReleased(int x, int y2, int index) {
      super.onPointerReleased(x, y2, index);
      if (nameServer != null && nameServer.length > 0 && y2 < CCanvas.hieght - cmdH) {
         int row = (y2 - this.yPaint) / 20;
         if (row == this.selected && CCanvas.isDoubleClick && this.center != null) {
            this.center.action.perform();
         }
         if (row >= 0 && row < nameServer.length) {
            this.selected = row;
         }
      }
      syncSelectedServer();
   }

   public void show() {
      super.show();
      GameMidlet.loadIP();
   }
}
