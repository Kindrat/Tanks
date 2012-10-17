package ua.pirateparty.games.tanks.dao.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 2:05
 */

public class Utils {

    public static void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
