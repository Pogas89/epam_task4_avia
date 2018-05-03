package by.epam.ivanov.aviacompany.dao;

import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.Staff;

import java.util.List;

public interface CrewDAO extends DAO<Crew> {
    List<Crew> getCrews() throws DaoException;
}
