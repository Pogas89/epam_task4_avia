package by.epam.ivanov.aviacompany.controller.staffCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Department;
import by.epam.ivanov.aviacompany.entity.Staff;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.StaffService;
import by.epam.ivanov.aviacompany.util.Commands;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaffSaveCommand extends Command {
    private Logger LOGGER = Logger.getLogger(StaffSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Staff staff = new Staff();
        try {
            staff.setId(Integer.parseInt(request.getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.error("id not number");
        }
        staff.setFirstName(request.getParameter("firstName"));
        staff.setLastName(request.getParameter("lastName"));
        try {
            staff.setDepartment(Department.values()[Integer.parseInt(request.getParameter("department"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
        if (staff.getLastName() != null) {
            try {
                StaffService staffService = getServiceFactory().getStaffService();
                staffService.save(staff);
            } catch (ServiceFactoryException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }
        }
        return Commands.STAFFLIST_COMMAND;
    }
}
