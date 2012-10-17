package ua.pirateparty.games.tanks.dao.implementations;

import ua.pirateparty.games.tanks.dao.interfaces.ITankStatisticDao;
import ua.pirateparty.games.tanks.server.entities.player.Player;
import ua.pirateparty.games.tanks.server.entities.stat.TankStatistic;

import java.sql.*;
import java.util.ArrayList;

import static ua.pirateparty.games.tanks.dao.db.ISqlConst.*;
import static ua.pirateparty.games.tanks.dao.db.Utils.closeConnection;
import static ua.pirateparty.games.tanks.util.tracer.Tracer.trace;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 15.10.12
 * Time: 1:54
 */
public class PgTankStatisticDao implements ITankStatisticDao {
    private String TABLE = "public.tank_statistic";
    @Override
    public TankStatistic create(Player player) {
        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO).append(TABLE).append(OPEN_BKT);
        query.append("user_id, tank_id").append(CLOSE_BKT);
        query.append(VALUES).append(OPEN_BKT);
        query.append(player.getId()).append(COMMA);
        query.append(player.getTank().getId()).append(CLOSE_BKT).append(SEMICOLON);

        TankStatistic tankStatistic = new TankStatistic();
        Connection connection = FACTORY.getConnection();
        boolean isCreate = false;

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                tankStatistic.setTankId(player.getTank().getId());
                tankStatistic.setGames(rs.getLong("games"));
                tankStatistic.setKills(rs.getLong("kills"));
                tankStatistic.setDeaths(rs.getLong("deaths"));
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        trace(query);
        return tankStatistic;
    }

    @Override
    public ArrayList<TankStatistic> readAll(Player player) {
        ArrayList<TankStatistic> allPlayerTanks = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(TABLE).append(WHERE).append("user_id=").append(player.getId()).append(SEMICOLON);

        TankStatistic tankStatistic = new TankStatistic();
        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                tankStatistic.setTankId(rs.getLong("tank_id"));
                tankStatistic.setGames(rs.getLong("games"));
                tankStatistic.setKills(rs.getLong("kills"));
                tankStatistic.setDeaths(rs.getLong("deaths"));

                allPlayerTanks.add(tankStatistic);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        trace(query);
        return allPlayerTanks;
    }

    @Override
    public TankStatistic read(Player player) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Player player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Player player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
