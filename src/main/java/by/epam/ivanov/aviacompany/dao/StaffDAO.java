package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.Staff;

import java.util.List;

/**
 * Provides an abstract interface to all type of DB for Staff Entity
 *
 * @see DaoException
 * @see DAO
 */
public interface StaffDAO extends DAO<Staff> {

    /**
     * Provides access to staff by it lastname that stored in db
     *
     * @return staff entity
     * @throws DaoException
     */
    Staff readByLastName(String lastName) throws DaoException;

    /**
     * Provides access to the list of all staffs stored in db
     *
     * @return List of all staffs stored in db
     * @throws DaoException
     */
    List<Staff> getStaffs() throws DaoException;

    /**
     * Provides access to the list of staffs in crew stored in db
     *
     * @return list of staffs in crew
     * @throws DaoException
     */
    List<Staff> getStaffsFromCrew(Integer cr_id) throws DaoException;

    /**
     * Provides access to the list of staffs that not in any crew stored in db
     *
     * @return list of crews, that not in any crew
     * @throws DaoException
     */
    List<Staff> getFreeStaffs() throws DaoException;

    /**
     * Provides access to the list of staffs stored in db , that not in archive
     *
     * @return list of staffs, that not in archive
     * @throws DaoException
     */
    List<Staff> getActualStaffs() throws DaoException;
}
