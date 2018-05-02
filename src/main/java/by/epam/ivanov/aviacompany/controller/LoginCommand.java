package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.UserService;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command {
    private Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            try {
                UserService userService = getServiceFactory().getUserService();
                User user = userService.readByLogin(login);
                if (user != null && password.equals(user.getPassword())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("currentUser", user);
                    return "/index.jsp";
                } else {
                    return Pages.LOGIN_PAGE + "?message=Login or password incorrect";
                }
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        } else
            return Pages.LOGIN_PAGE;
    }
}
