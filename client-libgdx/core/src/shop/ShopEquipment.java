package shop;

import CLib.mGraphics;
import Equipment.Equip;
import coreLG.CCanvas;
import coreLG.TerrainMidlet;
import java.util.Vector;
import model.CRes;
import model.Font;
import model.IAction;
import model.Language;
import model.PlayerInfo;
import model.Position;
import network.Command;
import network.GameService;
import screen.CScreen;
import screen.GameScr;
import screen.TabScreen;

public class ShopEquipment extends TabScreen {
   int select;
   private Vector items = new Vector();
   private int W;
   private int nLine;
   private int hLine;
   private int wTab;
   private int wXp;
   private int wYp;
   private int cmtoYI;
   private int cmyI;
   private int cmdyI;
   private int cmvyI;
   private int cmyILim;
   private int cmtoYID;
   private int cmyID;
   private int cmdyID;
   private int cmvyID;
   private int cmyIDLim;
   Vector myShop;
   Position transText1;
   Position transText2;
   Equip eSelect;
   public String equipDetail;
   public String equipName;
   public String price;
   int size;
   public boolean expandDetail;
   private int xExpand;
   private int yExpand;
   public static int pa = 0;
   public static int paID = 0;
   public static boolean trans = false;
   int speed;

   public ShopEquipment() {
      this.W = CCanvas.width;
      this.nLine = 4;
      this.wTab = 40;
      this.wXp = 9;
      this.wYp = 5;
      this.myShop = new Vector();
      this.transText1 = new Position(0, 1);
      this.transText2 = new Position(0, 1);
      this.equipDetail = "";
      this.equipName = "";
      this.price = "";
      this.size = 0;
      this.speed = 1;
      this.nameCScreen = " ShopEquipment screen!";
      this.right = new Command(Language.back(), new IAction() {
         public void perform() {
            ShopEquipment.this.doClose();
         }
      });
      this.hTabScreen = 180;
      this.n = 4;
      this.title = Language.shoptrangbi();
      this.getW();
      this.xPaint = CCanvas.width / 2 - 85;
      this.yPaint = (CCanvas.hieght - CScreen.cmdH) / 2 - 85;
      this.nLine = 4;
      this.wXp = this.wBlank / 4;
      this.wYp = 5;
      this.wTab = 40;
   }

   public void show(CScreen lastScreen) {
      super.show(lastScreen);
      this.cmtoYI = 0;
      this.cmtoYID = 0;
      this.getCommand();
      this.getDetail();
   }

   public void paint(mGraphics g) {
      super.paint(g);
      this.paintEquip(g, this.xPaint, this.yPaint + 29, this.myShop, this.select);
      this.paintDetail(g, this.xPaint, this.yPaint + 103);
      this.paintSuper(g);
   }

   public void update() {
      super.update();
   }

   public void mainLoop() {
      super.mainLoop();
      this.itemCamera();
   }

   private boolean hasSelection() {
      return this.myShop != null && this.select >= 0 && this.select < this.myShop.size()
              && this.myShop.elementAt(this.select) instanceof Equip;
   }

   private void clearSelectionDetail() {
      this.select = 0;
      this.eSelect = null;
      this.equipDetail = "";
      this.equipName = "Không có trang bị";
      this.price = "";
      this.expandDetail = false;
      this.left = null;
      this.cmyIDLim = 0;
   }

