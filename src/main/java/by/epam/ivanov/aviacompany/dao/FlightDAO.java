package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.Flight;

import java.util.List;

public interface FlightDAO extends DAO<Flight> {
    List<Flight> readAllFlights() throws DaoException;

    List<Flight> readNewFlights() throws DaoException;
}
