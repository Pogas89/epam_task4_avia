package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.Flight;

import java.util.List;

/**
 * Provides an abstract interface to all type of DB for Flight Entity
 *
 * @see DaoException
 * @see DAO
 */
public interface FlightDAO extends DAO<Flight> {
    /**
     * Provides access to the list of all crews stored in db
     *
     * @return List of all crews stored in db
     * @throws DaoException
     */
    List<Flight> readAllFlights() throws DaoException;

    /**
     * Provides access to the list of flights with "new" status stored in db
     *
     * @return list of flights with "new" status
     * @throws DaoException
     */
    List<Flight> readNewFlights() throws DaoException;

    /**
     * Provides access to the list of flights stored in db, that not in archive
     *
     * @return list of flights, that not in archive
     * @throws DaoException
     */
    List<Flight> readActualFlights() throws DaoException;
}
