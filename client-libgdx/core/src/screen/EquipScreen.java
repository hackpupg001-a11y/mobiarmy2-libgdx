package screen;

import CLib.Image;
import CLib.mGraphics;
import CLib.mImage;
import Equipment.Equip;
import Equipment.PlayerEquip;
import coreLG.CCanvas;
import coreLG.TerrainMidlet;
import java.util.Vector;
import model.CRes;
import model.Font;
import model.IAction;
import model.Language;
import model.MaterialIconMn;
import model.PlayerInfo;
import model.Position;
import network.Command;
import network.GameService;
import player.CPlayer;

public class EquipScreen extends TabScreen {
   int num;
   int select;
   int[] xE;
   int[] yE;
   byte[] typeE;
   int[] dbKeyChange = new int[5];
   boolean isSelect;
   public static Vector<Equip> inventory = new Vector();
   public Vector<Equip> myEquips = new Vector();
   public static mImage imgIcon;
   public static mImage imgMaterial;
   public static mImage[] imgIconEQ = new mImage[5];
   public int wTab;
   public int wIndex;
   public int hIndex;
   public int wP;
   public Command cmdSelect;
   public Command menu;
   public static boolean isEquip = false;
   int dem;
   boolean isCompine;
   short mSelect;
   short mComSelect;
   public short[] lastDb = new short[5];
   public Vector vLastE = new Vector();
   public Equip[] lastEquip = new Equip[5];
   int W;
   static int cmtoYI;
   static int cmyI;
   static int cmdyI;
   static int cmvyI;
   int nLine;
   int ind;
   public byte[] addPoint;
   public int[] atts;
   public Equip equipSelect;
   int dx;
   String attribute;
   String name;
   int xName;
   int wName;
   int wDetail;
   boolean scroll;
   int ds;
   Position transText1;
   Position transText2;
   int cc;
   int ee;
   public Equip[] currEq;
   PlayerEquip equip;
   int pa;
   boolean trans;
   int speed;
   int cmtoYITem;

   public EquipScreen() {
      this.W = CCanvas.width;
      this.addPoint = new byte[6];
      this.atts = new int[5];
      this.dx = -1;
      this.attribute = "";
      this.name = "";
      this.transText1 = new Position(0, 1);
      this.transText2 = new Position(0, 1);
      this.currEq = new Equip[5];
      this.equip = null;
      this.pa = 0;
      this.trans = false;
      this.speed = 1;
      this.nameCScreen = "EquipScreen screen!";
      this.xPaint = CCanvas.width / 2 - 75;
      this.yPaint = (CCanvas.hieght - CScreen.cmdH) / 2 - 85;
      this.wTabScreen = 150;
      this.hTabScreen = 170;
      this.xE = new int[]{this.W / 2 - 52, this.W / 2 - 77, this.W / 2 - 27, this.W / 2 - 37, this.W / 2 - 67};
      this.yE = new int[]{this.yPaint + 33, this.yPaint + 55, this.yPaint + 55, this.yPaint + 83, this.yPaint + 83};
      this.typeE = new byte[]{0, 1, 2, 3, 4};
      this.menu = new Command("Menu", new IAction() {
         public void perform() {
            Command detail = new Command(Language.detail(), new IAction() {
               public void perform() {
                  EquipScreen.this.doDetail();
               }
            });
            Command inventory = new Command(Language.ruongdo(), new IAction() {
               public void perform() {
                  if (EquipScreen.inventory.size() == 0) {
                     CCanvas.startOKDlg(Language.beNotInventory());
                  } else {
                     EquipScreen.this.doInventory();
                  }

               }
            });
            String title = !EquipScreen.this.isCompine ? Language.kethop() : Language.trangbi();
            new Command(title, new IAction() {
               public void perform() {
                  EquipScreen.this.isCompine = !EquipScreen.this.isCompine;
                  if (!EquipScreen.this.isCompine) {
                     for(int i = 0; i < EquipScreen.this.myEquips.size(); ++i) {
                        ((Equip)EquipScreen.this.myEquips.elementAt(i)).isSelect = false;
                     }
                  }

               }
            });
            Vector<Command> menu = new Vector();
            final Equip selectedEquip = EquipScreen.this.getEquipSelect();
            if (selectedEquip != null) {
               if (selectedEquip.date == 0) {
                  Command giahan = new Command("Gia hạn", new IAction() {
                     public void perform() {
                        CCanvas.startOKDlg(Language.pleaseWait());
                        GameService.gI().get_more_day((byte)0, selectedEquip.dbKey);
                     }
                  });
                  menu.addElement(giahan);
               }

               menu.addElement(detail);
            }

            menu.addElement(inventory);
            CCanvas.menu.startAt(menu, 0);
         }
      });
      this.left = this.menu;
      this.cmdSelect = new Command(Language.select(), new IAction() {
         public void perform() {
            EquipScreen.this.doFire();
         }
      });
      this.center = this.cmdSelect;
      this.right = new Command(Language.close(), new IAction() {
         public void perform() {
            for(int i = 0; i < EquipScreen.this.lastDb.length; ++i) {
               if (EquipScreen.this.dbKeyChange[i] != EquipScreen.this.lastDb[i]) {
                  CCanvas.startYesNoDlg(Language.saveEquip(), new IAction() {
                     public void perform() {
                        EquipScreen.this.doAgree();
                     }
                  }, new IAction() {
                     public void perform() {
                        EquipScreen.this.doClose();
                     }
                  });
                  return;
               }
            }

            EquipScreen.this.doClose();
         }
      });
      this.title = Language.trangbi();
      this.n = 4;
      this.getW();
      if (CCanvas.isTouch) {
         this.wTab = 30;
         this.wIndex = 2;
         this.wP = 5;
      } else {
         this.wTab = 20;
         this.wIndex = 3;
         this.wP = 0;
      }

   }

