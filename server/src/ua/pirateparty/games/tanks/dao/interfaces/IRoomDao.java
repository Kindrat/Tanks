package ua.pirateparty.games.tanks.dao.interfaces;

import ua.pirateparty.games.tanks.server.entities.lobby.Room;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 15.12.12
 * Time: 23:48
 */
public interface IRoomDao {
    public abstract List<Integer> getRoomIds();

    public abstract Map<Integer, Room> getRoomParams(List<Integer> ids);
}
