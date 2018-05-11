package by.epam.ivanov.aviacompany.controller.flightCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.Flight;
import by.epam.ivanov.aviacompany.entity.FlightStatus;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.FlightService;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FlightEditCommand extends Command {
    private Logger LOGGER = Logger.getLogger(FlightEditCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error("id is not a number");
        }
        if (id != null){
            try {
                FlightService flightService = getServiceFactory().getFlightService();
                Flight flight = flightService.readById(id);

                request.setAttribute("flight", flight);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        CrewService crewService = null;
        try {
            crewService = getServiceFactory().getCrewService();
            List<Crew> crews = crewService.readCrews();
            request.setAttribute("crewList", crews);
        } catch (ServiceFactoryException | ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
        request.setAttribute("flightStatus", FlightStatus.values());
        return Pages.FLIGHTEDIT_PAGE;
    }
}
