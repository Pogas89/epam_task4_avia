package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.Staff;

import java.util.List;

public interface StaffDAO extends DAO<Staff> {
    Staff readByLastName(String lastName) throws DaoException;

    List<Staff> getStaffs() throws DaoException;

    List<Staff> getStaffsFromCrew(Integer cr_id) throws DaoException;

    List<Staff> getFreeStaffs() throws DaoException;
}
