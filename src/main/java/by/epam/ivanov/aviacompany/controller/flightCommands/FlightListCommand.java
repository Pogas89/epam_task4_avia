package by.epam.ivanov.aviacompany.controller.flightCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.Flight;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.entity.UserRole;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.FlightService;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.util.PagedListHolderImpl;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FlightListCommand extends Command {
    private Logger LOGGER = Logger.getLogger(FlightListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");
        try {
            FlightService service = getServiceFactory().getFlightService();
            List<Flight> flightList = null;
            if (currentUser.getUserRole()== UserRole.ADMIN) {
                flightList = service.readActualFlights();
            } else if (currentUser.getUserRole()==UserRole.DISPETCHER){
                flightList = service.readNewFlights();
            }
            if (!flightList.isEmpty()) {
                CrewService crewService = getServiceFactory().getCrewService();
                Crew crew;
                Integer id;
                for (Flight f : flightList) {
                    crew = f.getCrew();
                    id = crew.getId();
                    crew = crewService.readById(id);
                    f.setCrew(crew);
                }
                PagedListHolderImpl<Flight> listHolder = new PagedListHolderImpl<>(flightList);
                listHolder.setAttribut("flightList");
                listHolder.setPadding(request);
            }
            return Pages.FLIGHTLIST_PAGE;
        } catch (ServiceFactoryException | ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
    }
}
