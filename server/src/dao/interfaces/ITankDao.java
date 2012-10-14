package dao.interfaces;

import entities.player.Player;
import entities.game.Tank;

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
