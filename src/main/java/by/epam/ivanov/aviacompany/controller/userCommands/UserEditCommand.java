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

public class UserEditCommand extends Command {
    private Logger LOGGER = Logger.getLogger(UserEditCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.debug("userEdit command start");
        Integer id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            LOGGER.debug("id=" + id);
        } catch (NumberFormatException e) {
            throw new ServletException(e);
        }
        if (id != null) {
            try {
                UserService userService = getServiceFactory().getUserService();
                User user = userService.readById(id);
                request.setAttribute("user", user);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        request.setAttribute("userRoles", UserRole.values());
        LOGGER.debug("userEdit command end with" + Pages.USEREDIT_PAGE);
        return Pages.USEREDIT_PAGE;
    }
}
