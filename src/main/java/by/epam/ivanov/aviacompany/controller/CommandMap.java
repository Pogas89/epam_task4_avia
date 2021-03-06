package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.controller.crewCommands.*;
import by.epam.ivanov.aviacompany.controller.flightCommands.FlightDeleteCommand;
import by.epam.ivanov.aviacompany.controller.flightCommands.FlightEditCommand;
import by.epam.ivanov.aviacompany.controller.flightCommands.FlightListCommand;
import by.epam.ivanov.aviacompany.controller.flightCommands.FlightSaveCommand;
import by.epam.ivanov.aviacompany.controller.staffCommands.StaffDeleteCommand;
import by.epam.ivanov.aviacompany.controller.staffCommands.StaffEditCommand;
import by.epam.ivanov.aviacompany.controller.staffCommands.StaffListCommand;
import by.epam.ivanov.aviacompany.controller.staffCommands.StaffSaveCommand;
import by.epam.ivanov.aviacompany.controller.userCommands.*;
import by.epam.ivanov.aviacompany.util.Commands;

import java.util.HashMap;

class CommandMap {
    private static final HashMap<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(Commands.USERLIST_COMMAND, new UserListCommand());
        commandMap.put(Commands.USEREDIT_COMMAND, new UserEditCommand());
        commandMap.put(Commands.USEREDITPASS_COMMAND, new UserEditPassword());
        commandMap.put(Commands.USERSAVEPASS_COMMAND, new UserSavePassword());
        commandMap.put(Commands.USERSETDEFPASS_COMMAND, new UserSetDefaultPassword());
        commandMap.put(Commands.USERSAVE_COMMAND, new UserSaveCommand());
        commandMap.put(Commands.USERDELETE_COMMAND, new UserDeleteCommand());
        commandMap.put(Commands.STAFFLIST_COMMAND, new StaffListCommand());
        commandMap.put(Commands.STAFFEDIT_COMMAND, new StaffEditCommand());
        commandMap.put(Commands.STAFFSAVE_COMMAND, new StaffSaveCommand());
        commandMap.put(Commands.STAFFDELETE_COMMAND, new StaffDeleteCommand());
        commandMap.put(Commands.LOGIN_COMMAND, new LoginCommand());
        commandMap.put(Commands.LOGOUT_COMMAND, new LogoutCommand());
        commandMap.put(Commands.MAIN_COMMAND, new MainCommand());
        commandMap.put(Commands.INDEX_PAGE_COMMAND, new MainCommand());
        commandMap.put(Commands.CREWLIST_COMMAND, new CrewListCommand());
        commandMap.put(Commands.CREWEDIT_COMMAND, new CrewEditCommand());
        commandMap.put(Commands.CREWSAVE_COMMAND, new CrewSaveCommand());
        commandMap.put(Commands.CREWDELETE_COMMAND, new CrewDeleteCommand());
        commandMap.put(Commands.CREWSHOW_COMMAND, new CrewShowCommand());
        commandMap.put(Commands.ADDSTAFFINCREW_COMMAND, new AddStaffInCrewCommand());
        commandMap.put(Commands.DELSTAFFFROMCREW_COMMAND, new DeleteStaffFromCrewCommand());
        commandMap.put(Commands.FLIGHTLIST_COMMAND, new FlightListCommand());
        commandMap.put(Commands.FLIGHTEDIT_COMMAND, new FlightEditCommand());
        commandMap.put(Commands.FLIGHTSAVE_COMMAND, new FlightSaveCommand());
        commandMap.put(Commands.FLIGHTDELETE_COMMAND, new FlightDeleteCommand());
    }

    static Command getCommand(String name){
        if (name != null || commandMap.containsKey(name))
            return commandMap.get(name);
        return null;
    }
}
