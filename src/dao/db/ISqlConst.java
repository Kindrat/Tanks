package dao.db;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 2:06
 */

public interface ISqlConst
{

    public static final IConnectionFactory FACTORY = new PgConnectionFactory();
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String SELECT_ALL_FROM = "SELECT * FROM ";
    public static final String SELECT = "SELECT ";
    public static final String DELETE_FROM = "DELETE FROM ";
    public static final String FROM = " FROM ";
    public static final String UPDATE = "UPDATE ";
    public static final String SET = " SET ";
    public static final String WHERE = " WHERE ";
    public static final String VALUES = " VALUES";
    public static final String OPEN_BKT = "(";
    public static final String CLOSE_BKT = ") ";
    public static final String COMMA = ",";
    public static final String AND = " AND ";
    public static final String OR = " OR ";
    public static final String SPACE = " ";
    public static final String SEMICOLON = ";";
    public static final String QUOTE = "'";
    public static final String LIKE = " LIKE ";
    public static final String COUNT = " COUNT(*) ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String LIMIT = " LIMIT ";
    public static final String DEFAULT = "DEFAULT ";

}
