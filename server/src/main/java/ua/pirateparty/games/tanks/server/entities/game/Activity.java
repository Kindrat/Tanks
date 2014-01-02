package ua.pirateparty.games.tanks.server.entities.game;

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
