package ua.pirateparty.games.tanks.commands.tank;

import ua.pirateparty.games.tanks.commands.Command;
import ua.pirateparty.games.tanks.server.entities.game.Activity;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 15.10.12
 * Time: 0:44
 */
public class CNewTank extends Command {
    private int type;
    private ArrayList<Activity> tankAI;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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
