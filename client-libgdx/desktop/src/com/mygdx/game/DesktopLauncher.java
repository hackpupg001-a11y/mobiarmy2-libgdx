package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.teamobi.mobiarmy2.MainGame;
import coreLG.HybridConfig;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class DesktopLauncher {
    private DesktopLauncher() {
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("[CLIENT] Data Viewer");
        System.out.println("[CLIENT] Endpoint " + HybridConfig.host() + ":" + HybridConfig.gamePort());
        System.out.println("[CLIENT] Controls WASD=D-pad Q/E=softkeys SPACE=OK/fire (hold supported)");
        System.out.println("[CLIENT] Mod hotkeys F6=360 F7=AimHUD F8=AutoClick F9=Overlay");
        System.out.println("[CLIENT] Window " + HybridConfig.windowWidth() + "x" + HybridConfig.windowHeight() + " (resizable)");

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setIdleFPS(30);
        // A minimized LWJGL window can transiently report a 0x0 framebuffer.
        // Pause rendering while minimized, but do NOT pause merely on Alt+Tab.
        // MainGame.pause()/resume() keep the desktop session state intact.
        config.setPauseWhenMinimized(true);
        config.setPauseWhenLostFocus(false);
        config.setWindowedMode(HybridConfig.windowWidth(), HybridConfig.windowHeight());
        config.setResizable(true);
        config.setWindowIcon("res/icon.png");
        config.setTitle("Data Viewer");
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> writeCrashLog(throwable));
        try {
            new Lwjgl3Application(new MainGame(), config);
        } catch (RuntimeException | Error throwable) {
            writeCrashLog(throwable);
            throw throwable;
        }
    }

    private static void writeCrashLog(Throwable throwable) {
        try {
            StringWriter buffer = new StringWriter();
            throwable.printStackTrace(new PrintWriter(buffer));
            Path log = Path.of(System.getProperty("user.home"), "DataViewer-crash.log");
            Files.writeString(log, buffer.toString(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception ignored) {
            // A crash logger must never hide the original exception.
        }
    }
}
