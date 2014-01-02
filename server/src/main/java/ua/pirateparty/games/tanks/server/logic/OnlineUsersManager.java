package ua.pirateparty.games.tanks.server.logic;

import ua.pirateparty.games.tanks.server.entities.game.Field;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.util.HashMap;
import java.util.Map;

public class OnlineUsersManager {

    public static Map<Integer, Player> connected = new HashMap<>();
    public static Map<Long, Player> authorized = new HashMap<>();
    public static Map<Long, Player> zombie = new HashMap<>();
    public static Map<Integer, Field> fields = new HashMap<>();
}
