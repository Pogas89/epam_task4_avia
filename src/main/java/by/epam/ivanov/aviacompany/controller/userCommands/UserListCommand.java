package by.epam.ivanov.aviacompany.controller.userCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.UserService;
import by.epam.ivanov.aviacompany.util.PagedListHolderImpl;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListCommand extends Command {
    private Logger LOGGER = Logger.getLogger(UserListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try{
            UserService userService = getServiceFactory().getUserService();
            List<User> userList = userService.readActualUsers();
            PagedListHolderImpl<User> listHolder = new PagedListHolderImpl<>(userList);
            listHolder.setAttribut("userList");
            listHolder.setPadding(request);
            return Pages.USERLIST_PAGE;
        } catch (ServiceException | ServiceFactoryException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
    }
}
