package dao.db;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 2:07
 */

public interface IConnectionFactory
{

    public abstract Connection getConnection();
}
