package ua.pirateparty.games.tanks.protocols.amf.commands.login;

import ua.pirateparty.games.tanks.protocols.amf.commands.Command;
import ua.pirateparty.games.tanks.protocols.amf.responses.login.SPing;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import static ua.pirateparty.games.tanks.server.conf.Constants.SERVER_VERSION;
import static ua.pirateparty.games.tanks.server.conf.Constants.STATUS_MESSAGE;

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
            player.sendData(new SPing(SERVER_VERSION, STATUS_MESSAGE));
        }
    }

    @Override
    public String toString() {
        return "CPing{}";
    }
}