   public void init() {
      isEquip = true;
      this.isClose = false;
      this.select = 0;
      PlayerInfo m = TerrainMidlet.myInfo;
      if (m == null || m.myEquip == null) {
         CRes.out("[EQUIP] Player/equipment state is not ready yet");
         this.myEquips.removeAllElements();
         return;
      }
      int gunIndex = m.gun & 255;
      if (m.equipVipID != null && gunIndex < m.equipVipID.length
              && m.equipVipID[gunIndex] != null && m.equipVipID[gunIndex].length > 1) {
         PlayerInfo.vipID = m.equipVipID[gunIndex][1];
      }
      this.getLastEquip();
      this.getDetail();
      TerrainMidlet.myInfo.getMyEquip(9);
      TerrainMidlet.myInfo.setAllEquipEffect();

      for(int i = 0; i < 5; ++i) {
         this.dbKeyChange[i] = -1;
         this.lastDb[i] = -1;
      }

      this.getMyEquip();
      if (inventory.size() == 0) {
         inventory = inventory;
      }

      this.vLastE = this.myEquips;
      this.setCurrEquip();
      this.getBaseAttribute();
      this.seeNextAttribute();
   }

   public void show(CScreen lastScreen) {
      if (this.select < 0) {
         this.select = 0;
      }

      if (this.select > this.myEquips.size() - 1) {
         this.select = this.myEquips.size() - 1;
      }

      if (this.getEquipSelect() != null) {
         this.getDetail();
      }

      super.show(lastScreen);
   }

   public Equip getEquip(int dbKey) {
      for(int i = 0; i < this.myEquips.size(); ++i) {
         Object raw = this.myEquips.elementAt(i);
         if (!(raw instanceof Equip)) continue;
         Equip e = (Equip)raw;
         if (e.dbKey == dbKey) {
            return e;
         }
      }

      return null;
   }

   public void removeEquip(int dbKey, int nDelete) {
      for(int i = 0; i < this.myEquips.size(); ++i) {
         Equip e = (Equip)this.myEquips.elementAt(i);
         if (!e.isMaterial) {
            if (e.dbKey == dbKey) {
               e.num -= nDelete;
               if (e.num <= 0) {
                  e.num = 0;
                  this.myEquips.removeElement(e);
                  this.currEq[i] = null;
               }

               return;
            }
         } else if (e.id == dbKey) {
            e.num -= nDelete;
            if (e.num <= 0) {
               e.num = 0;
               this.myEquips.removeElement(e);
            }

            return;
         }
      }

   }

   public int countCombine() {
      int a = 0;

      for(int i = 0; i < this.myEquips.size(); ++i) {
         Equip e = (Equip)this.myEquips.elementAt(i);
         if (e.isSelect) {
            ++a;
         }
      }

      return a;
   }

