package by.epam.ivanov.aviacompany.service.logic;

import by.epam.ivanov.aviacompany.dao.CrewDAO;
import by.epam.ivanov.aviacompany.dao.DaoException;
import by.epam.ivanov.aviacompany.dao.StaffDAO;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.Staff;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.ServiceException;

import java.util.List;

public class CrewServiceImpl implements CrewService {
    private CrewDAO crewDAO;
    private StaffDAO staffDAO;

    public void setStaffDAO(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    public void setCrewDAO(CrewDAO crewDAO) {
        this.crewDAO = crewDAO;
    }

    @Override
    public Crew readById(Integer id) throws ServiceException {
        try {
            return crewDAO.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Crew> readCrews() throws ServiceException {
        try {
            return crewDAO.getCrews();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Crew> readActualCrews() throws ServiceException {
        try {
            return crewDAO.getActualCrews();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Crew crew) throws ServiceException {
        try{
            if(crew.getId()!=null){
                crewDAO.update(crew);
            } else{
                Integer id =crewDAO.create(crew);
                crew.setId(id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            crewDAO.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Staff> readFreeStaff() throws ServiceException {
        try{
            return staffDAO.getFreeStaffs();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Staff> readStaffFromCrew(Integer id) throws ServiceException {
        try{
            return staffDAO.getStaffsFromCrew(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addStaffInCrew(Integer crewId, Integer staffId) throws ServiceException {
        try{
            crewDAO.addStaffinCrew(crewId,staffId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteStaffFromCrew(Integer crewId, Integer staffId) throws ServiceException {
        try {
            crewDAO.deleteStaffFromCrew(crewId,staffId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
