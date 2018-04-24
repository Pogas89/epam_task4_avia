package by.epam.ivanov.aviacompany.util.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionPool {
    private static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private int maxPool;
    private String url;
    private String user;
    private String password;
    private List<PooledConnection> free, used;

    private ConnectionPool() {
    }

    public ConnectionPool(int maxPool, String driver, String url, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        this.maxPool = maxPool;
        this.url = url;
        this.user = user;
        this.password = password;

        free = Collections.synchronizedList(new ArrayList<PooledConnection>(maxPool));
        free.add(createConnectionWrapper());
        used = Collections.synchronizedList(new ArrayList<PooledConnection>(maxPool));
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

    public synchronized void destroy() {
        for (PooledConnection connection : free) {
            try {
                connection.getRawConnection().close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        for (PooledConnection connection : used) {
            try {
                connection.getRawConnection().close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        PooledConnection connection;

        if (free.size() > 0) {
            connection = free.remove(free.size() - 1);
        } else if (used.size() < maxPool) {
            connection = createConnectionWrapper();
        } else {
            throw new RuntimeException("Can not create connection");
        }

        used.add(connection);
        return connection;
    }

    public PooledConnection createConnectionWrapper() throws SQLException {
        Connection connection = null;
        PooledConnection pooledConnection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            pooledConnection = new PooledConnection(this, connection);
        } catch (SQLException e) {
            LOGGER.error("Can not create connection", e);
            try {
                if (connection != null) connection.close();
            } catch (SQLException e2) {
                LOGGER.error("Can not close connection", e2);
                throw e;
            }
        }
        return pooledConnection;
    }

    public void freeConnectionWrapper(PooledConnection connection) {
        used.remove(connection);
        free.add(connection);
    }
}
