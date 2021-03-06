package by.epam.ivanov.aviacompany.dao.mysql;

import by.epam.ivanov.aviacompany.dao.DaoException;
import by.epam.ivanov.aviacompany.dao.FlightDAO;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.Flight;
import by.epam.ivanov.aviacompany.entity.FlightStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * The implementation of FlightDao to db of MySql Type
 *
 * @see FlightDAO
 */
public class MySqlFlightDAO extends MySqlBaseDAO implements FlightDAO {
    private final Logger LOGGER = Logger.getLogger(MySqlFlightDAO.class);

    private Flight getFlightFromDB(ResultSet resultSet) throws DaoException {
        Flight flight;
        try {
            flight = new Flight();
            flight.setId(resultSet.getInt("fl_id"));
            flight.setName(resultSet.getString("fl_name"));
            flight.setDeparture(resultSet.getString("fl_departure"));
            flight.setDestination(resultSet.getString("fl_destination"));
            flight.setDate(resultSet.getDate("fl_date"));
            //Convert UTC Time in System TimeZone Time
            DateFormat utcFormat = new SimpleDateFormat("HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            DateFormat sysFormat = new SimpleDateFormat("HH:mm:ss");
            sysFormat.setTimeZone(TimeZone.getDefault());
            Date date = utcFormat.parse(resultSet.getTime("fl_time").toString());
            flight.setTime(Time.valueOf(sysFormat.format(date)));
            flight.setStatus(FlightStatus.values()[resultSet.getInt("fl_statatus")]);
            flight.setCrew(new Crew());
            flight.getCrew().setId(resultSet.getInt("crew_id"));
            return flight;
        } catch (SQLException | ParseException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Flight read(Integer id) throws DaoException {
        String sql = "SELECT * FROM flight WHERE fl_id =?;";
        Flight flight = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                flight = getFlightFromDB(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return flight;
    }

    @Override
    public Integer create(Flight entity) throws DaoException {
        String sql = "INSERT INTO flight " +
                "(fl_name, fl_departure, fl_destination, fl_date, fl_time, fl_statatus, crew_id)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = getConnection().
                prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDeparture());
            statement.setString(3, entity.getDestination());
            statement.setDate(4, entity.getDate());
            statement.setTime(5, entity.getTime());
            statement.setInt(6, entity.getStatus().ordinal());
            if (entity.getCrew() != null) {
                statement.setInt(7, entity.getCrew().getId());
            } else {
                statement.setNull(7, Types.INTEGER);
            }
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
    public void update(Flight entity) throws DaoException {
        String sql;
        if (entity.getCrew().getId() != 0) {
            sql = "UPDATE flight SET fl_name=?, fl_departure=?, fl_destination=?," +
                    "fl_date=?, fl_time=?, fl_statatus=?, crew_id=? WHERE fl_id=?;";
        } else {
            sql = "UPDATE flight SET fl_name=?, fl_departure=?, fl_destination=?," +
                    "fl_date=?, fl_time=?, fl_statatus=? WHERE fl_id=?;";
        }
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDeparture());
            statement.setString(3, entity.getDestination());
            statement.setDate(4, entity.getDate());
            statement.setTime(5, entity.getTime());
            statement.setInt(6, entity.getStatus().ordinal());
            if (entity.getCrew().getId() != 0) {
                statement.setInt(7, entity.getCrew().getId());
                statement.setInt(8, entity.getId());
            } else {
                statement.setInt(7, entity.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "UPDATE flight SET isArchive=1 WHERE fl_id=?;";
        changeToArchive(sql, id);
    }

    @Override
    public List<Flight> readAllFlights() throws DaoException {
        String sql = "SELECT * FROM flight ORDER BY fl_date DESC , fl_time DESC;";
        return getListOfFlight(sql);
    }

    @Override
    public List<Flight> readActualFlights() throws DaoException {
        String sql = "SELECT * FROM flight WHERE isArchive=0 ORDER BY fl_date DESC , fl_time DESC;";
        return getListOfFlight(sql);
    }

    private List<Flight> getListOfFlight(String sql) throws DaoException{
        Flight flight;
        List<Flight> flights = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                flight = getFlightFromDB(resultSet);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return flights;
    }

    @Override
    public List<Flight> readNewFlights() throws DaoException {
        String sql = "SELECT * FROM flight WHERE isArchive=0 && fl_statatus=? ORDER BY fl_date DESC , fl_time DESC;";
        Flight flight;
        List<Flight> flights = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, FlightStatus.NEW.ordinal());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flight = getFlightFromDB(resultSet);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return flights;
    }
}
