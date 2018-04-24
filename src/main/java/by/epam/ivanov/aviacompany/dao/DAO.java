package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.Entity;

public interface DAO<T extends Entity> {
    T read(Integer id) throws DaoException;

    Integer create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}