package ua.pirateparty.games.tanks.server.conf;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 17:55
 */
public class Constants {
    public static final boolean DEBUG = true;

    public static final String SERVER_VERSION = "0.0.1";
    public static final String STATUS_MESSAGE = "OK";
    public static final String EXTERNAL_CONFIG_FILE = "config.xml";

    public static final int DEFAULT_TANK_ID = 1;

    public static interface direction{
        public static final int N = 1;
        public static final int S = 2;
        public static final int E = 3;
        public static final int W = 4;
        public static final int NE = 13;
        public static final int NW = 14;
        public static final int SE = 23;
        public static final int SW = 24;
    }
}
