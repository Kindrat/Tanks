package entities.game;

import entities.player.Player;

import java.util.ArrayList;
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
    private ArrayList<Player> players;

    private Map<Integer, Player> connected;
    private Map<Long, Player> authorized;
    private Map<Long, Player> zombies;
    private Map<Long, Player> bots;

    public Field(long width, long height, ArrayList<Player> players) {
        this.width=width;
        this.height=height;
        this.players=players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}
