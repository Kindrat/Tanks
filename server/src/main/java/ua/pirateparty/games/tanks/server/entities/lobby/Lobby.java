package ua.pirateparty.games.tanks.server.entities.lobby;

import java.util.List;
import java.util.Map;

public class Lobby {

    private static final Lobby INSTANCE = new Lobby();

    private List<Integer> roomIds;
    private Map<Integer, Room> rooms;

    private Lobby(){
        initRooms();
    }

    public static Lobby getInstance(){
        return INSTANCE;
    }

    public List<Integer> getRoomIds() {
        return roomIds;
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    private void initRooms() {
    }
}
