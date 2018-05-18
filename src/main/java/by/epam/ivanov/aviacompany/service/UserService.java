package by.epam.ivanov.aviacompany.service;

import by.epam.ivanov.aviacompany.entity.User;

import java.util.List;

public interface UserService {
    User readById(Integer id) throws ServiceException;

    User readByLogin(String login) throws ServiceException;

    List<User> readUsers() throws ServiceException;

    List<User> readActualUsers() throws ServiceException;

    void save(User user) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
