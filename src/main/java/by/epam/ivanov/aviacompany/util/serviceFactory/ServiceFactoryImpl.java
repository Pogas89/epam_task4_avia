package by.epam.ivanov.aviacompany.util.serviceFactory;

import by.epam.ivanov.aviacompany.dao.CrewDAO;
import by.epam.ivanov.aviacompany.dao.StaffDAO;
import by.epam.ivanov.aviacompany.dao.UserDAO;
import by.epam.ivanov.aviacompany.dao.mysql.MySqlCrewDAO;
import by.epam.ivanov.aviacompany.dao.mysql.MySqlStaffDAO;
import by.epam.ivanov.aviacompany.dao.mysql.MySqlUserDAO;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.StaffService;
import by.epam.ivanov.aviacompany.service.UserService;
import by.epam.ivanov.aviacompany.service.logic.CrewServiceImpl;
import by.epam.ivanov.aviacompany.service.logic.StaffServiceImpl;
import by.epam.ivanov.aviacompany.service.logic.UserServiceImpl;
import by.epam.ivanov.aviacompany.util.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactoryImpl implements ServiceFactory {
    private Logger LOGGER = Logger.getLogger(ServiceFactoryImpl.class);
    ConnectionPool pool;
    Connection connection;

    @Override
    public UserService getUserService() throws ServiceFactoryException {
        UserServiceImpl service = new UserServiceImpl();
        service.setUserDAO(getUserDAO());
        return service;
    }

    @Override
    public StaffService getStaffService() throws ServiceFactoryException {
        StaffServiceImpl service = new StaffServiceImpl();
        service.setStaffDAO(getStaffDAO());
        return service;
    }

    @Override
    public UserDAO getUserDAO() throws ServiceFactoryException {
        MySqlUserDAO userDAO = new MySqlUserDAO();
        userDAO.setConnection(getConnection());
        return userDAO;
    }

    @Override
    public StaffDAO getStaffDAO() throws ServiceFactoryException {
        MySqlStaffDAO staffDAO = new MySqlStaffDAO();
        staffDAO.setConnection(getConnection());
        return staffDAO;
    }

    @Override
    public Connection getConnection() throws ServiceFactoryException {
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection!=null) {
            connection.close();
        }
    }

    @Override
    public CrewService getCrewService() throws ServiceFactoryException {
        CrewServiceImpl service = new CrewServiceImpl();
        service.setCrewDAO(getCrewDAO());
        return service;
    }

    @Override
    public CrewDAO getCrewDAO() throws ServiceFactoryException {
        MySqlCrewDAO crewDAO = new MySqlCrewDAO();
        crewDAO.setConnection(connection);
        return  crewDAO;
    }
}
