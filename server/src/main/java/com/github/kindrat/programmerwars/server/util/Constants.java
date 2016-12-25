package com.github.kindrat.programmerwars.server.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final int DEFAULT_TANK_ID = 1;
    public static final int DEFAULT_ROOM_SIZE = 20;

    public interface Direction {
        int N = 1;
        int S = 2;
        int E = 3;
        int W = 4;
        int NE = 13;
        int NW = 14;
        int SE = 23;
        int SW = 24;
    }

    public interface Fields {
        byte SMALL = 1;
        byte MEDIUM = 2;
        byte LARGE = 3;
    }
}
