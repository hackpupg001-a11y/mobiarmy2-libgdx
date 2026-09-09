package Equipment;

import CLib.mGraphics;
import CLib.mImage;
import coreLG.TerrainMidlet;

import java.util.Vector;

import model.Font;
import model.Language;
import model.PlayerInfo;
import screen.EquipScreen;

public class Equip {
    public int dbKey;
    public short id;
    public byte frame;
    public short[] x;
    public short[] y;
    public byte[] w;
    public byte[] h;
    public byte[] dx;
    public byte[] dy;
    public short icon;
    public byte type;
    public byte glass;
    public byte slot;
    public byte vip;
    public boolean isVip;
    public int num = 1;
    public int numSelected = 1;
    public boolean isBuyNum = false;
    public short[] inv_attAddPoint = new short[5];
    public byte[] inv_ability = new byte[5];
    public byte[] inv_percen = new byte[5];
    public short[] shop_attAddPoint = new short[5];
    public byte[] shop_ability = new byte[5];
    public byte[] shop_percen = new byte[5];
    public int index;
    public byte date;
    public String name;
    public int xu;
    public int luong;
    public byte level;
    public int level2;
    public boolean isSelect = false;
    public byte bullet;
    public boolean isMaterial = false;
    public String strDetail;
    public mImage materialIcon;
    public boolean notPaint;
    public int shopDetailNunmLines;
    public Vector<String> shopDetailNunStrs = null;
    int header;

    public Equip(short id, byte type, byte frame, short[] x, short[] y, byte[] w, byte[] h, byte[] dx, byte[] dy, short icon, byte bullet) {
        this.id = id;
        this.type = type;
        this.frame = frame;
        this.x = new short[frame];
        this.y = new short[frame];
        this.w = new byte[frame];
        this.h = new byte[frame];
        this.dx = new byte[frame];
        this.dy = new byte[frame];

        for (int i = 0; i < frame; ++i) {
            this.x[i] = x[i];
            this.y[i] = y[i];
            this.w[i] = w[i];
            this.h[i] = h[i];
            this.dx[i] = dx[i];
            this.dy[i] = dy[i];
        }

        this.icon = icon;
        this.type = type;
        this.bullet = bullet;
    }

    public Equip() {
    }

    public int[] getBaseAttribute() {
        int[] atts = new int[5];
        PlayerInfo info = TerrainMidlet.myInfo;
        if (info == null || info.ability == null || info.attribute == null) {
            return atts;
        }

        int[] ability = new int[5];
        int[] percen = new int[5];
        for (int i = 0; i < 5; ++i) {
            ability[i] += arrayValue(this.inv_ability, i);
            percen[i] += arrayValue(this.inv_percen, i);
        }

        atts[0] = ability[0] * 10;
        atts[0] += (1000 + arrayValue(info.ability, 0) * 10) * percen[0] / 100;
        EquipGlass glassInfo = PlayerEquip.getEquipGlass(info.gun);
        int maxDamage = glassInfo == null ? 0 : glassInfo.maxDamage;
        int damPoint = ability[1] + arrayValue(info.attribute, 1);
        int defPoint = ability[2] + arrayValue(info.attribute, 2);
        int luckPoint = ability[3] + arrayValue(info.attribute, 3);
        int team = ability[4] + arrayValue(info.attribute, 4);
        atts[1] = maxDamage * (damPoint / 3 + 100 + percen[1]) / 100;
        atts[2] = defPoint * 10;
        atts[2] += atts[2] * percen[2] / 100;
        atts[3] = luckPoint * 10;
        atts[3] += atts[3] * percen[3] / 100;
        atts[4] = team * 10;
        atts[4] += atts[4] * percen[4] / 100;
        return atts;
    }

    public void setInvAtribute() {
        PlayerInfo m = TerrainMidlet.myInfo;
        for (int i = 0; i < 5; ++i) {
            this.inv_attAddPoint[i] = 0;
            this.inv_attAddPoint[i] = (short)(this.inv_attAddPoint[i] + arrayValue(this.inv_ability, i));
            if (m != null && m.attribute != null) {
                this.inv_attAddPoint[i] = (short)(this.inv_attAddPoint[i]
                        + arrayValue(m.attribute, i) * arrayValue(this.inv_percen, i) / 100);
            }
        }
    }

    public void removeAbility() {
        for (int i = 0; i < 5; ++i) {
            this.inv_attAddPoint[i] = 0;
            this.inv_percen[i] = 0;
            this.inv_ability[i] = 0;
        }

    }

