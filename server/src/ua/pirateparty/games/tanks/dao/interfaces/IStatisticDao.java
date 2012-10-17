package ua.pirateparty.games.tanks.dao.interfaces;

import ua.pirateparty.games.tanks.server.entities.player.Player;
import ua.pirateparty.games.tanks.server.entities.stat.Statistic;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:53
 */
public interface IStatisticDao {

    public abstract Statistic create (long playerId);

    public abstract Statistic read (long playerId);

    public abstract void update (Player player);

}