   public void getMyEquip() {
      this.myEquips.removeAllElements();
      PlayerInfo info = TerrainMidlet.myInfo;
      if (inventory == null || info == null || info.myEquip == null || info.myEquip.equips == null) {
         return;
      }

      for(int i = 0; i < inventory.size(); ++i) {
         Object raw = inventory.elementAt(i);
         if (!(raw instanceof Equip)) {
            continue;
         }
         Equip e = (Equip)raw;
         if (!e.isMaterial && e.glass == info.gun) {
            this.myEquips.addElement(e);
         }
      }

      byte materialId = -1;
      for(int i = 0; i < inventory.size(); ++i) {
         Object raw = inventory.elementAt(i);
         if (!(raw instanceof Equip)) {
            continue;
         }
         Equip e = (Equip)raw;
         if (e.isMaterial && materialId != (byte)e.id) {
            materialId = (byte)e.id;
            if (e.materialIcon == null) {
               if (MaterialIconMn.isExistIcon(e.icon)) {
                  e.materialIcon = MaterialIconMn.getImageFromID(e.icon);
               }
               GameService.gI().getMaterialIcon((byte)0, materialId, -1);
            }
         }
      }

      this.hIndex = this.myEquips.size() / this.wIndex;
      if (this.myEquips.size() % this.wIndex != 0) {
         ++this.hIndex;
      }

      for(int i = 0; i < this.myEquips.size(); ++i) {
         Equip tam = (Equip)this.myEquips.elementAt(i);
         int type = tam.type & 255;
         if (type < info.myEquip.equips.length && info.myEquip.equips[type] != null
                 && info.myEquip.equips[type].dbKey == tam.dbKey) {
            info.myEquip.equips[type].removeAbility();
            info.myEquip.equips[type].addAbilityFromEquip(tam);
         }
      }
   }

   public void addEquip(Equip e, boolean isNum) {
      boolean tam = true;

      for(int i = 0; i < inventory.size(); ++i) {
         Equip eq = (Equip)inventory.elementAt(i);
         if (eq.isMaterial && eq.id == e.id) {
            if (!isNum) {
               ++eq.num;
            } else {
               eq.num += e.num;
            }

            tam = false;
            break;
         }
      }

      if (tam) {
         if (MaterialIconMn.isExistIcon(e.icon)) {
            e.materialIcon = MaterialIconMn.getImageFromID(e.id);
         } else {
            GameService.gI().getMaterialIcon((byte)0, (byte)e.id, -1);
         }

         inventory.insertElementAt(e, 0);
      }

   }

   public void addMaterial(Equip e) {
      inventory.addElement(e);
   }

   public void getEquip(Vector icon) {
      inventory = new Vector();
      inventory = icon;
      this.getMyEquip();
   }

   public void getMaterialIcon(int id, byte[] dataRawImages, int len) {
      int i;
      Equip e;
      for(i = 0; i < inventory.size(); ++i) {
         e = (Equip)inventory.elementAt(i);
         if (e.isMaterial && e.id == id) {
            e.materialIcon = mImage.createImage(dataRawImages, 0, len, "EquipScrenn" + e.id, (String)null);
         }
      }

      for(i = 0; i < this.myEquips.size(); ++i) {
         e = (Equip)this.myEquips.elementAt(i);
         if (e.isMaterial && e.id == id) {
            e.materialIcon = mImage.createImage(dataRawImages, 0, len, "EquipScrenn" + e.id, (String)null);
         }
      }

   }

   public void getMaterialIcon(int id, Image img) {
      int i;
      Equip e;
      for(i = 0; i < inventory.size(); ++i) {
         e = (Equip)inventory.elementAt(i);
         if (e.isMaterial && e.id == id) {
            e.materialIcon = new mImage(img);
         }
      }

      for(i = 0; i < this.myEquips.size(); ++i) {
         e = (Equip)this.myEquips.elementAt(i);
         if (e.isMaterial && e.id == id) {
            e.materialIcon = new mImage(img);
         }
      }

   }

   public void addEquip(Equip e) {
      inventory.insertElementAt(e, 0);
      this.myEquips.insertElementAt(e, 0);
   }

   public void doClose() {
      this.isClose = true;
      CCanvas.endDlg();
      this.resetEquip();
   }

   public void doAgree() {
      CCanvas.startWaitDlg(Language.pleaseWait());
      GameService.gI().changeEquip(this.dbKeyChange);
   }