    public void addAbilityFromEquip(Equip e) {
        if (e == null) {
            return;
        }
        for (int i = 0; i < 5; ++i) {
            this.inv_ability[i] = (byte)arrayValue(e.inv_ability, i);
            this.inv_attAddPoint[i] = (short)arrayValue(e.inv_attAddPoint, i);
            this.inv_percen[i] = (byte)arrayValue(e.inv_percen, i);
        }
    }

    public void getInvAtribute(byte[] ability) {
        if (ability == null) {
            return;
        }
        PlayerInfo m = TerrainMidlet.myInfo;
        int pairCount = Math.min(5, (ability.length + 1) / 2);
        for (int a = 0; a < pairCount; ++a) {
            int abilityIndex = a * 2;
            int percentIndex = abilityIndex + 1;
            int flat = abilityIndex < ability.length ? ability[abilityIndex] : 0;
            int percent = percentIndex < ability.length ? ability[percentIndex] : 0;
            this.inv_ability[a] = (byte)flat;
            this.inv_percen[a] = (byte)percent;
            int base = m == null || m.attribute == null ? 0 : arrayValue(m.attribute, a);
            this.inv_attAddPoint[a] = (short)(flat + base * percent / 100);
        }
    }

    public boolean isSameEquip(Equip e) {
        return this.id == e.id && this.type == e.type && this.glass == e.glass;
    }

    public void getShopAtribute(byte[] ability) {
        if (ability == null) {
            return;
        }
        int pairCount = Math.min(5, (ability.length + 1) / 2);
        for (int a = 0; a < pairCount; ++a) {
            int abilityIndex = a * 2;
            int percentIndex = abilityIndex + 1;
            this.shop_ability[a] = abilityIndex < ability.length ? ability[abilityIndex] : 0;
            this.shop_percen[a] = percentIndex < ability.length ? ability[percentIndex] : 0;
        }
    }

    public void changeToEquip(Equip e) {
        if (e == null) {
            return;
        }
        this.icon = e.icon;
        this.glass = e.glass;
        this.type = e.type;
        this.id = e.id;
        this.date = e.date;
        int l = minFrameCount(e);
        this.x = new short[l];
        this.y = new short[l];
        this.w = new byte[l];
        this.h = new byte[l];
        this.dx = new byte[l];
        this.dy = new byte[l];
        for (int i = 0; i < l; ++i) {
            this.x[i] = e.x[i];
            this.y[i] = e.y[i];
            this.dx[i] = e.dx[i];
            this.dy[i] = e.dy[i];
            this.w[i] = e.w[i];
            this.h[i] = e.h[i];
        }
        this.bullet = e.bullet;
        this.frame = (byte)l;
        this.addAbilityFromEquip(e);
        this.dbKey = e.dbKey;
        this.level = e.level;
        this.level2 = e.level2;
        this.name = e.name;
        this.slot = e.slot;
        this.vip = e.vip;
        this.xu = e.xu;
        this.luong = e.luong;
    }

    public String getStrInvDetail() {
        ensureAttributeArrays();
        String attribute = "";
        if (this.inv_ability[0] != 0) {
            attribute = attribute + Language.sinhluc() + " +" + this.inv_ability[0] + ".";
        }

        if (this.inv_ability[1] != 0) {
            attribute = attribute + " " + Language.sucmanh() + " +" + this.inv_ability[1] + ".";
        }

        if (this.inv_ability[2] != 0) {
            attribute = attribute + " " + Language.phongthu() + " +" + this.inv_ability[2] + ".";
        }

        if (this.inv_ability[3] != 0) {
            attribute = attribute + " " + Language.mayman() + " +" + this.inv_ability[3] + ".";
        }

        if (this.inv_ability[4] != 0) {
            attribute = attribute + " " + Language.dongdoi() + " +" + this.inv_ability[4] + ".";
        }

        if (this.vip == 0) {
            if (this.inv_percen[0] != 0) {
                attribute = attribute + " " + Language.sinhluc() + " +" + this.inv_percen[0] + "%.";
            }

            if (this.inv_percen[1] != 0) {
                attribute = attribute + " " + Language.sucmanh() + " +" + this.inv_percen[1] + "%.";
            }

            if (this.inv_percen[2] != 0) {
                attribute = attribute + " " + Language.phongthu() + " +" + this.inv_percen[2] + "%.";
            }

            if (this.inv_percen[3] != 0) {
                attribute = attribute + " " + Language.mayman() + " +" + this.inv_percen[3] + "%.";
            }

            if (this.inv_percen[4] != 0) {
                attribute = attribute + " " + Language.dongdoi() + " +" + this.inv_percen[4] + "%.";
            }
        } else {
            attribute = attribute + " " + Language.All5();
        }

        if (this.date != 0) {
            attribute = attribute + " " + Language.expr() + ": " + this.date + ".";
        } else {
            attribute = attribute + " Hết hạn sử dụng .";
        }

        attribute = attribute + " " + Language.eSlot() + " " + this.slot + ".";
        return attribute;
    }

