package by.epam.ivanov.aviacompany.service;

import by.epam.ivanov.aviacompany.entity.Flight;

import java.util.List;

public interface FlightService {
    Flight readById(Integer id) throws ServiceException;

    List<Flight> readFlights() throws ServiceException;

    List<Flight> readNewFlights() throws ServiceException;

    void save(Flight flight) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
