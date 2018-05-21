package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.User;

import java.util.List;

/**
 * Provides an abstract interface to all type of DB for User Entity
 *
 * @see DaoException
 * @see DAO
 */
public interface UserDAO extends DAO<User> {
    /**
     * Provides access to user by it id that stored in db
     *
     * @return user entity
     * @throws DaoException
     */
    User readByLogin(String login) throws DaoException;

    /**
     * Provides access to all users stored in db
     *
     * @return list of users
     * @throws DaoException
     */
    List<User> getUsers() throws DaoException;

    /**
     * Provides access to the list of users stored in db , that not in archive
     *
     * @return list of users, that not in archive
     * @throws DaoException
     */
    List<User> getActualUsers() throws DaoException;

    /**
     * provides the ability to change the user's password in db
     *
     * @param id of user
     * @param pass new password
     * @throws DaoException
     */
    void changePassword(Integer id, String pass) throws DaoException;
}
