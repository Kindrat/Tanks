package commands;

import entities.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 16:24
 */
public abstract class Command {

    public Command() {}

    public abstract void execute(Player player);

}
