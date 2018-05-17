package by.epam.ivanov.aviacompany.util;

import by.epam.ivanov.aviacompany.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListner implements HttpSessionListener {
    private Logger LOGGER = Logger.getLogger(SessionListner.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOGGER.debug("Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        User user = (User) httpSessionEvent.getSession().getAttribute("currentUser");
        LOGGER.info("Session with user: " + user.getLogin() + "destroyed");
    }
}
