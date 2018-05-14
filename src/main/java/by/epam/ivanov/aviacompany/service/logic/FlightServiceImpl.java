package by.epam.ivanov.aviacompany.service.logic;

import by.epam.ivanov.aviacompany.dao.DaoException;
import by.epam.ivanov.aviacompany.dao.FlightDAO;
import by.epam.ivanov.aviacompany.entity.Flight;
import by.epam.ivanov.aviacompany.service.FlightService;
import by.epam.ivanov.aviacompany.service.ServiceException;

import java.util.List;

public class FlightServiceImpl implements FlightService {
    private FlightDAO flightDAO;

    public void setFlightDAO(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    @Override
    public Flight readById(Integer id) throws ServiceException {
        try {
            return flightDAO.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Flight> readFlights() throws ServiceException {
        try {
            return flightDAO.readAllFlights();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Flight> readNewFlights() throws ServiceException {
        try {
            return flightDAO.readNewFlights();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Flight flight) throws ServiceException {
        try{
            if(flight.getId()!=null){
                flightDAO.update(flight);
            } else{
                Integer id =flightDAO.create(flight);
                flight.setId(id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            flightDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
