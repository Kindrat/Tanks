package ua.pirateparty.games.tanks.server.logic;

import ua.pirateparty.games.tanks.server.entities.game.Field;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 14.10.12
 * Time: 22:23
 */
public class Logic extends Thread{
    private Field field;

    public Logic(Field field){
        this.field=field;
    }

    public void run(){

    }
}
