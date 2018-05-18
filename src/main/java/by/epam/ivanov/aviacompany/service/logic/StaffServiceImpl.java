package by.epam.ivanov.aviacompany.service.logic;

import by.epam.ivanov.aviacompany.dao.DaoException;
import by.epam.ivanov.aviacompany.dao.StaffDAO;
import by.epam.ivanov.aviacompany.entity.Staff;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.StaffService;
import org.apache.log4j.Logger;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    private static Logger LOGGER = Logger.getLogger(StaffServiceImpl.class);
    private StaffDAO staffDAO;

    public void setStaffDAO(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    @Override
    public Staff readById(Integer id) throws ServiceException {
        try {
            return staffDAO.read(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public Staff readByLastname(String lastName) throws ServiceException {
        try {
            return staffDAO.readByLastName(lastName);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Staff> readStaffs() throws ServiceException {
        try {
            return staffDAO.getStaffs();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Staff> readActualStaffs() throws ServiceException {
        try {
            return staffDAO.getActualStaffs();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Staff> readStaffFromCrew(Integer id) throws ServiceException {
        try {
            return staffDAO.getStaffsFromCrew(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Staff staff) throws ServiceException {
        try {
            if (staff.getId() == null) {
                Integer id = staffDAO.create(staff);
                staff.setId(id);
            } else {
                staffDAO.update(staff);
            }
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            staffDAO.delete(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e);
        }
    }
}
