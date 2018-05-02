package by.epam.ivanov.aviacompany.service;

import by.epam.ivanov.aviacompany.entity.Staff;

import java.util.List;

public interface StaffService {
    Staff readById(Integer id) throws ServiceException;

    Staff readByLastname(String lastName) throws ServiceException;

    List<Staff> readStaffs() throws ServiceException;

    void save(Staff staff) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
