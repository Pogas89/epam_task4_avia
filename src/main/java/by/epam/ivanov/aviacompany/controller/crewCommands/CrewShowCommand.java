package by.epam.ivanov.aviacompany.controller.crewCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.Staff;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CrewShowCommand extends Command {
    private Logger LOGGER = Logger.getLogger(CrewShowCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }
        try{
            CrewService crewService = getServiceFactory().getCrewService();
            Crew crew = crewService.readById(id);
            List<Staff> staffInCrew = crewService.readStaffFromCrew(id);
            List<Staff> freeStaff = crewService.readFreeStaff();
            request.setAttribute("crew", crew);
            request.setAttribute("staffInCrew", staffInCrew);
            request.setAttribute("freeStaff", freeStaff);
            return Pages.CREWSHOW_PAGE;
        } catch (ServiceFactoryException | ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
    }
}
