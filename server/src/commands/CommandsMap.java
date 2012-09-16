package commands;

import commands.login.CPing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 16:40
 */

public class CommandsMap {
    private static final Map<String, Command> commands = new HashMap<>();

    static{
        commands.put("*", new CPing());

    }

    public CommandsMap()
    {
    }

    public static synchronized Command get(String key)
    {
        return commands.get(key);
    }

    public static synchronized boolean contains(String key)
    {
        return commands.containsKey(key);
    }
}