   public void getCommand() {
      if (!this.hasSelection()) {
         this.left = null;
         return;
      }
      this.eSelect = (Equip)this.myShop.elementAt(this.select);
      final Command xu = new Command(Language.muaXu(), new IAction() {
         public void perform() {
            final Equip selected = ShopEquipment.this.getCurrEq();
            if (selected == null || selected.xu == -1) {
               return;
            }
            CCanvas.startYesNoDlg(Language.bancochac() + selected.xu + Language.xu(), new IAction() {
               public void perform() {
                  CCanvas.startOKDlg(Language.pleaseWait());
                  GameService.gI().buy_sell_Equip((byte)0, (int[])null, (short)selected.index, (byte)0);
               }
            });
         }
      });
      final Command luong = new Command(Language.muaLuong(), new IAction() {
         public void perform() {
            final Equip selected = ShopEquipment.this.getCurrEq();
            if (selected == null || selected.luong == -1) {
               return;
            }
            CCanvas.startYesNoDlg(Language.bancochac() + selected.luong + Language.luong(), new IAction() {
               public void perform() {
                  CCanvas.startOKDlg(Language.pleaseWait());
                  GameService.gI().buy_sell_Equip((byte)0, (int[])null, (short)selected.index, (byte)1);
               }
            });
         }
      });
      Command menuLeft = new Command("Menu", new IAction() {
         public void perform() {
            Vector menu = new Vector();
            Equip selected = ShopEquipment.this.getCurrEq();
            if (selected == null) {
               return;
            }
            if (selected.xu != -1) {
               menu.addElement(xu);
            }
            if (selected.luong != -1) {
               menu.addElement(luong);
            }
            if (!menu.isEmpty()) {
               CCanvas.menu.startAt(menu, 0);
            }
         }
      });
      if (this.eSelect.luong != -1 && this.eSelect.xu != -1) {
         this.left = menuLeft;
      } else if (this.eSelect.xu == -1 && this.eSelect.luong != -1) {
         this.left = luong;
      } else if (this.eSelect.xu != -1) {
         this.left = xu;
      } else {
         this.left = null;
      }
   }

   private void itemCamera() {
      if (this.cmyI != this.cmtoYI) {
         this.cmvyI = this.cmtoYI - this.cmyI << 2;
         this.cmdyI += this.cmvyI;
         this.cmyI += this.cmdyI >> 4;
         this.cmdyI &= 15;
      }

      if (this.cmyI > this.cmyILim) {
         this.cmyI = this.cmyILim;
      }

      if (this.cmyI < 0) {
         this.cmyI = 0;
      }

      if (this.cmyID != this.cmtoYID) {
         this.cmvyID = this.cmtoYID - this.cmyID << 2;
         this.cmdyID += this.cmvyID;
         this.cmyID += this.cmdyID >> 4;
         this.cmdyID &= 15;
      }

      if (this.cmyID > this.cmyIDLim) {
         this.cmyID = this.cmyIDLim;
      }

      if (this.cmyID < 0) {
         this.cmyID = 0;
      }

   }

   public void doClose() {
      this.isClose = true;
   }

   public Equip getCurrEq() {
      return this.hasSelection() ? (Equip)this.myShop.elementAt(this.select) : null;
   }

   public void getMyShop() {
      this.myShop.removeAllElements();

      if (this.items == null || TerrainMidlet.myInfo == null) {
         return;
      }
      for(int i = 0; i < this.items.size(); ++i) {
         Object raw = this.items.elementAt(i);
         if (!(raw instanceof Equip)) {
            continue;
         }
         Equip e = (Equip)raw;
         if (e.glass == TerrainMidlet.myInfo.gun) {
            this.myShop.addElement(e);
         }
      }

   }

   public void setItems(Vector item) {
      this.select = 0;
      this.items = item == null ? new Vector() : item;
      this.getMyShop();
      this.size = this.myShop.size();
      this.hLine = this.size / this.nLine;
      if (this.size % this.nLine != 0) {
         ++this.hLine;
      }
      this.cmyILim = Math.max(0, this.hLine * this.wTab - 70);
      if (!this.hasSelection()) {
         this.clearSelectionDetail();
         return;
      }
      this.eSelect = (Equip)this.myShop.elementAt(this.select);
      this.getDetail();
   }

