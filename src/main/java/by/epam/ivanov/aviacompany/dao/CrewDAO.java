package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.Crew;

import java.util.List;

public interface CrewDAO extends DAO<Crew> {
    List<Crew> getCrews() throws DaoException;

    void addStaffinCrew(Integer crewId, Integer staffId) throws DaoException;

    void deleteStaffFromCrew(Integer crewId, Integer staffId) throws DaoException;

    List<Crew> getActualCrews() throws DaoException;
}
