package model;

import CLib.mGraphics;
import coreLG.CCanvas;
import java.util.Vector;
import network.Command;
import screen.CScreen;

public class PauseMenu {
   public boolean isShow;
   public Vector menuItems;
   public int menuSelectedItem;
   public int menuX;
   public int menuY;
   public int menuW;
   public int menuH;
   public int menuTemY;

   public void startAt(Vector menuItems) {
      int dis = CCanvas.isTouch ? 30 : 24;
      this.menuItems = menuItems;
      this.menuW = 0;
      this.menuH = 0;

      for(int i = 0; i < menuItems.size(); ++i) {
         Command c = (Command)menuItems.elementAt(i);
         int w = Font.bigFont.getWidth(c.caption);
         if (w > this.menuW) {
            this.menuW = w;
         }

         this.menuH += dis;
      }

      this.menuW += 10;
      if (this.menuW < 100) {
         this.menuW = 100;
      }

      this.menuH += 4;
      this.menuX = (CCanvas.width >> 1) - (this.menuW >> 1);
      this.menuY = CCanvas.hieght - this.menuH >> 1;
      this.menuTemY = CCanvas.hieght - 24;
      this.isShow = true;
      this.menuSelectedItem = 0;
   }

   public void onPointerPressed(int xScreen, int yScreen, int index) {
      CCanvas.isPointer(this.menuX, this.menuY, this.menuW, this.menuH, index);
      CScreen.clearKey();
   }

   public void onPointerDrag(int xScreen, int yScreen, int index) {
   }

   public void onPointerRealeased(int xRealeased, int yRealeased, int index) {
      if (CCanvas.isPointer(this.menuX, this.menuY, this.menuW, this.menuH, index)) {
         int aa = (yRealeased - this.menuY) / 30;
         if (aa >= 0 && aa < this.menuItems.size()) {
            if (this.menuSelectedItem != aa) {
               this.menuSelectedItem = aa;
            } else {
               this.isShow = false;
               ((Command)this.menuItems.elementAt(this.menuSelectedItem)).action.perform();
            }
         }
      }

   }

   public void paint(mGraphics g) {
      if (CCanvas.isDebugging()) {
         g.setColor(2263535);
         g.fillRect(this.menuX, this.menuY, this.menuW, this.menuH, false);
      }

      g.setClip(0, 0, CCanvas.width, CCanvas.hieght);
      CScreen.paintBorderRect(g, this.menuY - 25, 4, this.menuH + 25, "");
      g.translate(-g.getTranslateX(), -g.getTranslateY());
      int dis = CCanvas.isTouch ? 30 : 24;

      for(int i = 0; i < this.menuItems.size(); ++i) {
         g.setColor(0);
         if (i == this.menuSelectedItem) {
            g.setColor(16767817);
            g.fillRect(CCanvas.width / 2 - 85, this.menuY + i * dis - 1, 170, 24, false);
         }

         Font.bigFont.drawString(g, ((Command)this.menuItems.elementAt(i)).caption, CCanvas.hw, this.menuY + i * dis, 2);
      }

   }

   public void handleKeyboardInput() {
      if (!this.isShow || this.menuItems == null || this.menuItems.size() == 0) {
         return;
      }

      if (CCanvas.keyPressed[2]) {
         CCanvas.keyPressed[2] = false;
         this.menuSelectedItem--;
         if (this.menuSelectedItem < 0) {
            this.menuSelectedItem = this.menuItems.size() - 1;
         }
      }
      if (CCanvas.keyPressed[8]) {
         CCanvas.keyPressed[8] = false;
         this.menuSelectedItem++;
         if (this.menuSelectedItem >= this.menuItems.size()) {
            this.menuSelectedItem = 0;
         }
      }
      if (CCanvas.keyPressed[13]) {
         CCanvas.keyPressed[13] = false;
         this.isShow = false;
         return;
      }
      if (CCanvas.keyPressed[5] || CCanvas.keyPressed[12]) {
         CCanvas.keyPressed[5] = false;
         CCanvas.keyPressed[12] = false;
         Command selectedCommand = (Command)this.menuItems.elementAt(this.menuSelectedItem);
         this.isShow = false;
         if (selectedCommand != null && selectedCommand.action != null) {
            selectedCommand.action.perform();
         }
      }
   }

   public void update() {
      if (this.menuTemY > this.menuY) {
         int delta = this.menuTemY - this.menuY >> 1;
         if (delta < 1) {
            delta = 1;
         }

         this.menuTemY -= delta;
      }

      this.menuTemY = this.menuY;
   }
}
