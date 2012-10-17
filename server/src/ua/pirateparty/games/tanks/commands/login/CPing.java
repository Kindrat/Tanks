package ua.pirateparty.games.tanks.commands.login;

import ua.pirateparty.games.tanks.commands.Command;
import ua.pirateparty.games.tanks.server.entities.player.Player;
import ua.pirateparty.games.tanks.global.Config;
import ua.pirateparty.games.tanks.response.login.SPing;

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

    @Override
    public String toString() {
        return "CPing{}";
    }
}
