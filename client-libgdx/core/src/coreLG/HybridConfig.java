package coreLG;

/**
 * Desktop/private-server endpoint configuration.
 *
 * Defaults target the local Hybrid server. Override without rebuilding with:
 *   -Darmy2.host=10.0.0.20 -Darmy2.port=8122 -Darmy2.webPort=8080
 * or environment variables ARMY2_HOST / ARMY2_PORT / ARMY2_WEB_PORT.
 */
public final class HybridConfig {
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final String LAN_HOST = "10.119.209.217";
    private static final String LAN_NAME = "Trái Đất";
    private static final int DEFAULT_GAME_PORT = 8122;
    private static final int DEFAULT_WEB_PORT = 8080;

    private HybridConfig() {
    }

    public static String host() {
        String value = firstNonBlank(System.getProperty("army2.host"), System.getenv("ARMY2_HOST"), DEFAULT_HOST);
        value = value.trim();
        if (value.indexOf(':') >= 0) {
            throw new IllegalArgumentException("ARMY2 host must be IPv4/DNS without ':' for the legacy server-list format: " + value);
        }
        return value;
    }

    public static int gamePort() {
        return parsePort(firstNonBlank(System.getProperty("army2.port"), System.getenv("ARMY2_PORT"), String.valueOf(DEFAULT_GAME_PORT)), DEFAULT_GAME_PORT);
    }

    public static int webPort() {
        return parsePort(firstNonBlank(System.getProperty("army2.webPort"), System.getenv("ARMY2_WEB_PORT"), String.valueOf(DEFAULT_WEB_PORT)), DEFAULT_WEB_PORT);
    }

    public static String serverName() {
        return firstNonBlank(System.getProperty("army2.serverName"), System.getenv("ARMY2_SERVER_NAME"), "Localhost").trim();
    }

    public static String serverListEntry() {
        return serverName() + ":" + host() + ":" + gamePort();
    }

    /**
     * Server picker used by the desktop client.
     * Keep Localhost first, then the user's LAN/VPN server.
     * A custom -Darmy2.host/ARMY2_HOST endpoint is appended when it is different.
     */
    public static String serverListEntries() {
        String local = "Localhost:" + DEFAULT_HOST + ":" + DEFAULT_GAME_PORT;
        String lan = LAN_NAME + ":" + LAN_HOST + ":" + DEFAULT_GAME_PORT;
        String configuredHost = host();
        int configuredPort = gamePort();
        if ((configuredHost.equals(DEFAULT_HOST) && configuredPort == DEFAULT_GAME_PORT)
                || (configuredHost.equals(LAN_HOST) && configuredPort == DEFAULT_GAME_PORT)) {
            return local + "," + lan;
        }
        return local + "," + lan + "," + serverName() + ":" + configuredHost + ":" + configuredPort;
    }

    public static String lanHost() {
        return LAN_HOST;
    }

    public static String lanName() {
        return LAN_NAME;
    }

    public static String serverListUrl() {
        return "http://" + host() + ":" + webPort() + "/srvip/army2list.txt";
    }

    public static String registrationUrl() {
        return webRootUrl() + "/register";
    }

    public static String registrationUrlFor(String selectedHost) {
        String value = selectedHost == null ? host() : selectedHost.trim();
        if (value.isEmpty() || value.indexOf(':') >= 0) {
            value = host();
        }
        return "http://" + value + ":" + webPort() + "/register";
    }

    public static String webRootUrl() {
        return "http://" + host() + ":" + webPort();
    }

    private static int parsePort(String raw, int fallback) {
        try {
            int value = Integer.parseInt(raw.trim());
            if (value < 1 || value > 65535) {
                throw new IllegalArgumentException("Port out of range: " + value);
            }
            return value;
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }

    private static String firstNonBlank(String first, String second, String fallback) {
        if (first != null && !first.trim().isEmpty()) {
            return first;
        }
        if (second != null && !second.trim().isEmpty()) {
            return second;
        }
        return fallback;
    }
}
