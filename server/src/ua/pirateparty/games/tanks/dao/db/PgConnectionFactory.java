package ua.pirateparty.games.tanks.dao.db;

import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.jdbc2.optional.PoolingDataSource;
import ua.pirateparty.games.tanks.server.conf.ExternalConfigReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static ua.pirateparty.games.tanks.util.log.Loggers.dbLogger;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 2:07
 */

public class PgConnectionFactory extends ExternalConfigReader implements IConnectionFactory{

    private static PGPoolingDataSource dataSource;
    private static Map<String, String> tables;

    static {
        dataSource = new PoolingDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(host);
        dataSource.setDatabaseName(base);
        dataSource.setMaxConnections(maxDBConnections);

        tables = new HashMap<>();
        tables.put("players", "players.players");
        tables.put("roomIds", "rooms.ids");
        tables.put("roomParams", "rooms.params");
    }

    public PgConnectionFactory(){}

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch(SQLException e) {
            dbLogger.error(e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch(SQLException e) {
            dbLogger.error(e);
        }
    }

    public Map<String, String> getTables() {
        return tables;
    }
}