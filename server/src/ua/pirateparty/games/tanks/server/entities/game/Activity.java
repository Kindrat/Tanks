package ua.pirateparty.games.tanks.server.entities.game;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 18.10.12
 * Time: 0:07
 */
public class Activity {
    private int type;
    private String[] args;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
