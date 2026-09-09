package mod;

import java.util.prefs.Preferences;

/**
 * Small desktop QoL/mod layer inspired by the V10 client.
 * Settings are local-only and never alter the network protocol.
 */
public final class ModSettings {
    private static final Preferences PREFS = Preferences.userNodeForPackage(ModSettings.class);

    public static volatile boolean angle360 = PREFS.getBoolean("angle360", true);
    public static volatile boolean showAimHud = PREFS.getBoolean("showAimHud", true);
    public static volatile boolean autoClick = PREFS.getBoolean("autoClick", false);
    public static volatile boolean showOverlay = PREFS.getBoolean("showOverlay", true);
    public static volatile int autoClickIntervalMs = clamp(PREFS.getInt("autoClickIntervalMs", 200), 30, 5000);
    public static volatile int autoClickKey = PREFS.getInt("autoClickKey", 10);

    private ModSettings() {
    }

    public static void toggleAngle360() {
        angle360 = !angle360;
        PREFS.putBoolean("angle360", angle360);
    }

    public static void toggleAimHud() {
        showAimHud = !showAimHud;
        PREFS.putBoolean("showAimHud", showAimHud);
    }

    public static void toggleAutoClick() {
        autoClick = !autoClick;
        PREFS.putBoolean("autoClick", autoClick);
    }

    public static void toggleOverlay() {
        showOverlay = !showOverlay;
        PREFS.putBoolean("showOverlay", showOverlay);
    }

    public static void setAutoClickIntervalMs(int value) {
        autoClickIntervalMs = clamp(value, 30, 5000);
        PREFS.putInt("autoClickIntervalMs", autoClickIntervalMs);
    }

    public static int normalize360(int angle) {
        return Math.floorMod(angle, 360);
    }

    public static String shortStatus() {
        return "360:" + onOff(angle360)
                + "  HUD:" + onOff(showAimHud)
                + "  AUTO:" + onOff(autoClick);
    }

    private static String onOff(boolean value) {
        return value ? "ON" : "OFF";
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
