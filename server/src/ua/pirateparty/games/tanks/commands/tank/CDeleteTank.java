package ua.pirateparty.games.tanks.commands.tank;

import ua.pirateparty.games.tanks.commands.Command;
import ua.pirateparty.games.tanks.server.entities.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 15.10.12
 * Time: 1:34
 */
public class CDeleteTank extends Command {

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void execute(Player player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
