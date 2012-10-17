package ua.pirateparty.games.tanks.server.entities.game;

import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 22.09.12
 * Time: 21:33
 */
public class Field {
    private long width;
    private long height;

    private Map<Long, Player> players;
    private Map<Long, Player> zombies;

    public Field(long width, long height, Map<Long, Player> players) {
        this.width=width;
        this.height=height;
        this.players=players;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public Map<Long, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Long, Player> players) {
        this.players = players;
    }

    public Map<Long, Player> getZombies() {
        return zombies;
    }

    public void setZombies(Map<Long, Player> zombies) {
        this.zombies = zombies;
    }
}
