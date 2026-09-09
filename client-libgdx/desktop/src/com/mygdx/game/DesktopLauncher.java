package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.teamobi.mobiarmy2.MainGame;
import coreLG.HybridConfig;

public final class DesktopLauncher {
    private DesktopLauncher() {
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("[CLIENT] Data Viewer");
        System.out.println("[CLIENT] Endpoint " + HybridConfig.host() + ":" + HybridConfig.gamePort());
        System.out.println("[CLIENT] Controls WASD=D-pad Q/E=softkeys SPACE=OK/fire (hold supported)");
        System.out.println("[CLIENT] Mod hotkeys F6=360 F7=AimHUD F8=AutoClick F9=Overlay");

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setIdleFPS(30);
        config.setWindowedMode(960, 540);
        config.setResizable(false);
        config.setWindowIcon("res/icon.png");
        config.setTitle("Data Viewer");
        new Lwjgl3Application(new MainGame(), config);
    }
}
