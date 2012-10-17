package ua.pirateparty.games.tanks.dao.interfaces;

import ua.pirateparty.games.tanks.server.entities.player.Player;
import ua.pirateparty.games.tanks.server.entities.stat.TankStatistic;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 15.10.12
 * Time: 1:36
 */
public interface ITankStatisticDao {

    public abstract TankStatistic create(Player player);

    public abstract ArrayList<TankStatistic> readAll(Player player);

    public abstract TankStatistic read (Player player);

    public abstract void update(Player player);

    public abstract void delete(Player player);
}