   public void doFire() {
      try {
         if (this.myEquips == null || this.myEquips.size() == 0) {
            return;
         }

         final Equip e = this.getEquipSelect();
         if (e == null || e.isMaterial) {
            return;
         }

         if (e.date == 0) {
            CCanvas.startYesNoDlg(Language.noticGiahanTrangBi(), new IAction() {
               public void perform() {
                  CCanvas.startOKDlg(Language.pleaseWait());
                  GameService.gI().get_more_day((byte)0, e.dbKey);
               }
            }, new IAction() {
               public void perform() {
                  CCanvas.endDlg();
               }
            });
            return;
         }

         PlayerInfo m = TerrainMidlet.myInfo;
         if (m == null || m.myEquip == null || m.myEquip.equips == null) {
            CRes.out("[EQUIP] Equip click ignored: player equipment state is not ready");
            return;
         }
         int gun = m.gun & 255;
         int type = e.type & 255;

         if (e.vip == 1) {
            if (e.id != PlayerInfo.vipID) {
               GameService.gI().vip_equip((byte)1, e.dbKey);
            } else {
               boolean isVip = TerrainMidlet.isVip != null && gun < TerrainMidlet.isVip.length
                       && TerrainMidlet.isVip[gun];
               e.isVip = isVip;
               GameService.gI().vip_equip((byte)(isVip ? 0 : 1), e.dbKey);
            }
            return;
         }

         if (e.level > m.level2) {
            CCanvas.startOKDlg(Language.banphaitren() + e.level + Language.moicothe());
            return;
         }

         for(int i = 0; i < m.myEquip.equips.length; ++i) {
            if (m.myEquip.equips[i] != null && e.dbKey == m.myEquip.equips[i].dbKey) {
               return;
            }
         }

         if (this.typeE != null && this.dbKeyChange != null) {
            for(int i = 0; i < this.typeE.length && i < this.dbKeyChange.length; ++i) {
               if ((e.type & 255) == (this.typeE[i] & 255)) {
                  this.dbKeyChange[i] = e.dbKey;
               }
            }
         }

         if (m.equipID == null || gun >= m.equipID.length || m.equipID[gun] == null
                 || type >= m.equipID[gun].length) {
            CRes.out("[EQUIP] Equip click ignored: missing equipID gun=" + gun + " type=" + type);
            return;
         }
         short id = m.equipID[gun][type];
         Equip currE = PlayerEquip.createEquip(m.gun, e.type, id);
         m.addChangeEquip(e, currE);
         this.changeEquip();
         this.setCurrEquip();
         this.getBaseAttribute();
      } catch (Exception ex) {
         CRes.out("[EQUIP] Equip click failed safely: " + ex);
      }

   }

   public void doDetail() {
      CCanvas.startOKDlg(this.attribute);
   }

   public Equip getEquipSelect() {
      if (this.myEquips.size() > 0) {
         if (this.select <= 0) {
            this.select = 0;
         } else if (this.select >= this.myEquips.size()) {
            this.select = this.myEquips.size() - 1;
         }

         Object raw = this.myEquips.elementAt(this.select);
         return raw instanceof Equip ? (Equip)raw : null;
      } else {
         return null;
      }
   }

   public void resetEquip() {
      try {
         PlayerInfo m = TerrainMidlet.myInfo;
         if (m == null || m.myEquip == null || m.myEquip.equips == null) return;

         for(int i = 0; i < this.lastEquip.length && i < m.myEquip.equips.length; ++i) {
            if (this.lastEquip[i] != null) {
               if (m.myEquip.equips[i] == null) m.myEquip.equips[i] = new Equip();
               m.myEquip.equips[i].changeToEquip(this.lastEquip[i]);
            }
         }

         this.myEquips = this.vLastE;
      } catch (Exception var3) {
      }

   }

   public void getLastEquip() {
      PlayerInfo m = TerrainMidlet.myInfo;
      if (m == null || m.myEquip == null || m.myEquip.equips == null) {
         for (int i = 0; i < 5; ++i) {
            this.lastDb[i] = -1;
            this.dbKeyChange[i] = -1;
            this.lastEquip[i] = null;
         }
         return;
      }
      int gun = m.gun & 255;
      for(int i = 0; i < 5; ++i) {
         Equip current = i < m.myEquip.equips.length ? m.myEquip.equips[i] : null;
         if (current != null) {
            this.lastDb[i] = (short)current.dbKey;
            if (m.equipID != null && gun < m.equipID.length && m.equipID[gun] != null && i < m.equipID[gun].length) {
               m.equipID[gun][i] = current.id;
            }
            this.lastEquip[i] = new Equip();
            this.lastEquip[i].changeToEquip(current);
         } else {
            this.lastDb[i] = -1;
            this.lastEquip[i] = null;
         }
         this.dbKeyChange[i] = this.lastDb[i];
      }
   }

   public void changeEquip() {
      PlayerInfo m = TerrainMidlet.myInfo;
      Equip sl = this.getEquipSelect();
      if (m == null || m.myEquip == null || m.myEquip.equips == null || sl == null) {
         return;
      }
      int type = sl.type & 255;
      if (type >= m.myEquip.equips.length) {
         CRes.out("[EQUIP] Invalid equipment type " + type + " for id " + sl.id);
         return;
      }
      if (m.myEquip.equips[type] == null) {
         m.myEquip.equips[type] = PlayerEquip.getEquip(sl.glass, sl.type, sl.id);
         if (m.myEquip.equips[type] == null) {
            CRes.out("[EQUIP] Missing template glass=" + sl.glass + " type=" + sl.type + " id=" + sl.id);
            return;
         }
      }

      Equip target = m.myEquip.equips[type];
      target.changeToEquip(sl);
      target.icon = sl.icon;
      target.x = sl.x;
      target.y = sl.y;
      target.dx = sl.dx;
      target.dy = sl.dy;
      target.w = sl.w;
      target.h = sl.h;
      target.bullet = sl.bullet;
      target.frame = sl.frame;
      target.addAbilityFromEquip(sl);
      target.dbKey = sl.dbKey;
   }

