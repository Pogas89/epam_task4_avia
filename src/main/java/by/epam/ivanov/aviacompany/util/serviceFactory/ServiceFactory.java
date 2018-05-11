package by.epam.ivanov.aviacompany.util.serviceFactory;

import by.epam.ivanov.aviacompany.dao.CrewDAO;
import by.epam.ivanov.aviacompany.dao.FlightDAO;
import by.epam.ivanov.aviacompany.dao.StaffDAO;
import by.epam.ivanov.aviacompany.dao.UserDAO;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.FlightService;
import by.epam.ivanov.aviacompany.service.StaffService;
import by.epam.ivanov.aviacompany.service.UserService;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws ServiceFactoryException;

    CrewService getCrewService() throws ServiceFactoryException;

    StaffService getStaffService() throws ServiceFactoryException;

    FlightService getFlightService() throws ServiceFactoryException;

    UserDAO getUserDAO() throws ServiceFactoryException;

    CrewDAO getCrewDAO() throws ServiceFactoryException;

    StaffDAO getStaffDAO() throws ServiceFactoryException;

    FlightDAO getFlightDAO() throws ServiceFactoryException;

    Connection getConnection() throws ServiceFactoryException;
}
