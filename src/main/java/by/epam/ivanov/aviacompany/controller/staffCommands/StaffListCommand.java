package by.epam.ivanov.aviacompany.controller.staffCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import by.epam.ivanov.aviacompany.entity.Staff;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.StaffService;
import by.epam.ivanov.aviacompany.util.PagedListHolderImpl;
import by.epam.ivanov.aviacompany.util.Pages;
import by.epam.ivanov.aviacompany.util.serviceFactory.ServiceFactoryException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StaffListCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(StaffListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            StaffService staffService = getServiceFactory().getStaffService();
            List<Staff> staffList = staffService.readActualStaffs();
            PagedListHolderImpl<Staff> listHolder = new PagedListHolderImpl<>(staffList);
            listHolder.setAttribut("staffList");
            listHolder.setPadding(request);
            return Pages.STAFFLIST_PAGE;
        } catch (ServiceFactoryException | ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
    }
}
