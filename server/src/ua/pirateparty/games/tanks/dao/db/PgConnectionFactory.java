package ua.pirateparty.games.tanks.dao.db;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.jdbc2.optional.PoolingDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 2:07
 */

public class PgConnectionFactory implements IConnectionFactory{

    private static PGPoolingDataSource dataSource;
    private static String user;
    private static String pass;
    private static String host;
    private static String base;

    static
    {
        parseXml();
        dataSource = new PoolingDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setServerName(host);
        dataSource.setDatabaseName(base);
        dataSource.setMaxConnections(50);
    }

    public PgConnectionFactory(){ }

    public Connection getConnection()
    {
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }

    private static void parseXml()
    {
        try
        {
            Document document = (new SAXReader()).read("db.conf");
            user = document.getRootElement().elementText("user");
            pass = document.getRootElement().elementText("pass");
            host = document.getRootElement().elementText("host");
            base = document.getRootElement().elementText("base");
        }
        catch(DocumentException e)
        {
            e.printStackTrace();
        }
    }
}