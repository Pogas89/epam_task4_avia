package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.util.connection.ConnectionPool;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactory;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static by.epam.ivanov.aviacompany.util.Pages.ERROR_PAGE;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    private ConnectionPool pool;

    private void procces(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Controller starts");
        String url = req.getRequestURI();
        Command command = CommandMap.getCommand(url);
        LOGGER.debug("Controller has command " + url);

        String page = ERROR_PAGE;
        if (command != null) {
            try (ServiceFactory factory = getServiceFactory()) {
                LOGGER.debug("Controller has ServiceFactory" + factory);
                command.setServiceFactory(factory);
                LOGGER.debug("command=" + command);
                page = command.execute(req, resp);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }

        LOGGER.debug("controller get RD" + page);
        req.getRequestDispatcher(page).forward(req, resp);
        LOGGER.debug("Controller end with page" + page);
    }

    private ServiceFactory getServiceFactory() {
        return new ServiceFactoryImpl();
    }

    @Override
    public void init() throws ServletException {
        LOGGER.debug("Contrroller start init");
        try {
            pool = ConnectionPool.getInstance();
            LOGGER.debug("Contrroller end init");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procces(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procces(req, resp);
    }
}
