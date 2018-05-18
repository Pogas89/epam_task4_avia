package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    User readByLogin(String login) throws DaoException;

    List<User> getUsers() throws DaoException;

    List<User> getActualUsers() throws DaoException;

    void changePassword(Integer id, String pass) throws DaoException;
}
