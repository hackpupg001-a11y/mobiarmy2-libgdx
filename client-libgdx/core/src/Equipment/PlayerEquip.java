package Equipment;

import CLib.mGraphics;
import CLib.mImage;

import java.util.Vector;

import player.CPlayer;

public class PlayerEquip {
    public byte glass;
    public byte type;
    public byte id;
    public static Vector playerData;
    public static mImage[] imgData = new mImage[10];
    public static byte[][] data = new byte[10][];
    public static byte[][][] header = new byte[10][10][];
    public static mImage[] bullets = new mImage[10];
    public Equip[] equips = new Equip[5];
    public static Vector headers = new Vector();
    int d;

    public PlayerEquip() {
    }

    public PlayerEquip(short[][] equip) {
        this.equips[0] = createEquip((byte) equip[0][0], (byte) equip[0][1], equip[0][2]);
        this.equips[1] = createEquip((byte) equip[1][0], (byte) equip[1][1], equip[1][2]);
        this.equips[2] = createEquip((byte) equip[2][0], (byte) equip[2][1], equip[2][2]);
        this.equips[3] = createEquip((byte) equip[3][0], (byte) equip[3][1], equip[3][2]);
        this.equips[4] = createEquip((byte) equip[4][0], (byte) equip[4][1], equip[4][2]);
    }

    public void addOneEquip(Equip eq) {
        for (int i = 0; i < 5; ++i) {
            if (this.equips[i] != null) {
                for (int a = 0; a < 5; ++a) {
                    this.equips[i].inv_attAddPoint[a] = eq.inv_attAddPoint[a];
                }
            }
        }

    }

    public static void addGlassEquip(Vector glass) {
        playerData = glass;
    }

    public static EquipGlass getEquipGlass(byte id) {
        if (playerData == null) {
            return null;
        }
        for (int i = 0; i < playerData.size(); ++i) {
            EquipGlass eq = (EquipGlass) playerData.elementAt(i);
            if (eq != null && eq.glassID == id) {
                return eq;
            }
        }

        return null;
    }

    public static Equip getEquip(byte glass, byte type, short id) {
        EquipGlass g = getEquipGlass(glass);
        if (g != null) {
            TypeEquip t = g.getType(type);
            if (t != null) {
                Equip e = t.getEquip(id);
                if (e != null) {
                    e.glass = glass;
                    return e;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public static Equip createEquip(byte glass, byte type, short id) {
        Equip tam = getEquip(glass, type, id);
        if (tam == null) {
            return null;
        }
        Equip e = new Equip();
        e.glass = tam.glass;
        e.type = tam.type;
        e.id = tam.id;
        e.name = tam.name;
        e.date = tam.date;
        e.x = tam.x;
        e.y = tam.y;
        e.dx = tam.dx;
        e.dy = tam.dy;
        e.w = tam.w;
        e.h = tam.h;
        e.level = tam.level;
        e.level2 = tam.level2;
        e.frame = tam.frame;
        e.icon = tam.icon;
        e.xu = tam.xu;
        e.luong = tam.luong;
        e.bullet = tam.bullet;
        e.index = tam.index;
        e.slot = tam.slot;
        e.vip = tam.vip;
        e.addAbilityFromEquip(tam);
        if (tam.shop_ability != null) {
            e.shop_ability = tam.shop_ability.clone();
        }
        if (tam.shop_percen != null) {
            e.shop_percen = tam.shop_percen.clone();
        }
        if (tam.shop_attAddPoint != null) {
            e.shop_attAddPoint = tam.shop_attAddPoint.clone();
        }
        return e;
    }

    public void paintGiap(int x, int y, int look, int frame, mGraphics g) {
        if (this.equips[2] != null) {
            this.equips[2].drawImage(g, look, frame, x, y);
        }

    }

    public void paintNon(int x, int y, int look, int frame, mGraphics g) {
        if (this.equips[1] != null) {
            this.equips[1].drawImage(g, look, frame, x, y);
        }

    }

    public void paintKinh(int x, int y, int look, int frame, mGraphics g) {
        if (this.equips[3] != null) {
            this.equips[3].drawImage(g, look, frame, x, y);
        }

    }

    public void paintCanh(int x, int y, int look, int frame, mGraphics g) {
        if (this.equips[4] != null) {
            this.equips[4].drawImage(g, look, frame, x, y);
        }

    }

    public void paintSung(int x, int y, int look, int frame, mGraphics g) {
        if (this.equips[0] != null) {
            this.equips[0].drawImage(g, look, frame, x, y);
        }

    }

    public void paintFace(int x, int y, int look, int frame, mGraphics g) {
        if (g == null || this.equips == null || this.equips.length == 0 || this.equips[0] == null) {
            return;
        }
        int glassIndex = this.equips[0].glass & 255;
        if (CPlayer.pImg == null || glassIndex >= CPlayer.pImg.length) {
            return;
        }
        mImage img = CPlayer.pImg[glassIndex];
        if (img == null || img.image == null) {
            return;
        }
        int W = img.image.getWidth();
        int H = img.image.getHeight() / 10;
        if (W <= 0 || H <= 0 || frame < 0 || frame * H + H > img.image.getHeight()) {
            return;
        }
        g.drawRegion(img, 0, frame * H, W, H, look, x, y, mGraphics.BOTTOM | mGraphics.HCENTER, false);
    }

    public void paint(mGraphics g, int look, int frame, int x, int y) {
        this.paintSung(x, y, look, frame, g);
        this.paintCanh(x, y, look, frame, g);
        this.paintFace(x, y, look, frame, g);
        this.paintNon(x, y, look, frame, g);
        this.paintGiap(x, y, look, frame, g);
        this.paintKinh(x, y, look, frame, g);
    }
}
