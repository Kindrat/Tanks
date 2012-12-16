package ua.pirateparty.games.tanks.server.entities.lobby;

import ua.pirateparty.games.tanks.dao.implementations.RoomDao;
import ua.pirateparty.games.tanks.dao.interfaces.IRoomDao;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 15.12.12
 * Time: 21:56
 */
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
        IRoomDao roomDao = new RoomDao();
        roomIds = roomDao.getRoomIds();
        rooms = roomDao.getRoomParams(roomIds);
    }
}
