package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.controller.userCommands.UserEditCommand;
import by.epam.ivanov.aviacompany.controller.userCommands.UserListCommand;
import by.epam.ivanov.aviacompany.controller.userCommands.UserSaveCommand;

import javax.servlet.ServletException;
import java.util.HashMap;

public class CommandMap {
    private static HashMap<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("/admin/userlist.html", new UserListCommand());
        commandMap.put("/admin/useredit.html", new UserEditCommand());
        commandMap.put("/admin/usersave.html", new UserSaveCommand());

        //todo: дописать остальные команды
    }

    public static Command getCommand(String name) throws ServletException{
        if (name != null || commandMap.containsKey(name))
            return commandMap.get(name);
        return null;
    }
}
