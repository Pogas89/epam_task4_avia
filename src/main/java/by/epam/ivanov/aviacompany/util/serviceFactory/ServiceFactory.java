package by.epam.ivanov.aviacompany.util.serviceFactory;

import by.epam.ivanov.aviacompany.dao.StaffDAO;
import by.epam.ivanov.aviacompany.dao.UserDAO;
import by.epam.ivanov.aviacompany.service.StaffService;
import by.epam.ivanov.aviacompany.service.UserService;

import java.sql.Connection;

public interface ServiceFactory {
    UserService getUserService() throws ServiceFactoryException;

    StaffService getStaffService() throws ServiceFactoryException;

    UserDAO getUserDAO() throws ServiceFactoryException;

    StaffDAO getStaffDAO() throws ServiceFactoryException;

    Connection getConnection() throws ServiceFactoryException;
}
