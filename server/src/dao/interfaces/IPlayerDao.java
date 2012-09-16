package dao.interfaces;

import entities.Player;
import entities.Tank;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 1:59
 */

public interface IPlayerDao {

    public abstract Player createPlayer(Player player);

    public abstract Player readPlayer(Player player);

    public abstract void updatePlayer(Player player);

    public abstract Tank getTank(int tankId);
}