   public void doInventory() {
      this.isClose = true;
      CCanvas.inventory.show(CCanvas.menuScr);
   }

   public void paintEquip(mGraphics g, Image img, int X, int Y) {
      g.setColor(4156571);
      g.fillRoundRect(X - 1 - 9, Y - 1 - 9, 20, 20, 4, 4, false);
      g.setColor(16774532);
      g.fillRect(X - 1 - 8, Y - 1 - 8, 18, 18, false);
   }

   public void itemCamera() {
      if (cmyI != cmtoYI) {
         cmvyI = cmtoYI - cmyI << 2;
         cmdyI += cmvyI;
         cmyI += cmdyI >> 4;
         cmdyI &= 15;
      }

      this.nLine = this.num / this.wIndex;
      if (this.num % this.wIndex != 0) {
         ++this.nLine;
      }

      int yLim = this.nLine * this.wTab - 60;
      if (cmyI > yLim) {
         cmyI = yLim;
      }

      if (cmyI < 0) {
         cmyI = 0;
      }

   }

   public void paintItem(mGraphics g, int X, int Y) {
      g.setColor(4156571);
      g.fillRoundRect(X - 5, this.yPaint + 96, 72, 67, 6, 6, false);
      g.setClip(X - 1, Y - 1, 62, 60);
      g.translate(0, -cmyI);
      int row = 0;
      int col = 0;
      PlayerInfo m = TerrainMidlet.myInfo;

      for(int n = 0; this.myEquips != null && n < this.myEquips.size(); ++n) {
         Object raw = this.myEquips.elementAt(n);
         if (!(raw instanceof Equip)) {
            continue;
         }
         Equip e = (Equip)raw;
         int x1 = X + col * this.wTab + this.wP;
         int y1 = Y + row * this.wTab + this.wP;
         int type = e.type & 255;

         if (m != null && m.myEquip != null && m.myEquip.equips != null
                 && type < m.myEquip.equips.length && m.myEquip.equips[type] != null
                 && e.dbKey == m.myEquip.equips[type].dbKey) {
            g.setColor(4819660);
            g.fillRect(x1, y1, 16, 16, true);
         }

         if (e.vip == 1) {
            g.setColor(5361158);
            g.fillRect(x1, y1, 16, 16, true);
            int gun = m == null ? -1 : m.gun & 255;
            if (m != null && TerrainMidlet.isVip != null && gun >= 0 && gun < TerrainMidlet.isVip.length
                    && TerrainMidlet.isVip[gun] && e.id == PlayerInfo.vipID) {
               g.setColor(5963263);
               g.fillRect(x1, y1, 16, 16, true);
            }
         }

         if (e.date == 0) {
            g.setColor(9014930);
            g.fillRect(x1, y1, 16, 16, true);
         }

         if (this.select == n) {
            g.setColor(16767817);
            g.fillRect(x1 - 1, y1 - 1, 18, 18, true);
            if (!CCanvas.isTouch) {
               cmtoYI = y1 - (Y + 20);
            }
            for(int a = 0; a < this.typeE.length; ++a) {
               if (e.type == this.typeE[a]) {
                  this.ind = a;
               }
            }
         }

         if (e.isSelect) {
            g.setColor(16777215);
            g.fillRect(x1, y1, 16, 16, true);
         }
         e.drawIcon(g, x1, y1, true);
         if (!e.isMaterial) {
            int freeSlots = Math.max(0, Math.min(3, 3 - e.slot));
            for(int a = 0; a < freeSlots; ++a) {
               g.setColor(this.select == n ? 0 : 16377901);
               g.fillRect(x1 + a * 4, y1, 2, 2, true);
            }
         }

         ++col;
         if (col == this.wIndex) {
            ++row;
            col = 0;
         }
      }
      g.translate(0, -g.getTranslateY());
   }

