package ua.pirateparty.games.tanks.dao.interfaces;

import ua.pirateparty.games.tanks.server.entities.game.Activity;
import ua.pirateparty.games.tanks.server.entities.player.Player;
import ua.pirateparty.games.tanks.server.entities.game.Tank;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:50
 */

public interface ITankDao {

    public abstract Tank readDefault (int type);

    public abstract Tank create(Player player);

    public abstract ArrayList<Tank> readAll(Player player);

    public abstract Tank read (long tankId);

    public abstract void updateStats(Tank tank);

    public abstract void updateAI(ArrayList<Activity> tankAI, long tankId);

    public abstract void delete(Player player);
}
