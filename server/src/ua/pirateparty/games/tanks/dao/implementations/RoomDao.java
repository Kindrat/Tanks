package ua.pirateparty.games.tanks.dao.implementations;

import ua.pirateparty.games.tanks.dao.interfaces.IRoomDao;
import ua.pirateparty.games.tanks.server.entities.lobby.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.pirateparty.games.tanks.dao.db.ISqlConst.*;
import static ua.pirateparty.games.tanks.dao.db.PgConnectionFactory.closeConnection;
import static ua.pirateparty.games.tanks.util.log.Loggers.dbLogger;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 15.12.12
 * Time: 23:49
 */
public class RoomDao implements IRoomDao {
    @Override
    public List<Integer> getRoomIds() {
        List<Integer> ids = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(FACTORY.getTables().get("roomIds"));
        query.append(WHERE).append("active=true").append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ids.add(rs.getInt("id"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            dbLogger.error(e);
        }finally {
            closeConnection(connection);
            dbLogger.debug(query.toString());
        }

        return ids;
    }

    @Override
    public Map<Integer, Room> getRoomParams(List<Integer> ids) {
        Map<Integer, Room> rooms = new HashMap<>();
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(FACTORY.getTables().get("roomParams")).append(SEMICOLON);

        Room room;
        byte fieldType;
        int id, fields;
        String name;
        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                id=rs.getInt("id");
                if (ids.contains(id)){
                    name=rs.getString("name");
                    fields=rs.getInt("fields");
                    fieldType=rs.getByte("type");
                    room = new Room(id, name, fields, fieldType);
                    rooms.put(id, room);
                }
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            dbLogger.error(e);
        }finally {
            closeConnection(connection);
            dbLogger.debug(query.toString());
        }

        return rooms;
    }
}
