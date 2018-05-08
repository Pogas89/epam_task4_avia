package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.util.Pages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainCommand extends Command {
    private Logger LOGGER = Logger.getLogger(MainCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                LOGGER.debug("current user:" + user.getLogin());
                switch (user.getUserRole()) {
                    case ADMIN:
                        return Pages.ADMIN_PAGE;
                    case DISPETCHER:
                        return Pages.DISPETCHER_PAGE;
                }
            }
        }
        return Pages.LOGIN_PAGE;
    }
}
