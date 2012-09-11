package dao.implementations;

import dao.interfaces.IPlayerDao;
import entities.Player;
import entities.Tank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.db.ISqlConst.*;
import static dao.db.Utils.closeConnection;
import static global.Static.outLn;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static global.Config.DEBUG;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 1:59
 */

public class PgPlayerDao implements IPlayerDao{

    private final String TABLE = "public.users";
    private final String COLUMNS = "(name,exp,money,tank_id)";
    @Override
    public Player createPlayer(Player player) {
        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO).append(TABLE).append(COLUMNS);
        query.append(VALUES).append(OPEN_BKT);
        query.append(player.getName()).append(COMMA);
        query.append(DEFAULT).append(COMMA);
        query.append(DEFAULT).append(COMMA);
        query.append(DEFAULT).append(CLOSE_BKT).append(SEMICOLON);

        boolean isCreate = false;

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString(), RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                player.setId(rs.getLong("id"));
                player.setExp(rs.getLong("exp"));
                player.setMoney(rs.getLong("money"));
                player.setTankId(rs.getInt("tank_id"));

                player.setTank(getTank(player.getTankId()));

                isCreate = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        if (DEBUG){
            outLn(">>DB Connection :    "+ query.toString());
        }

        if (isCreate){
            return player;
        } else{
            return null;
        }
    }

    @Override
    public Player readPlayer(Player player) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(TABLE).append(WHERE);
        return null;
    }

    @Override
    public void updatePlayer(Player player) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE).append(TABLE).append(SET);
        query.append("name=").append(player.getName()).append(COMMA);
        query.append("exp=").append(player.getExp()).append(COMMA);
        query.append("money=").append(player.getMoney()).append(COMMA);
        query.append("tank_id=").append(player.getTankId());
        query.append(WHERE).append("id=").append(player.getId()).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.execute();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        if (DEBUG){
            outLn(">>DB Connection :    "+ query.toString());
        }
    }

    @Override
    public Tank getTank(int tankId) {
        Tank tank = new Tank();
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append("const.tanks").append(WHERE).append("id=").append(tankId).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                tank.setId(tankId);
                tank.setName(rs.getString("name"));
                tank.setHp(rs.getInt("hp"));
                tank.setMinAttackStrength(rs.getLong("minAttackStrength"));
                tank.setMaxAttackStrength(rs.getLong("maxAttackStrength"));
                tank.setAttackDistance(rs.getLong("attackDistance"));
                tank.setVisibleDistance(rs.getLong("visibleDistance"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }

        if (DEBUG){
            outLn(">>DB Connection :    "+ query.toString());
        }
        return tank;
    }
}
