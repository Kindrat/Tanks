package ua.pirateparty.games.tanks.protocols.amf.commands.tank;

import ua.pirateparty.games.tanks.protocols.amf.commands.Command;
import ua.pirateparty.games.tanks.server.entities.game.Activity;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.10.12
 * Time: 22:35
 */
public class CChangeTank extends Command {

    private ArrayList<Activity> tankAI;

    public ArrayList<Activity> getTankAI() {
        return tankAI;
    }

    public void setTankAI(ArrayList<Activity> tankAI) {
        this.tankAI = tankAI;
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
