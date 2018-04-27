package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.controller.staffCommands.StaffListCommand;
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
        commandMap.put(Commands.STAFFLIST_COMMAND, new StaffListCommand());

        //todo: дописать остальные команды
    }

    static Command getCommand(String name) throws ServletException{
        if (name != null || commandMap.containsKey(name))
            return commandMap.get(name);
        return null;
    }
}
