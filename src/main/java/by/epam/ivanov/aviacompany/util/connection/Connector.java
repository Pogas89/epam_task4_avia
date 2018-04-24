package by.epam.ivanov.aviacompany.util.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class Connector {
    private static ConnectionPool pool;
    private static int maxPool;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    public static void init(int maxPool, String driver, String url, String user, String password)
            throws SQLException, ClassNotFoundException {
        Connector.maxPool = maxPool;
        Connector.driver = driver;
        Connector.url = url;
        Connector.user = user;
        Connector.password = password;
        pool = new ConnectionPool(maxPool, driver, url, user, password);
    }

    public static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
}
