package dao.interfaces;

import entities.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 11.09.12
 * Time: 1:59
 */

public interface IPlayerDao {

    public abstract Player create(Player player);

    public abstract Player read(Player player);

    public abstract void update(Player player);

    public abstract void delete(Player player);

}
