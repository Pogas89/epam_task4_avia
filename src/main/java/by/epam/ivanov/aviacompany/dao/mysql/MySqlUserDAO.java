package by.epam.ivanov.aviacompany.dao.mysql;

import by.epam.ivanov.aviacompany.dao.DaoException;
import by.epam.ivanov.aviacompany.dao.UserDAO;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.entity.UserRole;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDAO extends MySqlBaseDAO implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(MySqlUserDAO.class);

    private User getUserFromDB(ResultSet resultSet) throws DaoException {
        User user;
        try {
            user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setLogin(resultSet.getString("us_login"));
            user.setPassword(resultSet.getString("us_password"));
            user.setFirstName(resultSet.getString("us_Fname"));
            user.setLastName(resultSet.getString("us_Lname"));
            user.setEmail(resultSet.getString("us_email"));
            user.setUserRole(UserRole.values()[resultSet.getInt("us_role")]);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) throws DaoException {
        String sql = "SELECT * FROM user WHERE user_id =?;";
        User user = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromDB(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        String sql = "SELECT * FROM user WHERE us_login =?;";
        User user = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromDB(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public List<User> getUsers() throws DaoException {
        String sql = "SELECT * FROM user;";
        List<User> users = new ArrayList();
        User user;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user = getUserFromDB(resultSet);
                users.add(user);
                LOGGER.debug("get user = " + user.getId());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public Integer create(User user) throws DaoException {
        String sql = "INSERT INTO user(us_login," +
                " us_password, us_Fname, us_Lname, us_email, us_role) " +
                "VALUES (?,?,?,?,?,?);";
        try (PreparedStatement statement =
                     getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getUserRole().ordinal());
            statement.executeUpdate();
            Integer id = null;
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM user WHERE user_id=?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User entity) throws DaoException {
        String sql = "UPDATE user SET us_login=?, us_password=?, us_Fname=?, " +
                "us_Lname=?, us_email=?, us_role=? WHERE user_id=?;";
        try (PreparedStatement statement =
                     getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getFirstName());
            statement.setString(4, entity.getLastName());
            statement.setString(5, entity.getEmail());
            statement.setInt(6, entity.getUserRole().ordinal());
            statement.setInt(7, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
    }
}