   private void paintEquip(mGraphics g, int X, int Y, Vector it, int select) {
      g.setColor(3832504);
      g.fillRoundRect(this.xPaint, this.yPaint + 23, this.wTabScreen, this.hTabScreen, 6, 6, false);
      int a = 0;
      int b = 0;

      g.setClip(X - 2, Y - 2, 170, 75);
      g.translate(0, -this.cmyI);
      g.setColor(16767817);

      if (it == null) {
         g.setClip(0, 0, 1000, 1000);
         g.translate(0, -g.getTranslateY());
         return;
      }
      for(int i = 0; i < it.size(); ++i) {
         Object raw = it.elementAt(i);
         if (!(raw instanceof Equip)) {
            continue;
         }
         Equip e = (Equip)raw;
         int xIcon = X + a * this.wTab + this.wXp;
         int yIcon = Y + b * this.wTab + this.wYp;
         if (i == select) {
            int xDraw = xIcon - (CCanvas.isTouch ? 12 : 2);
            int yDraw = yIcon - (CCanvas.isTouch ? 12 : 2);
            int width = CCanvas.isTouch ? 40 : 20;
            int height = CCanvas.isTouch ? 40 : 20;
            g.fillRect(xDraw, yDraw, width, height, true);
         }

         if (e.isSelect) {
            g.setColor(5612786);
            g.fillRect(xIcon, yIcon, 16, 16, true);
         }

         e.drawIcon(g, xIcon, yIcon, true);
         ++a;
         if (a == this.nLine) {
            a = 0;
            ++b;
         }
      }

      g.setClip(0, 0, 1000, 1000);
      g.translate(0, -g.getTranslateY());
   }

   public void paintDetail(mGraphics g, int X, int Y) {
      PlayerInfo m = TerrainMidlet.myInfo;
      String myMoney = Language.money() + ": "
              + (m == null ? "0" : String.valueOf(m.xu)) + Language.xu() + " - "
              + (m == null ? "0" : String.valueOf(m.luong)) + Language.luong();

      if (this.equipDetail == null) this.equipDetail = "";
      if (this.equipName == null) this.equipName = "";
      if (this.price == null) this.price = "";
      int bb = Font.normalFont.getWidth(this.equipDetail);
      int ee = Font.normalFont.getWidth(this.price);
      int cc = this.transText1.x;
      int dd = this.transText2.x;
      Font.normalFont.drawString(g, myMoney, this.W / 2, Y - 1, 3);
      g.setColor(2378093);
      g.fillRoundRect(X, Y + 14, 170, 16, 6, 6, false);
      g.fillRoundRect(X, Y + 34, 170, 16, 6, 6, false);
      g.fillRoundRect(X, Y + 54, 170, 16, 6, 6, false);
      Font.normalGFont.drawString(g, this.equipName, X + 6, Y + 15, 0);
      Font.normalYFont.drawString(g, this.price, X + 6 + dd, Y + 35, 0);
      if (this.eSelect != null && this.eSelect.shopDetailNunStrs != null && !this.eSelect.shopDetailNunStrs.isEmpty()) {
         this.xExpand = X + 6 + cc + 100 + 50;
         this.yExpand = Y + 55;
         if (this.expandDetail) {
            g.setClip(X, Y + 54, 170, 32);
            g.setColor(2378093);
            g.fillRoundRect(X, Y + 54, 170, 32, 6, 6, false);
            g.translate(0, -this.cmyID);
            int index = 0;

            for(int i = 0; i < this.eSelect.shopDetailNunStrs.size(); ++i) {
               if (this.eSelect.shopDetailNunStrs.elementAt(i) != null && !((String)this.eSelect.shopDetailNunStrs.elementAt(i)).equals("")) {
                  Font.normalYFont.drawString(g, (String)this.eSelect.shopDetailNunStrs.elementAt(i), X + 6 + cc, Y + 55 + ITEM_HEIGHT * index++, 0, true);
               }
            }

            g.translate(0, -g.getTranslateY());
         } else if (this.eSelect.shopDetailNunStrs.elementAt(0) != null) {
            Font.normalYFont.drawString(g, (String)this.eSelect.shopDetailNunStrs.elementAt(0), X + 6 + cc, Y + 55, 0);
         }

         if (this.eSelect.shopDetailNunStrs.size() > 1) {
            g.drawImage(GameScr.imgArrowRed, this.xExpand, this.yExpand, 0, false);
         }

         g.setClip(0, 0, 10000, 10000);
         g.translate(0, -g.getTranslateY());
      }
   }

