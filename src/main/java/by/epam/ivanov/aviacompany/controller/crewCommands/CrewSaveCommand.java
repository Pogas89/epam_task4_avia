package by.epam.ivanov.aviacompany.controller.crewCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Crew;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.service.CrewService;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.util.Commands;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CrewSaveCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(CrewSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Crew crew = new Crew();
        try{
            crew.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e){
        }
        crew.setName(request.getParameter("name"));
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            crew.setUser(user);
        } else
            return Pages.ERROR_PAGE;
        if(crew.getName()==null && crew.getUser()==null)
            return Pages.ERROR_PAGE;
        else {
            try{
                CrewService crewService = getServiceFactory().getCrewService();
                crewService.save(crew);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        return Commands.CREWLIST_COMMAND;
    }
}
