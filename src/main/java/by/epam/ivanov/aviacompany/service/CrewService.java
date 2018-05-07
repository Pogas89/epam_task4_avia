package by.epam.ivanov.aviacompany.service;

import by.epam.ivanov.aviacompany.entity.Crew;

import java.util.List;

public interface CrewService {
    Crew readById(Integer id) throws ServiceException;

    List<Crew> readCrews() throws ServiceException;

    void save (Crew crew) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}