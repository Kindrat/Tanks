package ua.pirateparty.games.tanks.server.entities.player;

import org.jboss.netty.channel.Channel;
import ua.pirateparty.games.tanks.server.entities.game.Field;
import ua.pirateparty.games.tanks.server.entities.game.Tank;
import ua.pirateparty.games.tanks.server.entities.stat.Statistic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GamePlayer extends Player {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private byte status;
    private long exp;
    private int level;

    private int roomId;
    private Tank tank;
    private Statistic statistic;
    private Field field;

    private char[] passHash;

    public GamePlayer(Player player) {
        this(player.channel);
    }

    public GamePlayer(Channel channel){
        super(channel);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public char[] getPassHash() {
        return passHash;
    }

    public void setPassHash(char[] hash) {
        this.passHash = hash;
    }
}
