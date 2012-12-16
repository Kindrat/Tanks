package ua.pirateparty.games.tanks.server.entities.game;

import ua.pirateparty.games.tanks.server.entities.player.Player;

import java.util.ArrayList;
import java.util.Map;

import static ua.pirateparty.games.tanks.server.conf.Constants.fields.*;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 22.09.12
 * Time: 21:33
 */
public class Field {
    private int hashCode;
    private long width;
    private long height;
    private byte type;

    private Map<Long, Player> players;
    private Map<Long, Player> zombies;
    private ArrayList<Tank> greenTeam;
    private ArrayList<Tank> redTeam;

    public Field(byte type) {
        this.type=type;
        switch (type){
            case SMALL:
                break;
            case MEDIUM:
                break;
            case LARGE:
                break;
            default:
                throw new RuntimeException("Unknown field type");
        }

    }

    private void separatePlayersIntoTeams(){}

    public void startBattle(){}

    public void broadcast(Object obj){
        for (Player player : players.values()){
            player.sendData(obj);
        }
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
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

    public int hashCode(){
        return (int)((this.width+this.height+System.currentTimeMillis())/(2*this.type));
    }
}
