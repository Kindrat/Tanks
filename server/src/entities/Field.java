package entities;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.09.12
 * Time: 0:37
 */

public class Field {
    private double width;
    private double length;

    private Map<Long, Player> players;

    public Field(double width, double length, Map<Long, Player> players){
        this.length=length;
        this.width=width;
        this.players=players;

        startGame();
    }

    private void startGame() {
        //To change body of created methods use File | Settings | File Templates.
    }

}
