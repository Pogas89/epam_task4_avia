package by.epam.ivanov.aviacompany.dao.mysql;

import by.epam.ivanov.aviacompany.dao.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The implementation of BaseDao to db of MySql Type
 *
 * @see by.epam.ivanov.aviacompany.dao.DAO
 */
public class MySqlBaseDAO {
    private static final Logger LOGGER = Logger.getLogger(MySqlBaseDAO.class);
    private Connection connection;

    Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    void changeToArchive(String sql, Integer id) throws DaoException {
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
    }
}