   public void getBaseAttribute() {
      PlayerInfo info = TerrainMidlet.myInfo;
      for (int i = 0; i < this.atts.length; ++i) this.atts[i] = 0;
      if (info == null || info.ability == null || info.myEquip == null || info.myEquip.equips == null) {
         return;
      }
      int[] ability = new int[5];
      int[] percen = new int[5];
      Equip vip = null;
      int gun = info.gun & 255;
      if (TerrainMidlet.isVip != null && gun < TerrainMidlet.isVip.length && TerrainMidlet.isVip[gun]) {
         for(int j = 0; this.myEquips != null && j < this.myEquips.size(); ++j) {
            Object raw = this.myEquips.elementAt(j);
            if (raw instanceof Equip && ((Equip)raw).id == PlayerInfo.vipID) {
               vip = (Equip)raw;
               break;
            }
         }
      }

      for(int j = 0; j < Math.min(5, info.myEquip.equips.length); ++j) {
         Equip eq = info.myEquip.equips[j];
         if (eq == null) continue;
         for(int k = 0; k < 5; ++k) {
            if (eq.inv_ability != null && k < eq.inv_ability.length) ability[k] += eq.inv_ability[k];
            if (eq.inv_percen != null && k < eq.inv_percen.length) percen[k] += eq.inv_percen[k];
         }
      }
      if (vip != null) {
         for(int j = 0; j < 5; ++j) {
            if (vip.inv_ability != null && j < vip.inv_ability.length) ability[j] += vip.inv_ability[j];
            if (vip.inv_percen != null && j < vip.inv_percen.length) percen[j] += vip.inv_percen[j];
         }
      }

      int base0 = info.ability.length > 0 ? info.ability[0] : 0;
      this.atts[0] = 1000 + base0 * 10 + ability[0] * 10;
      this.atts[0] += (1000 + base0) * percen[0] / 100;
      Equipment.EquipGlass currentGlass = PlayerEquip.getEquipGlass(info.gun);
      int maxDamage = currentGlass == null ? 0 : currentGlass.maxDamage;
      int damPoint = ability[1] + (info.ability.length > 1 ? info.ability[1] : 0);
      int defPoint = ability[2] + (info.ability.length > 2 ? info.ability[2] : 0);
      int luckPoint = ability[3] + (info.ability.length > 3 ? info.ability[3] : 0);
      int teamPoint = ability[4] + (info.ability.length > 4 ? info.ability[4] : 0);
      this.atts[1] = maxDamage * (damPoint / 3 + 100 + percen[1]) / 100;
      this.atts[2] = defPoint * 10;
      this.atts[2] += this.atts[2] * percen[2] / 100;
      this.atts[3] = luckPoint * 10;
      this.atts[3] += this.atts[3] * percen[3] / 100;
      this.atts[4] = teamPoint * 10;
      this.atts[4] += this.atts[4] * percen[4] / 100;
   }

   public void paintAbility(mGraphics g) {
      PlayerInfo info = TerrainMidlet.myInfo;
      if (info == null) {
         return;
      }
      Font.normalFont.drawString(g, "Level: " + info.level2, this.W / 2 + 24, this.yPaint + 22, 3);
      Font.normalFont.drawString(g, "%", this.W / 2 + 75, this.yPaint + 22, 3);

      for(int i = 0; i < 5; ++i) {
         if (LevelScreen.ability != null && LevelScreen.ability.image != null
                 && LevelScreen.ability.image.getHeight() >= (i + 1) * 16) {
            g.drawRegion(LevelScreen.ability, 0, i * 16, 16, 16, 0,
                    this.W / 2 - 1, this.yPaint + 46 + i * 18, 3, false);
         }
         g.setColor(2378093);
         g.fillRect(CCanvas.width / 2 + 9, this.yPaint + 38 + i * 18, 35, 16, false);
         g.fillRect(CCanvas.width / 2 + 46, this.yPaint + 38 + i * 18, 18, 16, false);
         g.fillRect(CCanvas.width / 2 + 66, this.yPaint + 38 + i * 18, 19, 16, false);
         int attDelta = info.attAddPoint1 != null && i < info.attAddPoint1.length ? info.attAddPoint1[i] : 0;
         int perDelta = info.attAddPoint2 != null && i < info.attAddPoint2.length ? info.attAddPoint2[i] : 0;
         byte dir1 = info.UpOrDown1 != null && i < info.UpOrDown1.length ? info.UpOrDown1[i] : 0;
         byte dir2 = info.UpOrDown2 != null && i < info.UpOrDown2.length ? info.UpOrDown2[i] : 0;
         Font.normalYFont.drawString(g, String.valueOf(this.atts[i]), this.W / 2 + 26, this.yPaint + 39 + i * 18, 3);
         drawDelta(g, Math.abs(attDelta), dir1, this.W / 2 + 56, this.yPaint + 39 + i * 18);
         drawDelta(g, Math.abs(perDelta), dir2, this.W / 2 + 75, this.yPaint + 39 + i * 18);
      }
   }

   private static void drawDelta(mGraphics g, int value, byte direction, int x, int y) {
      if (direction == 2) {
         Font.normalRFont.drawString(g, String.valueOf(value), x, y, 3);
      } else if (direction == 1) {
         Font.normalGFont.drawString(g, String.valueOf(value), x, y, 3);
      } else {
         Font.normalYFont.drawString(g, String.valueOf(value), x, y, 3);
      }
   }

