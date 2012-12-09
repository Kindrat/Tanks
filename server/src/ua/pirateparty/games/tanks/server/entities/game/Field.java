package ua.pirateparty.games.tanks.server.entities.game;

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

    private Map<Long, Tank> players;
    private Map<Long, Tank> zombies;

    public Field(long width, long height, Map<Long, Tank> players) {
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

    public Map<Long, Tank> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Long, Tank> players) {
        this.players = players;
    }

    public Map<Long, Tank> getZombies() {
        return zombies;
    }

    public void setZombies(Map<Long, Tank> zombies) {
        this.zombies = zombies;
    }
}
