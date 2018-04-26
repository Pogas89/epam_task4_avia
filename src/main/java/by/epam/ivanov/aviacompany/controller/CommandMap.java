package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.controller.filters.UserListCommand;

import javax.servlet.ServletException;
import java.util.HashMap;

public class CommandMap {
    private static HashMap<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("userlist", new UserListCommand());

        //todo: дописать остальные команды
    }

    public static Command getCommand(String name) throws ServletException{
        if (name != null || commandMap.containsKey(name))
            return commandMap.get(name);
        return null;
    }
}
