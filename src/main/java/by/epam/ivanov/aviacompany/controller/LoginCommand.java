package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.entity.UserRole;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.UserService;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.PasswordToHash;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            password = PasswordToHash.getHashSha256(password);
            try {
                UserService userService = getServiceFactory().getUserService();
                User user = userService.readByLogin(login);
                if (user != null && password.equals(user.getPassword())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("currentUser", user);
                    LOGGER.info("user authorized:" + user.getLogin() + " with role: " + user.getUserRole());
                    if (user.getUserRole().equals(UserRole.ADMIN)) {
                        return Pages.ADMIN_PAGE;
                    } else if (user.getUserRole().equals(UserRole.DISPETCHER)) {
                        return Pages.DISPETCHER_PAGE;
                    }
                } else {
                    return Pages.LOGIN_PAGE + "?message=app.login.incorrect";
                }
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        } else
            return Pages.LOGIN_PAGE;
        return null;
    }
}
