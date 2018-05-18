package by.epam.ivanov.aviacompany.controller.flightCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.service.FlightService;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.util.Commands;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlightDeleteCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(FlightDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error("Id not number");
        }
        if (id != null){
            try {
                FlightService flightService = getServiceFactory().getFlightService();
                flightService.delete(id);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        return Commands.FLIGHTLIST_COMMAND;
    }
}
