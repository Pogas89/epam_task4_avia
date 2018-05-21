package by.epam.ivanov.aviacompany.controller.userCommands;

import by.epam.ivanov.aviacompany.controller.Command;
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

public class UserSavePassword extends Command {
    private final Logger LOGGER = Logger.getLogger(UserSavePassword.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.debug("userSavePassword command start");
        User user = new User();
        try {
            user.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.info("id not number");
            throw new ServletException(e);
        }
        String password = request.getParameter("password");
        if (password != null) {
            password = PasswordToHash.getHashSha256(password);
            user.setPassword(password);
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser != null && user.getPassword() != null) {
                LOGGER.debug(currentUser);
                try {
                    UserService userService = getServiceFactory().getUserService();
                    userService.savePassword(currentUser.getId(), password);
                    LOGGER.debug("userSavePassword command end");
                    if (currentUser.getUserRole() == UserRole.ADMIN) return Pages.ADMIN_PAGE;
                    else return Pages.DISPETCHER_PAGE;
                } catch (ServiceFactoryException | ServiceException e) {
                    LOGGER.error(e.getMessage());
                    throw new ServletException(e);
                }
            }
        }
        return Pages.ERROR_PAGE;
    }
}
