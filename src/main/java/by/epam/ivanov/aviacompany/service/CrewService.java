package by.epam.ivanov.aviacompany.service;

import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.Staff;

import java.util.List;

public interface CrewService {
    Crew readById(Integer id) throws ServiceException;

    List<Crew> readCrews() throws ServiceException;

    void save (Crew crew) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    List<Staff> readFreeStaff() throws ServiceException;

    List<Staff> readStaffFromCrew(Integer id) throws ServiceException;

    void addStaffInCrew(Integer crewId, Integer staffId) throws ServiceException;

    void deleteStaffFromCrew(Integer crewId, Integer staffId) throws ServiceException;
}
