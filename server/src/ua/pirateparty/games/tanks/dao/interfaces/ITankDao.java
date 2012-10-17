package ua.pirateparty.games.tanks.dao.interfaces;

import ua.pirateparty.games.tanks.server.entities.player.Player;
import ua.pirateparty.games.tanks.server.entities.game.Tank;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:50
 */

public interface ITankDao {

    public abstract Tank readDefault (int type);

    public abstract void createCustom (Player player);

    public abstract Tank readCustom (long playerId);

    public abstract void updateCustom (Player player);
}
