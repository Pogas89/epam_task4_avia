package by.epam.ivanov.aviacompany.controller.userCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.entity.UserRole;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.UserService;
import by.epam.ivanov.aviacompany.util.Commands;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.PasswordToHash;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSaveCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(UserSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        User user = new User();
        try {
            user.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.info("id not number");
        }
        Integer url = null;
        try {
            url = Integer.parseInt(request.getParameter("url"));
        } catch (NumberFormatException e) {
            LOGGER.info("url not number");
        }
        LOGGER.debug("url from request " + url);
        user.setLogin(request.getParameter("login"));
        String password = request.getParameter("password");
        if (password != null) {
            password = PasswordToHash.getHashSha256(password);
            user.setPassword(password);
        }
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        try {
            user.setUserRole(UserRole.values()[Integer.parseInt(request.getParameter("userRole"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ServletException(e);
        }
        if (user.getLogin() != null && user.getPassword() != null
                && user.getFirstName() != null && user.getLastName() != null
                && user.getEmail() != null && user.getUserRole() != null) {
            try {
                UserService userService = getServiceFactory().getUserService();
                userService.save(user);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        if (url != null) {
            if (url == UserRole.ADMIN.ordinal()) return Pages.ADMIN_PAGE;
            if (url == UserRole.DISPETCHER.ordinal()) return Pages.DISPETCHER_PAGE;
        }
        return Commands.USERLIST_COMMAND;
    }
}
