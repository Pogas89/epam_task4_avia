package by.epam.ivanov.aviacompany.service.logic;

import by.epam.ivanov.aviacompany.dao.DaoException;
import by.epam.ivanov.aviacompany.dao.UserDAO;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User readById(Integer id) throws ServiceException {
        try {
            return userDAO.read(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public User readByLogin(String login) throws ServiceException {
        try {
            return userDAO.readByLogin(login);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> readUsers() throws ServiceException {
        try {
            return userDAO.getUsers();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            if (user.getId()==null) {
                Integer id = userDAO.create(user);
                user.setId(id);
            } else {
                userDAO.update(user);
            }
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            userDAO.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }

    }
}