   public void getDetail() {
      if (!this.hasSelection()) {
         this.clearSelectionDetail();
         return;
      }
      this.eSelect = (Equip)this.myShop.elementAt(this.select);
      try {
         this.equipDetail = this.eSelect.getStrShopDetail();
      } catch (RuntimeException detailError) {
         CRes.out("[SHOP-EQUIP] Bad detail for glass=" + this.eSelect.glass
                 + " type=" + this.eSelect.type + " id=" + this.eSelect.id + ": " + detailError);
         this.equipDetail = "";
         this.eSelect.shopDetailNunStrs = new Vector();
         this.eSelect.shopDetailNunmLines = 0;
      }
      if (this.equipDetail == null) {
         this.equipDetail = "";
      }
      String safeName = this.eSelect.name == null || this.eSelect.name.trim().isEmpty()
              ? "Trang bị #" + this.eSelect.id : this.eSelect.name;
      this.equipName = safeName + " (lvl " + this.eSelect.level + ")";
      String luong = (this.eSelect.xu != -1 ? "-" : "") + this.eSelect.luong + Language.luong();
      if (this.eSelect.luong == -1) {
         luong = "";
      }
      String xu = this.eSelect.xu + Language.xu();
      if (this.eSelect.xu == -1) {
         xu = "";
      }
      String ngay = this.eSelect.date >= 0 ? " (" + this.eSelect.date + Language.ngay() + ")" : "";
      this.price = Language.price() + ": " + xu + luong + ngay;
      this.cmyIDLim = this.eSelect.shopDetailNunStrs == null
              ? 0 : Math.max(0, this.eSelect.shopDetailNunStrs.size() * ITEM_HEIGHT - 32);
      this.getCommand();
      this.transText1.x = 0;
   }

   public void onPointerPressed(int x, int y2, int index) {
      super.onPointerPressed(x, y2, index);
   }

   public void onPointerReleased(int xRealse, int yRealse, int index) {
      super.onPointerReleased(xRealse, yRealse, index);
      trans = false;
      if (CCanvas.isPointer(this.xExpand, this.yExpand, 150, 150, index)) {
         this.expandDetail = !this.expandDetail;
         if (this.expandDetail) {
            this.hTabScreen = 200;
         } else {
            this.hTabScreen = 180;
         }

      } else {
         if (CCanvas.isPointer(this.xPaint, this.yPaint, 170, 120, index)) {
            int aa = (this.cmtoYI + yRealse - this.yPaint - 20) / this.wTab * this.nLine + (xRealse - this.xPaint - 8) / this.wTab;
            CRes.out("====>collum " + aa / 4);
            CRes.out("====>row " + (xRealse - this.xPaint - 8) / this.wTab);
            if (aa == this.select && this.left != null && CCanvas.isDoubleClick) {
               this.left.action.perform();
            }

            if (aa >= 0 && aa < this.myShop.size()) {
               this.select = aa;
               this.getDetail();
            }
         }

      }
   }

   public void onPointerDragged(int xDrag, int yDrag, int index) {
      super.onPointerDragged(xDrag, yDrag, index);
      if (!trans) {
         pa = this.cmyI;
         paID = this.cmyID;
         trans = true;
      }

      this.speed = 1;
      if (CCanvas.isPointer(this.W / 2 - 85, this.yPaint + 29, 170, 78, index)) {
         this.cmtoYI = pa + (CCanvas.pyFirst[index] - yDrag) * this.speed;
         if (this.cmtoYI < 0) {
            this.cmtoYI = 0;
         }

         if (this.cmtoYI > this.cmyILim) {
            this.cmtoYI = this.cmyILim;
         }

         CRes.out("cmtoYI = " + this.cmtoYI);
      }

      if (CCanvas.isPointer(this.W / 2 - 85, this.yPaint + 103, 170, 78, index)) {
         this.cmtoYID = paID + (CCanvas.pyFirst[index] - yDrag) * this.speed;
         if (this.cmtoYID < 0) {
            this.cmtoYID = 0;
         }

         if (this.cmtoYID > this.hTabScreen * 40 - 40) {
            this.cmtoYID = this.hTabScreen * 40 - 40;
         }
      }

   }
}
