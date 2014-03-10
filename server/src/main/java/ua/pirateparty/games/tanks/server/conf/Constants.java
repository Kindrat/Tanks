package ua.pirateparty.games.tanks.server.conf;

public class Constants {

    public static final int DEFAULT_TANK_ID = 1;
    public static final int DEFAULT_ROOM_SIZE = 20;

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

    public static interface fields{
        public static final byte SMALL = 1;
        public static final byte MEDIUM = 2;
        public static final byte LARGE = 3;
    }
}
