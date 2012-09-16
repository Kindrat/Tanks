package commands;

import entities.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 16:24
 */
public abstract class Command {

    public Command()
    {
    }

    public abstract void execute(String s,Player player);

    protected String[] splitParam(String param)
    {
        return param.split(";");
    }
}
