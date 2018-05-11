package by.epam.ivanov.aviacompany.dao.mysql;

import by.epam.ivanov.aviacompany.dao.CrewDAO;
import by.epam.ivanov.aviacompany.dao.DaoException;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlCrewDAO extends MySqlBaseDAO implements CrewDAO {
    private Logger LOGGER = Logger.getLogger(MySqlCrewDAO.class);

    @Override
    public List<Crew> getCrews() throws DaoException {
        String sql = "SELECT * FROM crew;";
        List<Crew> crewList = new ArrayList<>();
        Crew crew;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                crew = getCrewFromDB(resultSet);
                crewList.add(crew);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return crewList;
    }

    @Override
    public Crew read(Integer id) throws DaoException {
        String sql = "SELECT * FROM crew WHERE cr_id=?;";
        Crew crew = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                crew = getCrewFromDB(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return crew;
    }

    private Crew getCrewFromDB(ResultSet resultSet) throws DaoException {
        Crew crew;
        try {
            crew = new Crew();
            crew.setId(resultSet.getInt("cr_id"));
            crew.setName(resultSet.getString("cr_name"));
            crew.setUser(new User());
            crew.getUser().setId(resultSet.getInt("user_id"));
            return crew;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Integer create(Crew entity) throws DaoException {
        String sql = "INSERT INTO crew (cr_name, user_id) VALUES (?,?);";
        try (PreparedStatement statement = getConnection().
                prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getUser().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            Integer id = null;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Crew entity) throws DaoException {
        String sql = "UPDATE crew SET cr_name=?, user_id=? WHERE cr_id=?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getUser().getId());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM staff WHERE id=?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addStaffinCrew(Integer crewId, Integer staffId) throws DaoException {
        String sql = "INSERT INTO crew_staff VALUES (?,?);";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, crewId);
            statement.setInt(2, staffId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteStaffFromCrew(Integer crewId, Integer staffId) throws DaoException {
        String sql = "DELETE FROM crew_staff WHERE cr_id=? AND st_id=?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, crewId);
            statement.setInt(2, staffId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
