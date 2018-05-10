package by.epam.ivanov.aviacompany.controller.crewCommands;

import by.epam.ivanov.aviacompany.controller.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddStaffInCrewCommand extends Command {
    private Logger LOGGER =Logger.getLogger(AddStaffInCrewCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return null;
    }
}
