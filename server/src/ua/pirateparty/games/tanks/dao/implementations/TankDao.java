package ua.pirateparty.games.tanks.dao.implementations;

import ua.pirateparty.games.tanks.dao.interfaces.ITankDao;
import ua.pirateparty.games.tanks.server.entities.game.Activity;
import ua.pirateparty.games.tanks.server.entities.game.Tank;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.sql.*;
import java.util.ArrayList;

import static ua.pirateparty.games.tanks.dao.db.ISqlConst.*;
import static ua.pirateparty.games.tanks.dao.db.PgConnectionFactory.closeConnection;
import static ua.pirateparty.games.tanks.util.log.Loggers.dbLogger;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:51
 */

public class TankDao implements ITankDao{
    private final String DEFAULT_TABLE = "const.tanks";
    private final String TABLE = "public.tanks";
    @Override
    public Tank readDefault(int type) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(DEFAULT_TABLE).append(WHERE);
        query.append("id=").append(type).append(SEMICOLON);

        Tank tank = new Tank();
        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                tank.setId(type);
                tank.setHp(rs.getInt("hp"));
                tank.setName(rs.getString("name"));
                tank.setMinAttackStrength(rs.getDouble("minAttackStrength"));
                tank.setMaxAttackStrength(rs.getDouble("maxAttackStrength"));
                tank.setAttackDistance(rs.getDouble("attackDistance"));
                tank.setVisibleDistance(rs.getDouble("visibleDistance"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            dbLogger.error(e);
        }finally {
            closeConnection(connection);
            dbLogger.debug(query.toString());
        }
        return tank;
    }

    public Tank create(Player player) {
        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO).append(TABLE).append(OPEN_BKT);
        query.append("user_id, tank_id").append(CLOSE_BKT);
        query.append(VALUES).append(OPEN_BKT);
        query.append(player.getId()).append(COMMA);
        query.append(player.getTank().getId()).append(CLOSE_BKT).append(SEMICOLON);

        Tank tank = new Tank();
        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                tank.setId(rs.getLong("tank_id"));
                tank.setGames(rs.getLong("games"));
                tank.setKills(rs.getLong("kills"));
                tank.setDeaths(rs.getLong("deaths"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            dbLogger.error(e);
        }finally {
            closeConnection(connection);
            dbLogger.debug(query.toString());
        }
        return tank;
    }

    @Override
    public ArrayList<Tank> readAll(Player player) {
        ArrayList<Tank> allPlayerTanks = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(TABLE).append(WHERE).append("user_id=").append(player.getId()).append(SEMICOLON);

        Tank tank = new Tank();
        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                tank.setId(rs.getLong("tank_id"));
                tank.setGames(rs.getLong("games"));
                tank.setKills(rs.getLong("kills"));
                tank.setDeaths(rs.getLong("deaths"));

                allPlayerTanks.add(tank);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            dbLogger.error(e);
        }finally {
            closeConnection(connection);
            dbLogger.debug(query.toString());
        }
        return allPlayerTanks;
    }

    @Override
    public Tank read(long tankId) {
        Tank tank = new Tank();
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(TABLE).append(WHERE).append("tank_id=").append(tankId).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                tank.setId(tankId);
                tank.setGames(rs.getLong("games"));
                tank.setKills(rs.getLong("kills"));
                tank.setDeaths(rs.getLong("deaths"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            dbLogger.error(e);
        }finally {
            closeConnection(connection);
            dbLogger.debug(query.toString());
        }
        return tank;
    }

    @Override
    public void updateStats(Tank tank) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE).append(TABLE).append(SET);
        query.append("games=").append(tank.getGames()).append(COMMA);
        query.append("kills=").append(tank.getKills()).append(COMMA);
        query.append("deaths=").append(tank.getDeaths()).append(WHERE);
        query.append("tank_id=").append(tank.getId()).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.execute();
            ps.close();
        }catch (SQLException e){
            dbLogger.error(e);
        }finally {
            closeConnection(connection);
            dbLogger.debug(query.toString());
        }
    }

    @Override
    public void updateAI(ArrayList<Activity> tankAI, long tankId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Player player) {
        StringBuilder query = new StringBuilder();
        query.append(DELETE_FROM).append(TABLE).append(WHERE);
        query.append("tank_id=").append(player.getTank().getId()).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.execute();
            ps.close();
        }catch (SQLException e){
            dbLogger.error(e);
        }finally {
            closeConnection(connection);
            dbLogger.debug(query.toString());
        }
    }
}
