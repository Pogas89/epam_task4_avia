package by.epam.ivanov.aviacompany.dao.mysql;

import java.sql.Connection;

public class MySqlBaseDAO {
    private Connection connection;

    Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
