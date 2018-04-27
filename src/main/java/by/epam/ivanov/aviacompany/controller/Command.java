package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    private static Logger LOGGER = Logger.getLogger(Command.class);
    private ServiceFactory serviceFactory;

    public ServiceFactory getServiceFactory() {
        LOGGER.debug("Command-GetServiceFactory");
        return serviceFactory;
    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public abstract Forward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException;
}
