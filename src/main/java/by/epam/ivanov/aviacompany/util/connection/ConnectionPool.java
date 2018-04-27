package by.epam.ivanov.aviacompany.util.connection;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance = null;
    private int maxPool;
    private String url;
    private String user;
    private String password;
    private List<PooledConnection> free, used;

    private ConnectionPool() throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream
                    ("connection.properties")){
            properties.load(inputStream);
            Class.forName(properties.getProperty("driver"));
            this.maxPool = Integer.parseInt(properties.getProperty("maxPool"));
            this.url = properties.getProperty("url");
            this.user = properties.getProperty("user");
            this.password = properties.getProperty("password");
        } catch (IOException e) {
            LOGGER.error("can not load properties for connection");
        }

        free = Collections.synchronizedList(new ArrayList<PooledConnection>(maxPool));
        for (int i = 0; i < 4; i++) {
            LOGGER.debug("add connection " + i + ":");
            free.add(createConnectionWrapper());
        }
        used = Collections.synchronizedList(new ArrayList<PooledConnection>(maxPool));
    }

    public static ConnectionPool getInstance() throws SQLException, ClassNotFoundException {
        if(instance==null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

    private synchronized void destroy() {
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

    private PooledConnection createConnectionWrapper() throws SQLException {
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
        LOGGER.debug(connection);
        LOGGER.debug(pooledConnection);
        return pooledConnection;
    }

    void freeConnectionWrapper(PooledConnection connection) {
        LOGGER.debug("free connection " + connection);
        used.remove(connection);
        free.add(connection);
    }
}