   public void getDetail() {
      this.dx = -1;
      this.ds = 0;
      this.scroll = false;
      this.attribute = "";
      Equip eq = this.getEquipSelect();
      if (eq == null) {
         this.name = "";
         this.wName = 0;
         this.wDetail = 0;
         return;
      }
      this.xName = this.W / 2 - 4;
      this.name = eq.name == null || eq.name.trim().isEmpty() ? "Trang bị #" + eq.id : eq.name;
      this.wName = Font.normalFont.getWidth(this.name);
      if (eq.isMaterial) {
         this.attribute = eq.strDetail == null ? "" : eq.strDetail;
      } else {
         try {
            this.attribute = eq.getStrInvDetail();
         } catch (RuntimeException detailError) {
            CRes.out("[EQUIP] Bad inventory detail id=" + eq.id + ": " + detailError);
            this.attribute = "";
         }
      }
      if (this.attribute == null) this.attribute = "";
      this.wDetail = Font.normalFont.getWidth(this.name);
   }

   public Position transTextLimit(Position pos, int limit) {
      pos.x += pos.y;
      if (pos.y == -1 && Math.abs(pos.x) > limit) {
         pos.y *= -1;
      }

      if (pos.y == 1 && pos.x > 5) {
         pos.y *= -1;
      }

      return pos;
   }

   public void paintMoney(mGraphics g) {
      PlayerInfo m = TerrainMidlet.myInfo;
      if (m == null) return;
      g.setColor(1521982);
      g.setClip(this.W / 2 - 9, this.yPaint + 130, 95, 60);
      g.fillRoundRect(this.W / 2 - 9, this.yPaint + 130, 95, 16, 6, 6, false);
      g.fillRoundRect(this.W / 2 - 9, this.yPaint + 148, 95, 16, 6, 6, false);
      String money = CRes.getMoneys(m.xu) + Language.xu() + "-" + m.luong + Language.luong2();
      Font.normalYFont.drawString(g, money, this.xName + this.cc, this.yPaint + 131, 0);
      Font.normalGFont.drawString(g, this.name == null ? "" : this.name,
              this.W / 2 - 4 + this.dx + this.ee, this.yPaint + 149, 0);
      g.setClip(0, 0, CCanvas.width, CCanvas.hieght);
   }

   public void setCurrEquip() {
      PlayerInfo info = TerrainMidlet.myInfo;
      if (info == null || info.myEquip == null || info.myEquip.equips == null) {
         for (int i = 0; i < this.currEq.length; ++i) {
            this.currEq[i] = null;
         }
         return;
      }
      for(int i = 0; i < 5; ++i) {
         short id = -1;
         if (i < info.myEquip.equips.length && info.myEquip.equips[i] != null) {
            id = info.myEquip.equips[i].id;
         }
         this.currEq[i] = PlayerEquip.getEquip(info.gun, (byte)i, id);
      }
   }

   public void paintPlayer(mGraphics g) {
      int selectedSlot = Math.max(0, Math.min(this.xE.length - 1, this.ind));
      g.setColor(16767817);
      g.drawRect(this.xE[selectedSlot] - 9, this.yE[selectedSlot] - 9, 17, 17, false);
      g.drawRect(this.xE[selectedSlot] - 10, this.yE[selectedSlot] - 10, 19, 19, false);
      g.setColor(1521982);
      g.drawRect(this.xE[selectedSlot] - 11, this.yE[selectedSlot] - 11, 21, 21, false);

      for(int i = 0; i < 5 && i < this.currEq.length; ++i) {
         if (GameScr.s_imgITEM != null && GameScr.s_imgITEM.image != null) {
            this.paintEquip(g, GameScr.s_imgITEM.image, this.xE[i], this.yE[i]);
         }
         if (this.currEq[i] != null) {
            this.currEq[i].drawIcon(g, this.xE[i] - 8, this.yE[i] - 8, true);
         } else if (GameScr.s_imgITEM != null && GameScr.s_imgITEM.image != null) {
            g.drawRegion(GameScr.s_imgITEM, 0, 0, 16, 16, 0, this.xE[i], this.yE[i], 3, true);
         }
      }

      PlayerInfo myInfo = TerrainMidlet.myInfo;
      if (myInfo == null) return;
      int gun = myInfo.gun;
      int gunIndex = myInfo.gun & 255;
      if (TerrainMidlet.isVip != null && gunIndex < TerrainMidlet.isVip.length && TerrainMidlet.isVip[gunIndex]
              && myInfo.myVipEquip != null) {
         this.equip = myInfo.myVipEquip;
      } else {
         this.equip = myInfo.myEquip;
      }
      if (this.equip != null) {
         CPlayer.paintSimplePlayer(gun, CCanvas.gameTick % 5 > 2 ? 5 : 4,
                 CCanvas.width / 2 - 52, this.yPaint + 71, 0, this.equip, g);
      }
   }

