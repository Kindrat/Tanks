package commands.login;

import commands.Command;
import entities.Player;
import objects.login.OPing;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 16:48
 */
public class CPing extends Command {
    @Override
    public void execute(String s, Player player) {
        if (player.hasStatus(Player.NOT_LOGGED_IN)){
            String mes = "OK";
            player.sendData(new OPing(mes));
        }
    }
}
