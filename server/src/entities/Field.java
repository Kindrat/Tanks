package entities;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.09.12
 * Time: 0:37
 */

public class Field {
    private double width;
    private double length;

    private ArrayList<Tank> tanks;

    public Field(double width, double length, ArrayList<Tank> tanks){
        this.length=length;
        this.width=width;
        this.tanks=tanks;

        startGame();
    }

    private void startGame() {
        //To change body of created methods use File | Settings | File Templates.
    }

}
