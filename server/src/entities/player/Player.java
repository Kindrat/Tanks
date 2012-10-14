package entities.player;

import entities.game.Field;
import entities.game.Tank;
import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 15:36
 */
public class Player {

    private Channel channel;
    private long id;
    private String name;
    private byte status;
    private long exp;
    private int level;

    private Tank tank;
    private Statistic statistic;
    private Field field;

    private String passHash;

    public static byte NOT_LOGGED_IN = 0;
    public static byte LOGGED_IN = 1;
    public static byte IN_GAME = 2;

    public Player (Channel channel){
        this.channel=channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public boolean hasStatus(byte status) {
        return this.status == status;
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

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String hash) {
        this.passHash = hash;
    }

    public void sendData(Object obj) {
        if (channel != null && channel.isConnected() && channel.isOpen()) {
            channel.write(obj);
        }
    }
}