    public String getStrShopDetail() {
        ensureAttributeArrays();
        String attribute = "";
        this.shopDetailNunmLines = 0;
        this.shopDetailNunStrs = new Vector();
        if (this.shop_ability[0] != 0) {
            attribute = attribute + Language.sinhluc() + " +" + this.shop_ability[0] + " ";
            this.shopDetailNunStrs.addElement(Language.sinhluc() + " +" + this.shop_ability[0] + " ");
            ++this.shopDetailNunmLines;
        }

        if (this.shop_ability[1] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.sucmanh() + " +" + this.shop_ability[1] + " ";
            this.shopDetailNunStrs.addElement(" " + Language.sucmanh() + " +" + this.shop_ability[1] + " ");
        }

        if (this.shop_ability[2] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.phongthu() + " +" + this.shop_ability[2] + " ";
            this.shopDetailNunStrs.addElement(" " + Language.phongthu() + " +" + this.shop_ability[2] + " ");
        }

        if (this.shop_ability[3] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.mayman() + " +" + this.shop_ability[3] + " ";
            this.shopDetailNunStrs.addElement(" " + Language.mayman() + " +" + this.shop_ability[3] + " ");
        }

        if (this.shop_ability[4] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.dongdoi() + " +" + this.shop_ability[4] + " ";
            this.shopDetailNunStrs.addElement(" " + Language.dongdoi() + " +" + this.shop_ability[4] + " ");
        }

        if (this.shop_percen[0] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.sinhluc() + " +" + this.shop_percen[0] + "% ";
            this.shopDetailNunStrs.addElement(" " + Language.sinhluc() + " +" + this.shop_percen[0] + "% ");
        }

        if (this.shop_percen[1] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.sucmanh() + " +" + this.shop_percen[1] + "% ";
            this.shopDetailNunStrs.addElement(" " + Language.sucmanh() + " +" + this.shop_percen[1] + "% ");
        }

        if (this.shop_percen[2] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.phongthu() + " +" + this.shop_percen[2] + "% ";
            this.shopDetailNunStrs.addElement(" " + Language.phongthu() + " +" + this.shop_percen[2] + "% ");
        }

        if (this.shop_percen[3] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.mayman() + " +" + this.shop_percen[3] + "% ";
            this.shopDetailNunStrs.addElement(" " + Language.mayman() + " +" + this.shop_percen[3] + "% ");
        }

        if (this.shop_percen[4] != 0) {
            ++this.shopDetailNunmLines;
            attribute = attribute + " " + Language.dongdoi() + " +" + this.shop_percen[4] + "%";
            this.shopDetailNunStrs.addElement(" " + Language.dongdoi() + " +" + this.shop_percen[4] + "% ");
        }

        return attribute;
    }

    public void drawImage(mGraphics g, int Look, int Frame, int X, int Y) {
        if (this.notPaint || g == null || Frame < 0) {
            return;
        }
        int glassIndex = this.glass & 255;
        if (glassIndex >= PlayerEquip.imgData.length || PlayerEquip.imgData[glassIndex] == null
                || PlayerEquip.imgData[glassIndex].image == null || !hasFrame(Frame)) {
            return;
        }
        int srcW = this.w[Frame] & 255;
        int srcH = this.h[Frame] & 255;
        if (srcW <= 0 || srcH <= 0) {
            return;
        }
        int imageW = PlayerEquip.imgData[glassIndex].image.getWidth();
        int imageH = PlayerEquip.imgData[glassIndex].image.getHeight();
        if (this.x[Frame] < 0 || this.y[Frame] < 0 || this.x[Frame] + srcW > imageW || this.y[Frame] + srcH > imageH) {
            return;
        }

        int W = 0;
        int H = 0;
        if (glassIndex == 0 || glassIndex == 1 || glassIndex == 2 || glassIndex == 4 || glassIndex == 5 || glassIndex == 8 || glassIndex == 9) {
            W = 24;
            H = 24;
        } else if (glassIndex == 3) {
            W = 30;
            H = 32;
        } else if (glassIndex == 6) {
            W = 29;
            H = 24;
        } else if (glassIndex == 7) {
            W = 32;
            H = 32;
        }

        if (Look == 0) {
            g.drawRegion(PlayerEquip.imgData[glassIndex], this.x[Frame], this.y[Frame], srcW, srcH, Look,
                    X - W / 2 + this.dx[Frame] + 18, Y - H + this.dy[Frame] + 40, 0, false);
        } else if (Look == 2) {
            g.drawRegion(PlayerEquip.imgData[glassIndex], this.x[Frame], this.y[Frame], srcW, srcH, Look,
                    X + W / 2 - (this.dx[Frame] + 18), Y - H + this.dy[Frame] + 40,
                    mGraphics.TOP | mGraphics.RIGHT, false);
        }
    }

