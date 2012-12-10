package ua.pirateparty.games.tanks.dao.db;

import java.sql.Connection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 2:07
 */

public interface IConnectionFactory
{
    public abstract Connection getConnection();

    public abstract Map<String, String> getTables();
}
