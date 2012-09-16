package entities;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import util.amf.Amf3;

import java.util.ArrayList;

import static global.Config.DEBUG;
import static global.Static.outLn;

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
    private int tankId;
    private byte status;
    private int winCount;
    private int kills;
    private long exp;
    private long money;
    private int level;

    private Tank tank;
    private ArrayList<String> moves;

    private String passHash;

    public static byte NOT_LOGGED_IN = 0;
    public static byte LOGGED_IN = 1;
    public static byte IN_GAME = 2;

    public Player (Channel channel){
        this.channel=channel;
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

    public int getTankId() {
        return tankId;
    }

    public void setTankId(int tankId) {
        this.tankId = tankId;
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

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
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
            int size = 0;
            String traceMessage = "";
            byte bytes[] = Amf3.encode(obj);
            ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
            buffer.writeBytes(bytes);
            channel.write(buffer);
            size = bytes.length;
            traceMessage = obj.toString();

            if (DEBUG)
                outLn("Message: "+ traceMessage+ "; size: "+size+" bytes");
        }
    }

    public void hang() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
