package by.epam.ivanov.aviacompany.controller.userCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.entity.UserRole;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.UserService;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserEditPassword extends Command {
    private final Logger LOGGER = Logger.getLogger(UserEditPassword.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.debug("userEditPassword command start");
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error("id is not a number, throw exception");
            throw new ServletException(e);
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            User currentUser = (User) session.getAttribute("currentUser");
            LOGGER.error("userEditPassword currnetUser " + currentUser.getLogin());
            if (currentUser != null) {
                try {
                    UserService userService = getServiceFactory().getUserService();
                    User user = userService.readById(id);
                    if (user.getUserRole() == currentUser.getUserRole()) {
                        request.setAttribute("user", user);
                        LOGGER.debug("userEditPassword command end with" + Pages.USEREDITPASS_PAGE);
                        return Pages.USEREDITPASS_PAGE;
                    }
                } catch (ServiceFactoryException | ServiceException e) {
                    LOGGER.error(e.getMessage());
                    throw new ServletException(e);
                }
            }
        }
        return Pages.ERROR_PAGE;
    }

}