    public void drawIcon(mGraphics g, int X, int Y, boolean isClip) {
        if (g == null) {
            return;
        }
        if (!this.isMaterial) {
            int iconValue = this.icon;
            if (iconValue >= 0) {
                int offset = iconValue * 16;
                int index = offset / 1024;
                offset %= 1024;
                if (EquipScreen.imgIconEQ != null && index >= 0 && index < EquipScreen.imgIconEQ.length) {
                    mImage sheet = EquipScreen.imgIconEQ[index];
                    if (sheet != null && sheet.image != null && sheet.image.getWidth() >= 16
                            && offset >= 0 && offset + 16 <= sheet.image.getHeight()) {
                        g.drawRegion(sheet, 0, offset, 16, 16, 0, X, Y, 0, isClip);
                    }
                }
            }
        } else {
            if (this.materialIcon != null && this.materialIcon.image != null) {
                g.drawImage(this.materialIcon, X + 8, Y + 8, 3, isClip);
            }
            if (this.num > 1) {
                Font.smallFontYellow.drawString(g, String.valueOf(this.num), X + 11, Y + 11, 0);
            }
            if (this.numSelected > 1 && !this.isSelect || this.numSelected >= 1 && this.isSelect) {
                Font.smallFontRed.drawString(g, String.valueOf(this.numSelected), X + 11, Y, 0);
            }
        }
        if (this.isSelect) {
            g.setColor(0);
            g.drawRect(X, Y, 16, 16, true);
        }
    }

    private boolean hasFrame(int frameIndex) {
        return this.x != null && this.y != null && this.w != null && this.h != null && this.dx != null && this.dy != null
                && frameIndex < this.x.length && frameIndex < this.y.length && frameIndex < this.w.length
                && frameIndex < this.h.length && frameIndex < this.dx.length && frameIndex < this.dy.length;
    }

    private static int minFrameCount(Equip e) {
        if (e == null || e.x == null || e.y == null || e.w == null || e.h == null || e.dx == null || e.dy == null) {
            return 0;
        }
        int length = e.x.length;
        length = Math.min(length, e.y.length);
        length = Math.min(length, e.w.length);
        length = Math.min(length, e.h.length);
        length = Math.min(length, e.dx.length);
        length = Math.min(length, e.dy.length);
        return Math.max(0, length);
    }

    private void ensureAttributeArrays() {
        if (this.inv_attAddPoint == null || this.inv_attAddPoint.length < 5) this.inv_attAddPoint = new short[5];
        if (this.inv_ability == null || this.inv_ability.length < 5) this.inv_ability = new byte[5];
        if (this.inv_percen == null || this.inv_percen.length < 5) this.inv_percen = new byte[5];
        if (this.shop_attAddPoint == null || this.shop_attAddPoint.length < 5) this.shop_attAddPoint = new short[5];
        if (this.shop_ability == null || this.shop_ability.length < 5) this.shop_ability = new byte[5];
        if (this.shop_percen == null || this.shop_percen.length < 5) this.shop_percen = new byte[5];
    }

    private static int arrayValue(byte[] values, int index) {
        return values != null && index >= 0 && index < values.length ? values[index] : 0;
    }

    private static int arrayValue(short[] values, int index) {
        return values != null && index >= 0 && index < values.length ? values[index] : 0;
    }

    private static int arrayValue(int[] values, int index) {
        return values != null && index >= 0 && index < values.length ? values[index] : 0;
    }
}
