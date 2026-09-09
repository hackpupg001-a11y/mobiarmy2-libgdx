package mod;

import java.util.prefs.Preferences;

/**
 * Desktop QoL/V10 settings.  The V10 fields mirror the settings stored by the
 * original Army2 2.3.0 V10 client (bf/bg/bh/bi/bj plus O/P power limits).
 * They are local presentation/input settings and do not change packet layout.
 */
public final class ModSettings {
    private static final Preferences PREFS = Preferences.userNodeForPackage(ModSettings.class);

    public static volatile boolean angle360 = PREFS.getBoolean("angle360", true);
    public static volatile boolean showAimHud = PREFS.getBoolean("showAimHud", true);
    public static volatile boolean autoClick = PREFS.getBoolean("autoClick", false);
    public static volatile boolean showOverlay = PREFS.getBoolean("showOverlay", true);

    // Army2 2.3.0 V10 menu defaults recovered from the original V10 JAR.
    public static volatile boolean drawHp = PREFS.getBoolean("v10.drawHp", true);
    public static volatile boolean drawAimGuide = PREFS.getBoolean("v10.drawAimGuide", true);
    public static volatile boolean receiveBigSpeaker = PREFS.getBoolean("v10.receiveBigSpeaker", true);
    public static volatile int aimFrameMax = clamp(PREFS.getInt("v10.aimFrameMax", -1), -1, 500);
    public static volatile int aimFrameStep = clamp(PREFS.getInt("v10.aimFrameStep", 1), 1, 100);
    public static volatile int maxForce = clamp(PREFS.getInt("v10.maxForce", 30), 2, 100);
    public static volatile int secondMaxForce = clamp(PREFS.getInt("v10.secondMaxForce", 30), 2, 100);

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

    public static void toggleDrawHp() {
        drawHp = !drawHp;
        PREFS.putBoolean("v10.drawHp", drawHp);
    }

    public static void toggleDrawAimGuide() {
        drawAimGuide = !drawAimGuide;
        PREFS.putBoolean("v10.drawAimGuide", drawAimGuide);
    }

    public static void toggleReceiveBigSpeaker() {
        receiveBigSpeaker = !receiveBigSpeaker;
        PREFS.putBoolean("v10.receiveBigSpeaker", receiveBigSpeaker);
    }

    public static void setAimFrameMax(int value) {
        aimFrameMax = clamp(value, -1, 500);
        PREFS.putInt("v10.aimFrameMax", aimFrameMax);
    }

    public static void setAimFrameStep(int value) {
        aimFrameStep = clamp(value, 1, 100);
        PREFS.putInt("v10.aimFrameStep", aimFrameStep);
    }

    public static void setMaxForce(int value) {
        maxForce = clamp(value, 2, 100);
        PREFS.putInt("v10.maxForce", maxForce);
    }

    public static void setSecondMaxForce(int value) {
        secondMaxForce = clamp(value, 2, 100);
        PREFS.putInt("v10.secondMaxForce", secondMaxForce);
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
                + "  CĂN:" + onOff(drawAimGuide)
                + "  HP:" + onOff(drawHp)
                + "  AUTO:" + onOff(autoClick);
    }

    public static String onOffVi(boolean value) {
        return value ? "TẮT" : "BẬT";
    }

    private static String onOff(boolean value) {
        return value ? "ON" : "OFF";
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
