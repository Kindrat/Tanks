package ua.pirateparty.games.tanks.dao.implementations;

import ua.pirateparty.games.tanks.dao.interfaces.IPlayerDao;
import ua.pirateparty.games.tanks.dao.interfaces.IStatisticDao;
import ua.pirateparty.games.tanks.dao.interfaces.ITankDao;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static ua.pirateparty.games.tanks.dao.db.ISqlConst.*;
import static ua.pirateparty.games.tanks.dao.db.Utils.closeConnection;
import static ua.pirateparty.games.tanks.global.Config.DEFAULT_TANK_ID;
import static ua.pirateparty.games.tanks.util.tracer.Tracer.trace;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 1:59
 */

public class PgPlayerDao implements IPlayerDao{

    private final String TABLE = "public.users";
    private final String COLUMNS = "(name,exp,tank_id)";

    @Override
    public Player create(Player player) {
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

                ITankDao tankDao = new PgTankDao();
                player.setTank(tankDao.readDefault(DEFAULT_TANK_ID));

                IStatisticDao statisticDao = new PgStatisticDao();
                player.setStatistic(statisticDao.create(player.getId()));

                isCreate = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        trace(query);

        if (isCreate){
            return player;
        } else{
            return null;
        }
    }

    @Override
    public Player read(Player player) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(TABLE).append(WHERE);
        query.append("name=").append(player.getName()).append(AND);
        query.append("passHash=").append(player.getPassHash()).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                player.setId(rs.getLong("id"));
                player.setName(rs.getString("name"));
                player.setExp(rs.getLong("exp"));

                ITankDao tankDao = new PgTankDao();
                player.setTank(tankDao.readCustom(player.getId()));

                IStatisticDao statisticDao = new PgStatisticDao();
                player.setStatistic(statisticDao.read(player.getId()));
            }
            rs.close();
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        trace(query);
        return player;
    }

    @Override
    public void update(Player player) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE).append(TABLE).append(SET);
        query.append("name=").append(player.getName()).append(COMMA);
        query.append("exp=").append(player.getExp());
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
        trace(query);
    }

    @Override
    public void delete(Player player) {
        String TABLES = "users,statistic,tanks";
        StringBuilder query = new StringBuilder();
        query.append(DELETE_FROM).append(TABLES).append(WHERE);
        query.append("id=").append(player.getId()).append(AND);
        query.append("statistic.player_id=").append(player.getId()).append(AND);
        query.append("tanks.player_id=").append(player.getId()).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.execute();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        trace(query);
    }
}
