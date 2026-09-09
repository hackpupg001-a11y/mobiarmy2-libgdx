package network;

import CLib.Image;
import CLib.mSystem;
import com.teamobi.mobiarmy2.GameMidlet;
import coreLG.CCanvas;
import item.Bullet;

import java.io.IOException;
import java.util.Vector;

import map.HoleInfo;
import model.CRes;
import model.UserData;
import screen.GameScr;

public class GameService {
    ISession session;
    protected static GameService instance;

    public void setSession(ISession gi) {
        this.session = gi;
    }

    public static GameService gI() {
        if (instance == null) {
            instance = new GameService();
        }

        return instance;
    }

    public void login(String username, String pass, String version) {
        Message m = new Message(1);

        try {
            m.writer().writeUTF(username);
            m.writer().writeUTF(pass);
            m.writer().writeUTF(version);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var6) {
        }

    }

    public void requestRoomList() {
        // Hybrid server is the classic 2.3 protocol: client cmd 6 asks for the
        // room list. Upstream desktop 2.4 changed this flow to cmd -28, which
        // Hybrid intentionally does not handle and therefore left "Vui lòng chờ"
        // on screen forever.
        Message m = new Message((byte) 6);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void requestBoardList(byte id) {
        Message m = new Message((byte) 7);

        try {
            m.writer().writeByte(id);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
        CRes.out("SendM RequestBoardList BID: " + id);
    }

    public void requestEmptyRoom(byte type, byte level, String id) {
        CRes.out("=========> Hybrid 2.3 room request type = " + type + " lv = " + level + " id = " + id);

        // Compatibility bridge for calls inherited from the 2.4 desktop source.
        if (type == 0) {
            requestRoomList();
            return;
        }

        if (type == 2 && id != null) {
            try {
                int encoded = Integer.parseInt(id);
                int room = encoded / 1000;
                int zone = encoded % 1000;
                if (room >= 0 && room <= 127 && zone >= 0 && zone <= 127) {
                    joinBoard((byte) room, (byte) zone, "");
                    return;
                }
            } catch (NumberFormatException ignored) {
                // Fall through to a visible error instead of sending unsupported -28.
            }
            CCanvas.endDlg();
            CCanvas.startOKDlg("Số phòng/khu vực không hợp lệ.");
            return;
        }

        // type 1 (2.4 create-zone protocol) has no equivalent in the 2.3 server.
        // Refresh the real room list rather than sending an unsupported packet.
        requestRoomList();
    }

    public void joinBoard(byte roomID, byte boardID, String pass) {
        Message m = new Message((byte) 8);

        try {
            m.writer().writeByte(roomID);
            m.writer().writeByte(boardID);
            m.writer().writeUTF(pass);
        } catch (IOException var6) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void setBoardName(String boardName) {
        Message m = new Message((byte) 54);

        try {
            m.writer().writeUTF(boardName);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void changeMODE(byte MODE_TYPE) {
        // 2.4-only packet 73. Hybrid 2.3 has no matching handler.
    }

    public void leaveBoard() {
        Message m = new Message((byte) 15);
        this.session.sendMessage(m);
        m.cleanup();
        CRes.out("GUI MESSAGE: LEAVE BOARD");
    }

    public void ready(boolean isReady) {
        Message m = new Message((byte) 16);

        try {
            m.writer().writeBoolean(isReady);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void setMoney(int money) {
        Message m = new Message((byte) 19);

        try {
            m.writer().writeInt(money);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void setPassword(String pass) {
        Message m = new Message((byte) 18);

        try {
            m.writer().writeUTF(pass);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void chatToBoard(String text) {
        Message m = new Message((byte) 9);

        try {
            m.writer().writeUTF(text);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void kick(int kickID) {
        Message m = new Message((byte) 11);

        try {
            m.writer().writeInt(kickID);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
        CRes.out("SendM Kick: ID" + kickID);
    }

    public void joinAnyBoard(byte select) {
        Message m = new Message((byte) 28);

        try {
            m.writer().writeByte(select);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void checkFall(byte id, boolean isLand) {
        // 2.4-only packet 80. Hybrid server is authoritative for landing/fall state.
    }

    public void requestRichest(int page) {
        // Classic Hybrid rankings are served through cmd -14.
        bangxephang((byte) 0, page);
    }

    public void requestStrongest(int page) {
        // Classic Hybrid rankings are served through cmd -14.
        bangxephang((byte) 1, page);
    }

    public void requestRegister(String username, String accLogin, String pass) {
        Message m = new Message((byte) 121);

        try {
            m.writer().writeUTF(username);
            m.writer().writeUTF(accLogin);
            m.writer().writeUTF(pass);
        } catch (IOException var6) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void requestRegister3(String username, String pass, String version) {
        // 2.4 registration packet is unsupported; registration is handled by the Hybrid web UI.
        CCanvas.endDlg();
        CCanvas.startOKDlg("Đăng ký tài khoản tại web Hybrid.");
    }

    public void requestFriendList() {
        Message m = new Message((byte) 29);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void searchFriend(String text) {
        Message m = new Message((byte) 36);

        try {
            m.writer().writeUTF(text);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void addFriend(int id) {
        Message m = new Message((byte) 32);

        try {
            m.writer().writeInt(id);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void deleteFriend(int id) {
        Message m = new Message((byte) 33);

        try {
            m.writer().writeInt(id);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void requestAvatar(short avatar) {
        // Legacy social-avatar service is not part of the Hybrid 2.3 server.
    }

    public void chatTo(int iddb, String text) {
        Message m = new Message((byte) 5);

        try {
            m.writer().writeInt(iddb);
            m.writer().writeUTF(text);
        } catch (IOException var5) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void requestUserData() {
        // Legacy social profile service is not part of the Hybrid 2.3 server.
    }

    public void ping(int a, long b) {
        // Hybrid 2.3 has no client cmd 42 ping. TCP/session state is tracked locally.
    }

    public void requestAvatarShop() {
        // Legacy avatar shop service is not part of the Hybrid 2.3 server.
    }

    public void updateDateProfile(UserData userData) {
        // Legacy profile service is not part of the Hybrid 2.3 server.
    }

    public void buyAvatar(short id) {
        // Legacy avatar service is not part of the Hybrid 2.3 server.
    }

    public void setProvider(byte provider) {
        // 2.4 provider handshake (cmd 58) is intentionally skipped on Hybrid 2.3.
    }

    public void requestChargeMoneyInfo2(byte type, String id) {
        Message m = new Message((byte) 122);

        try {
            m.writer().writeByte(type);
            if (type == 1) {
                m.writer().writeUTF(id);
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        CRes.out("====>requestChargeMoneyInfo2; type = " + type + " id = " + id);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void training(byte type) {
        Message m = new Message((byte) 83);

        try {
            m.writer().writeByte(type);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void doLoadCard(String text, String text2, String link) {
        Message m = new Message((byte) 77);

        try {
            m.writer().writeUTF(text);
            m.writer().writeUTF(text2);
            m.writer().writeUTF(link);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        CRes.out("====> send cmd 77");
        CRes.out("====> doLoadCard " + text);
        CRes.out("====> doLoadCard " + text2);
        CRes.out("====> doLoadCard " + link);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void sendAdminCommand(String cmd) {
        // No remote admin command exists in the Hybrid game protocol.
    }

    public void startGame() {
        Message m = new Message((byte) 20);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void mapSelect(byte mapID) {
        Message m = new Message((byte) 75);

        try {
            m.writer().writeByte(mapID);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void trainingMap() {
        Message m = new Message((byte) -6);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void check_cross(byte n, int[] x, int[] y) {
        Message m = new Message((byte) 79);

        try {
            m.writer().writeByte(n);

            for (int i = 0; i < n; ++i) {
                CRes.out("x= " + x[i]);
                m.writer().writeInt(x[i]);
                m.writer().writeInt(y[i]);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void move(short x, short y) {
        if (!GameScr.trainingMode) {
            Message m = new Message((byte) 21);
            CRes.out(" move to " + x + "," + y);

            try {
                m.writer().writeShort(x);
                m.writer().writeShort(y);
            } catch (Exception var5) {
            }

            this.session.sendMessage(m);
            m.cleanup();
        }
    }

    public void waitForFIRETraining(byte type, short x, short y, short angle, byte force, byte force_2, byte numShoot) {
        Message m = new Message((byte) 84);

        try {
            m.writer().writeByte(type);
            m.writer().writeShort(x);
            m.writer().writeShort(y);
            m.writer().writeShort(angle);
            m.writer().writeByte(force);
            if (Bullet.isDoubleBull(type)) {
                m.writer().writeByte(force_2);
            }

            m.writer().writeByte(numShoot);
        } catch (Exception var10) {
        }

        this.session.sendMessage(m);
        m.cleanup();
        CRes.out("SendWait_Fire bull: " + type + " nShoot: " + numShoot + " force2= " + force_2);
    }

    public void waitForFIRE(byte type, short x, short y, short angle, byte force, byte force_2, byte numShoot) {
        Message m = new Message((byte) 22);

        try {
            m.writer().writeByte(type);
            m.writer().writeShort(x);
            m.writer().writeShort(y);
            m.writer().writeShort(angle);
            m.writer().writeByte(force);
            if (Bullet.isDoubleBull(type)) {
                m.writer().writeByte(force_2);
            }

            m.writer().writeByte(numShoot);
        } catch (Exception var10) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void shootResult() {
        if (!GameScr.trainingMode) {
            Message m = new Message((byte) 23);
            this.session.sendMessage(m);
            m.cleanup();
        }
    }

    public void requiredUpdateXY(short x, short y) {
        CRes.out("==> requiredUpdateXY " + x + "_" + y);
        if (!GameScr.trainingMode) {
            Message m = new Message((byte) 53);

            try {
                m.writer().writeShort(x);
                m.writer().writeShort(y);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            this.session.sendMessage(m);
            m.cleanup();
        }
    }

    public void inviteFriend(boolean isList, int id) {
        Message m = new Message((byte) 78);

        try {
            m.writer().writeBoolean(isList);
            m.writer().writeInt(id);
        } catch (Exception var5) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void skipTurn() {
        if (!GameScr.trainingMode) {
            Message m = new Message((byte) 49);
            this.session.sendMessage(m);
            m.cleanup();
        }
    }

    public void useItem(byte item) {
        if (!GameScr.trainingMode) {
            Message m = new Message((byte) 26);

            try {
                m.writer().writeByte(item);
            } catch (Exception var4) {
            }

            this.session.sendMessage(m);
            m.cleanup();
            CRes.out("==========> SendM UseITEM " + item);
        }
    }

    public void changeItem(int[] typeItem) {
        Message m = new Message((byte) 68);

        try {
            for (int i = 0; i < typeItem.length; ++i) {
                m.writer().writeByte(typeItem[i]);
            }
        } catch (Exception var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
        CRes.out("SendM changeGun " + typeItem.length);
    }

    public void changeGun(byte newGun) {
        Message m = new Message((byte) 69);

        try {
            m.writer().writeByte(newGun);
        } catch (Exception var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
        CRes.out("SendM chooseGun " + newGun);
    }

    public void selectMap(byte map) {
        // Desktop 2.4 used cmd 70; Hybrid 2.3 uses cmd 75 with the same map byte.
        mapSelect(map);
    }

    public void changeTeam() {
        Message m = new Message((byte) 71);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void disconnect() {
        Message m = new Message((byte) -4);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void setMaxPlayer(int max) {
        Message m = new Message((byte) 56);

        try {
            m.writer().writeByte(max);
        } catch (Exception var4) {
        }

        this.session.sendMessage(m);
    }

    public void requestInfoOf(int iDDB) {
        Message m = new Message((byte) 34);

        try {
            m.writer().writeInt(iDDB);
        } catch (Exception var4) {
        }

        this.session.sendMessage(m);
    }

    public void requestChangePass(String oldPass, String newPass) {
        Message m = new Message((byte) 81);

        try {
            m.writer().writeUTF(oldPass);
            m.writer().writeUTF(newPass);
        } catch (Exception var5) {
        }

        this.session.sendMessage(m);
    }

    public void requestBuyItem(byte action, byte id, byte numBuy) {
        Message m = new Message((byte) 72);

        try {
            m.writer().writeByte(action);
            m.writer().writeByte(id);
            m.writer().writeByte(numBuy);
        } catch (Exception var6) {
        }

        this.session.sendMessage(m);
    }

    public void buyGun(byte gunID, byte type) {
        Message m = new Message((byte) 74);

        try {
            m.writer().writeByte(gunID);
            m.writer().writeByte(type);
        } catch (Exception var5) {
        }

        this.session.sendMessage(m);
    }

    public void requestService(byte service, String arg) {
        // 2.4 carrier/service packet is unsupported by Hybrid 2.3.
        CCanvas.endDlg();
        CCanvas.startOKDlg("Tính năng này không dùng trên máy chủ Hybrid.");
    }

    public void zingConnect(String user, String key, byte bigProvider, String version) {
        // Legacy third-party login is not part of the Hybrid 2.3 server.
    }

    public void getString(String str) {
        // 2.4 provider/agent packet 127 is not used by Hybrid 2.3.
    }

    public void getProviderAgent() {
        // 2.4 provider/agent packet -26 is not used by Hybrid 2.3.
    }

    public void sendVersion(byte type, byte version) {
        Message m = new Message((byte) 90);

        try {
            m.writer().writeByte(type);
            m.writer().writeByte(version);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void addPoint(byte[] point) {
        Message m = new Message((byte) 98);

        try {
            m.writer().writeShort(point[0]);
            m.writer().writeShort(point[1]);
            m.writer().writeShort(point[2]);
            m.writer().writeShort(point[3]);
            m.writer().writeShort(point[4]);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void changeEquip(int[] id) {
        CRes.out("CHANGE EQUIP");
        Message m = new Message((byte) 102);

        try {
            for (int i = 0; i < id.length; ++i) {
                m.writer().writeInt(id[i]);
                CRes.out("qweqwdasdsad= " + id[i]);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void getShopEquip() {
        Message m = new Message((byte) 103);
        this.session.sendMessage(m);
    }

    public void buy_sell_Equip(byte action, int[] index, short ind, byte money) {
        Message m = new Message((byte) 104);

        try {
            m.writer().writeByte(action);
            if (action == 1) {
                m.writer().writeByte(index.length);

                for (int i = 0; i < index.length; ++i) {
                    m.writer().writeInt(index[i]);
                }
            }

            if (action == 0) {
                m.writer().writeShort(ind);
                m.writer().writeByte(money);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void sendRulet(byte money) {
        Message m = new Message((byte) 110);

        try {
            m.writer().writeByte(money);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void charactorInfo() {
        Message m = new Message((byte) 99);
        this.session.sendMessage(m);
    }

    public void platform_request() {
        Message m = new Message((byte) 114);

        try {
            if (GameMidlet.DEVICE == 2) {
                m.writer().writeUTF("iphone");
            } else if (GameMidlet.DEVICE == 1) {
                m.writer().writeUTF("android");
            } else if (GameMidlet.DEVICE == 4) {
                m.writer().writeUTF("pc");
            } else if (GameMidlet.DEVICE == 0) {
                m.writer().writeUTF("j2me{HD}");
            } else {
                m.writer().writeUTF("j2me{HD}");
            }

            m.writer().writeByte(GameMidlet.versioncode);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void vip_equip(byte action, int dbKey) {
        CRes.out("GUI + " + action);
        Message m = new Message((byte) -2);

        try {
            m.writer().writeByte(action);
            m.writer().writeInt(dbKey);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void topClan(byte page) {
        Message m = new Message((byte) 116);

        try {
            m.writer().writeByte(page);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void getClanIcon(short id) {
        if (id != 0 && id != -1) {
            Image img = null;
            if (CCanvas.iconMn.isExist(id)) {
                CRes.out("tim thay icon");
                img = CCanvas.iconMn.getImage(id);
                GameLogicHandler.gI().onGetImage(id, img);
            } else {
                CRes.out("request icon");
                Message m = new Message((byte) 115);

                try {
                    m.writer().writeShort(id);
                } catch (Exception var5) {
                    var5.printStackTrace();
                }

                this.session.sendMessage(m);
            }

        }
    }

    public void clanInfo(short id) {
        Message m = new Message((byte) 117);

        try {
            m.writer().writeShort(id);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void clanMember(byte page, short id) {
        Message m = new Message((byte) 118);

        try {
            m.writer().writeByte(page);
            m.writer().writeShort(id);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void getBigImage(byte id) {
        Message m = new Message((byte) 120);

        try {
            m.writer().writeByte(id);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void chatTeam(String mess) {
        // 2.4 team-chat packet 123 is not implemented by this Hybrid server.
    }

    public void getMaterialIcon(byte action, int id, int index) {
        CRes.out("get material icon " + id);
        Message m = new Message((byte) 126);

        try {
            m.writer().writeByte(action);
            m.writer().writeByte(id);
            if (action == 3) {
                m.writer().writeByte(index);
            }

            if (action == 4) {
                m.writer().writeByte(index);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void getFomula(byte id, byte action, byte level) {
        CRes.out("get fomula");
        Message m = new Message((byte) -18);

        try {
            m.writer().writeByte(id);
            m.writer().writeByte(action);
            if (action == 2) {
                m.writer().writeByte(level);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void imbue(byte type, byte num, int[] id, byte[] nSelect) {
        Message m = new Message((byte) 17);

        try {
            if (type == 0) {
                m.writer().writeByte(type);
                m.writer().writeByte(num);
                CRes.out("Num= " + num);

                for (int i = 0; i < num; ++i) {
                    m.writer().writeInt(id[i]);
                    m.writer().writeByte(nSelect[i]);
                    CRes.out("ID= " + id[i]);
                }
            }

            if (type == 1) {
                m.writer().writeByte(type);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void getShopLinhtinh(byte action, byte typeMoney, byte id, byte num) {
        Message m = new Message((byte) -3);

        try {
            m.writer().writeByte(action);
            if (action == 1) {
                m.writer().writeByte(typeMoney);
                m.writer().writeByte(id);
                m.writer().writeByte(num);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void signOut() {
        Message m = new Message((byte) -4);
        this.session.sendMessage(m);
    }

    public void changeRoomName() {
        // 2.4 room-name sync packet -19 is not implemented by Hybrid 2.3.
    }

    public void getShopBietDoi(byte action, byte money, byte id) {
        Message m = new Message((byte) -12);

        try {
            m.writer().writeByte(action);
            if (action == 1) {
                m.writer().writeByte(money);
                m.writer().writeByte(id);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void bangxephang(byte type, int page) {
        CRes.out("request list : page= " + page);
        Message m = new Message((byte) -14);

        try {
            m.writer().writeByte(type);
            m.writer().writeByte(page);
        } catch (IOException var5) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void luckGift(byte id) {
        CRes.out(" =======> send Lucky gift to server id == " + id);
        Message m = new Message((byte) -17);

        try {
            m.writer().writeByte(id);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void mission(byte type, byte id) {
        Message m = new Message((byte) -23);

        try {
            m.writer().writeByte(type);
            if (type == 1) {
                m.writer().writeByte(id);
            }
        } catch (IOException var5) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void inputMoney(byte type, int money) {
        Message m = new Message((byte) -21);

        try {
            m.writer().writeByte(type);
            m.writer().writeInt(money);
        } catch (IOException var5) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void get_more_day(byte action, int id) {
        CRes.out("Gia han");
        Message m = new Message((byte) -25);

        try {
            m.writer().writeByte(action);
            m.writer().writeInt(id);
        } catch (IOException var5) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void holeInfo(Vector holeInfo) {
        // 2.4 terrain-hole sync packet -92 is not used; Hybrid server owns fight state.
    }

    public void debugServer() {
        Message m = new Message(-25);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void onRegisterNickFree(String charname, String email_phone, String pass) {
        CRes.out("=========> START REGISTER NEW FREE ACCOUNT!");
        Message m = new Message((byte) 121);

        try {
            m.writer().writeUTF(charname);
            m.writer().writeUTF(email_phone);
            m.writer().writeUTF(pass);
        } catch (Exception var6) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void onSendChangeRequest(String charName) {
        CCanvas.endDlg();
        CCanvas.startOKDlg("Đổi tên nhân vật không được hỗ trợ bởi giao thức Hybrid 2.3.");
    }

    public void onInApppurchaseToServer(String product_ID, String token) {
        // Mobile in-app purchase packet -102 is disabled on desktop Hybrid.
    }
}
