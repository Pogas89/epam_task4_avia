package by.epam.ivanov.aviacompany.controller.userCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.User;
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

public class UserSetDefaultPassword extends Command {
    private final Logger LOGGER = Logger.getLogger(UserSetDefaultPassword.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.debug("UserSetDefaultPassword command start");
        User user = new User();
        try {
            user.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.info("id not number");
        }
        //Default Password
        String password = "111111";
        password = PasswordToHash.getHashSha256(password);
        user.setPassword(password);
        if (user != null) {
            try {
                UserService userService = getServiceFactory().getUserService();
                userService.savePassword(user.getId(), password);
                LOGGER.debug("UserSetDefaultPassword command end");
                return Commands.USERLIST_COMMAND;
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        return Pages.ERROR_PAGE;
    }
}
