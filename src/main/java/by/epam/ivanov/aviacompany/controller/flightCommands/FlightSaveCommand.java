package by.epam.ivanov.aviacompany.controller.flightCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.*;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.FlightService;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.util.Commands;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;

public class FlightSaveCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(FlightSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        Flight flight = new Flight();
        if (id != null) {
            try {
                FlightService flightService = getServiceFactory().getFlightService();
                flight = flightService.readById(id);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        } else
        {
            flight.setStatus(FlightStatus.NEW);
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                if (user.getUserRole() == UserRole.ADMIN) {
                    flight.setName(request.getParameter("name"));
                    flight.setDeparture(request.getParameter("departure"));
                    flight.setDestination(request.getParameter("destination"));
                    flight.setDate(Date.valueOf(request.getParameter("date")));
                    flight.setTime(Time.valueOf(request.getParameter("time") + ":00"));
                } else {
                    try {
                        flight.setStatus(FlightStatus.values()[Integer.parseInt(request.getParameter("flightStatus"))]);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        throw new ServletException(e);
                    }
                    Integer crewId = null;
                    try {
                        crewId = Integer.parseInt(request.getParameter("crewId"));
                    } catch (NumberFormatException e) {
                    }
                    if (crewId != null) {
                        try {
                            CrewService service = getServiceFactory().getCrewService();
                            Crew crew = service.readById(crewId);
                            flight.setCrew(crew);
                        } catch (ServiceException | ServiceFactoryException e) {
                            LOGGER.error(e.getMessage());
                            throw new ServletException(e);
                        }
                    }
                }
            }
        }
        if (flight.getName() != null && flight.getDeparture() != null
                && flight.getDestination() != null && flight.getDate() != null
                && flight.getTime() != null && flight.getStatus() != null) {
            try {
                FlightService flightService = getServiceFactory().getFlightService();
                flightService.save(flight);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        return Commands.FLIGHTLIST_COMMAND;
    }
}
