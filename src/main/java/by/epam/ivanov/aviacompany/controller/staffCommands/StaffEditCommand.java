package by.epam.ivanov.aviacompany.controller.staffCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Department;
import by.epam.ivanov.aviacompany.entity.Staff;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.StaffService;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaffEditCommand extends Command {
    private Logger LOGGER = Logger.getLogger(StaffEditCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.debug("StaffEditCommand start");
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            LOGGER.error("id is not a number");
        }
        if (id != null){
            try {
                StaffService staffService = getServiceFactory().getStaffService();
                Staff staff = staffService.readById(id);
                request.setAttribute("staff", staff);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        request.setAttribute("departments", Department.values());
        LOGGER.debug("StaffEditCommand end with" + Pages.STAFFEDIT_PAGE);
        return Pages.STAFFEDIT_PAGE;
    }
}
