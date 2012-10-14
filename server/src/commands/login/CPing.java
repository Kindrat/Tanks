package commands.login;

import commands.Command;
import entities.player.Player;
import global.Config;
import response.login.SPing;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 16:48
 */
public class CPing extends Command {

    @Override
    public void execute(Player player) {
        if (player.hasStatus(Player.NOT_LOGGED_IN)){
            player.sendData(new SPing(Config.SERVER_VERSION, Config.STATUS_MESSAGE));
        }
    }
}
