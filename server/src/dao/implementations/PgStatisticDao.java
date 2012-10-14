package dao.implementations;

import dao.interfaces.IStatisticDao;
import entities.player.Player;
import entities.player.Statistic;

import java.sql.*;

import static dao.db.ISqlConst.*;
import static dao.db.Utils.closeConnection;
import static global.Config.DEBUG;
import static global.Static.outLn;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:54
 */
public class PgStatisticDao implements IStatisticDao {
    private final String TABLE = "public.statistic";
    @Override
    public Statistic create(long playerId) {
        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO).append(TABLE).append(OPEN_BKT).append("player_id").append(CLOSE_BKT);
        query.append(VALUES).append(OPEN_BKT).append(playerId).append(CLOSE_BKT).append(SEMICOLON);

        Statistic statistic = new Statistic();
        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                statistic.setGamesPlayed(rs.getInt("games_played"));
                statistic.setKills(rs.getInt("kills"));
                statistic.setWins(rs.getInt("wins"));
                statistic.setLoses(rs.getInt("loses"));
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
        return statistic;
    }

    @Override
    public Statistic read(long playerId) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ALL_FROM).append(TABLE).append(WHERE);
        query.append("player_id=").append(playerId).append(SEMICOLON);

        Statistic statistic = new Statistic();
        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                statistic.setGamesPlayed(rs.getInt("games_played"));
                statistic.setKills(rs.getInt("kills"));
                statistic.setWins(rs.getInt("wins"));
                statistic.setLoses(rs.getInt("loses"));
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
        return statistic;    }

    @Override
    public void update(Player player) {
        StringBuilder query = new StringBuilder();
        Statistic statistic = player.getStatistic();

        query.append(UPDATE).append(TABLE).append(SET);
        query.append("games_played=").append(statistic.getGamesPlayed()).append(COMMA);
        query.append("kills=").append(statistic.getKills()).append(COMMA);
        query.append("wins=").append(statistic.getWins()).append(COMMA);
        query.append("loses=").append(statistic.getLoses()).append(WHERE);
        query.append("player_id=").append(player.getId()).append(SEMICOLON);

        Connection connection = FACTORY.getConnection();

        try{
            PreparedStatement ps = connection.prepareStatement(query.toString());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        if (DEBUG){
            outLn(">>DB Connection :    "+ query.toString());
        }
    }
}
