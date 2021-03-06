package by.epam.ivanov.aviacompany.controller.crewCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.service.CrewService;
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

public class CrewListCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(CrewListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            LOGGER.debug("in crew list command");
            CrewService crewService = getServiceFactory().getCrewService();
            List<Crew> crewList = crewService.readActualCrews();
            if(!crewList.isEmpty()){
                UserService userService = getServiceFactory().getUserService();
                Integer id;
                User user;
                for (Crew c:crewList) {
                    user = c.getUser();
                    id = user.getId();
                    user = userService.readById(id);
                    c.setUser(user);
                }

            }
            PagedListHolderImpl<Crew> listHolder = new PagedListHolderImpl<>(crewList);
            listHolder.setAttribut("crewList");
            listHolder.setPadding(request);
            return Pages.CREWLIST_PAGE;
        } catch (ServiceFactoryException | ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
    }
}