   public void paint(mGraphics g) {
      super.paint(g);
      this.paintPlayer(g);
      this.paintItem(g, this.W / 2 - 78, this.yPaint + 102);
      this.paintMoney(g);
      this.paintAbility(g);
      this.paintSuper(g);
   }

   public void seeNextAttribute() {
      if (this.myEquips == null || this.myEquips.isEmpty()) return;
      PlayerInfo m = TerrainMidlet.myInfo;
      Equip e = this.getEquipSelect();
      if (m == null || e == null || m.myEquip == null || m.myEquip.equips == null) return;
      int type = e.type & 255;
      Equip currE = type < m.myEquip.equips.length ? m.myEquip.equips[type] : null;
      if (e.isMaterial || this.isCompine || e.vip == 1) currE = e;
      try {
         m.compareEquip(e, currE);
      } catch (RuntimeException compareError) {
         CRes.out("[EQUIP] Compare failed id=" + e.id + ": " + compareError);
      }
      this.getDetail();
      this.transText2.x = -1;
   }

   public void update() {
      super.update();
      this.num = this.myEquips == null ? 0 : this.myEquips.size();
      this.center = this.num > 0 ? this.cmdSelect : null;
      this.left = this.menu;
      this.itemCamera();
      PlayerInfo m = TerrainMidlet.myInfo;
      if (m == null) return;
      int gun = m.gun & 255;
      if (m.equipVipID != null && gun < m.equipVipID.length && m.equipVipID[gun] != null && m.equipVipID[gun].length > 1) {
         PlayerInfo.vipID = m.equipVipID[gun][1];
      }
      String money = CRes.getMoneys(m.xu) + Language.xu() + "-" + m.luong + Language.luong2();
      int bb = Font.normalFont.getWidth(money);
      if (bb > 85) this.transTextLimit(this.transText1, bb - 80);
      this.cc = this.transText1.x;
      String safeName = this.name == null ? "" : this.name;
      int dd = Font.normalFont.getWidth(safeName);
      if (dd > 85) this.transTextLimit(this.transText2, dd - 80);
      this.ee = this.transText2.x;
   }

   public void onPointerDragged(int xDrag, int yDrag, int index) {
      super.onPointerDragged(xDrag, yDrag, index);
      if (!this.trans) {
         this.pa = cmyI;
         this.trans = true;
      }

      if (CCanvas.isPc()) {
         this.speed = 3;
      }

      cmtoYI = this.pa + (CCanvas.pyFirst[index] - yDrag) * this.speed;
      this.cmtoYITem = this.pa + (CCanvas.pyFirst[index] - yDrag);
      if (cmtoYI <= 0) {
         cmtoYI = 0;
         this.cmtoYITem = 0;
      }

   }

   public void onPointerReleased(int xReleased, int yReleased, int index) {
      super.onPointerReleased(xReleased, yReleased, index);
      this.trans = false;
      int xDraw = this.W / 2 - 86;
      int yDraw = this.yPaint + 96;
      if (CCanvas.isPointer(xDraw, yDraw + this.wP, 72, 87, index)) {
         int aa = (this.cmtoYITem + yReleased - yDraw - this.wP) / this.wTab * this.wIndex + (xReleased - xDraw - this.wP) / this.wTab;
         if (aa == this.select && this.center != null && CCanvas.isDoubleClick) {
            this.center.action.perform();
         }

         this.select = aa;
         this.getDetail();
         if (this.select < 0) {
            this.select = 0;
         }

         if (this.select > this.myEquips.size() - 1) {
            this.select = this.myEquips.size() - 1;
         }
      }

      this.seeNextAttribute();
   }

   public void onPointerPressed(int xPress, int yPress, int index) {
      super.onPointerPressed(xPress, yPress, index);
      int xDraw = this.W / 2 - 78;
      int yDraw = this.yPaint + 102;
      if (CCanvas.isPointer(xDraw, yDraw, 72, 87, index)) {
         int aa = (this.cmtoYITem + yPress - yDraw) / this.wTab * this.wIndex + (xPress - xDraw - this.wP) / this.wTab;
         this.select = aa;
         this.getDetail();
         if (this.select < 0) {
            this.select = 0;
         }

         if (this.select > this.myEquips.size() - 1) {
            this.select = this.myEquips.size() - 1;
         }
      }

   }
}
