package ua.pirateparty.games.tanks.dao.implementations;

import ua.pirateparty.games.tanks.dao.interfaces.ITankDao;
import ua.pirateparty.games.tanks.server.entities.game.Tank;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.pirateparty.games.tanks.dao.db.ISqlConst.*;
import static ua.pirateparty.games.tanks.dao.db.Utils.closeConnection;
import static ua.pirateparty.games.tanks.util.tracer.Tracer.trace;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:51
 */

public class PgTankDao implements ITankDao{
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
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        trace(query);
        return tank;
    }

    @Override
    public void createCustom(Player player) {
        Tank tank = player.getTank();
        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO).append(TABLE).append(OPEN_BKT).append("player_id, name, hp, minAttackStrength, maxAttackStrength, attackDistance, visibleDistance").append(CLOSE_BKT);
        query.append(VALUES).append(OPEN_BKT).append(player.getId()).append(COMMA);
        query.append(tank.getName()).append(COMMA);
        query.append(tank.getHp()).append(COMMA);
        query.append(tank.getMinAttackStrength()).append(COMMA);
        query.append(tank.getMaxAttackStrength()).append(COMMA);
        query.append(tank.getAttackDistance()).append(COMMA);
        query.append(tank.getVisibleDistance()).append(CLOSE_BKT).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        trace(query);
    }

    @Override
    public Tank readCustom(long playerId) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(TABLE).append(WHERE);
        query.append("player_id=").append(playerId).append(SEMICOLON);

        Tank tank = new Tank();
        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                tank.setId(rs.getInt("type"));
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
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        trace(query);
        return tank;
    }

    @Override
    public void updateCustom(Player player) {
        Tank tank = player.getTank();
        StringBuilder query = new StringBuilder();
        query.append(UPDATE).append(TABLE).append(SET);
        query.append("type=").append(tank.getId()).append(COMMA);
        query.append("name=").append(tank.getName()).append(COMMA);
        query.append("hp=").append(tank.getHp()).append(COMMA);
        query.append("minAttackStrength=").append(tank.getMinAttackStrength()).append(COMMA);
        query.append("maxAttackStrength=").append(tank.getMaxAttackStrength()).append(COMMA);
        query.append("attackDistance=").append(tank.getAttackDistance()).append(COMMA);
        query.append("visibleDistance=").append(tank.getVisibleDistance());
        query.append(WHERE).append("player_id=").append(player.getId()).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        trace(query);
    }
}
