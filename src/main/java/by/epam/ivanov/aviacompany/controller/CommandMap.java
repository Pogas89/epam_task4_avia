package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.controller.crewCommands.CrewListCommand;
import by.epam.ivanov.aviacompany.controller.staffCommands.StaffDeleteCommand;
import by.epam.ivanov.aviacompany.controller.staffCommands.StaffEditCommand;
import by.epam.ivanov.aviacompany.controller.staffCommands.StaffListCommand;
import by.epam.ivanov.aviacompany.controller.staffCommands.StaffSaveCommand;
import by.epam.ivanov.aviacompany.controller.userCommands.UserDeleteCommand;
import by.epam.ivanov.aviacompany.controller.userCommands.UserEditCommand;
import by.epam.ivanov.aviacompany.controller.userCommands.UserListCommand;
import by.epam.ivanov.aviacompany.controller.userCommands.UserSaveCommand;
import by.epam.ivanov.aviacompany.util.Commands;

import javax.servlet.ServletException;
import java.util.HashMap;

class CommandMap {
    private static HashMap<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(Commands.USERLIST_COMMAND, new UserListCommand());
        commandMap.put(Commands.USEREDIT_COMMAND, new UserEditCommand());
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

        //todo: дописать остальные команды
    }

    static Command getCommand(String name) throws ServletException{
        if (name != null || commandMap.containsKey(name))
            return commandMap.get(name);
        return null;
    }
}
