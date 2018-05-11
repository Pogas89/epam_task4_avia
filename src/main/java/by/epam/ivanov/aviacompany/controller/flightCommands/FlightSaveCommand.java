package by.epam.ivanov.aviacompany.controller.flightCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.Flight;
import by.epam.ivanov.aviacompany.entity.FlightStatus;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.FlightService;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.util.Commands;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;

public class FlightSaveCommand extends Command {
    private Logger LOGGER = Logger.getLogger(FlightSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Flight flight = new Flight();
        try {
            flight.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.error("id not number");
        }
        flight.setName(request.getParameter("name"));
        flight.setDeparture(request.getParameter("departure"));
        flight.setDestination(request.getParameter("destination"));
        flight.setDate(Date.valueOf(request.getParameter("date")));
        flight.setTime(Time.valueOf(request.getParameter("time")+":00"));//todo: исправить ошибку
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
        if (flight.getName() != null && flight.getDeparture() != null
                && flight.getDestination() != null && flight.getDate() != null
                && flight.getTime() != null && flight.getStatus() != null
                && flight.getCrew() != null){
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
