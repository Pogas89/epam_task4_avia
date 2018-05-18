package by.epam.ivanov.aviacompany.controller.crewCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.util.Commands;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteStaffFromCrewCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(DeleteStaffFromCrewCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.debug("CrewId=" + request.getParameter("crewId"));
        LOGGER.debug("StaffId=" + request.getParameter("staffId"));
        Integer crewId;
        Integer staffId;
        try {
            crewId = Integer.parseInt(request.getParameter("crewId"));
            staffId = Integer.parseInt(request.getParameter("staffId"));
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }
        if (crewId != null && staffId != null) {
            try {
                CrewService service = getServiceFactory().getCrewService();
                service.deleteStaffFromCrew(crewId, staffId);
            } catch (ServiceFactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return Commands.CREWSHOW_COMMAND + "?id=" + crewId;
    }
}
