package by.epam.ivanov.aviacompany.controller.userCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.controller.Forward;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.UserService;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(UserListCommand.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try{
            UserService userService = getServiceFactory().getUserService();
            List<User> userList = userService.readUsers();
            request.setAttribute("userList", userList);
            return new Forward(Pages.USERLIST_PAGE, false);
        } catch (ServiceException | ServiceFactoryException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
    }
}
