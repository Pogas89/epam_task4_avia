package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.util.Pages;
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

public class Controller extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(Controller.class);
    ConnectionPool pool;

    private void procces(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameOfCommand = req.getParameter("command");
        Command command = CommandMap.getCommand(nameOfCommand);

        String page = Pages.ERROR_PAGE;
        try (ServiceFactory factory = getServiceFactory()) {
            command.setServiceFactory(factory);
            page = command.execute(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    public ServiceFactory getServiceFactory() {
        return new ServiceFactoryImpl();
    }

    @Override
    public void init() throws ServletException {
        try {
            pool=ConnectionPool.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procces(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procces(req, resp);
    }
}
