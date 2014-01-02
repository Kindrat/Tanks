package ua.pirateparty.games.tanks.server.logic;

import ua.pirateparty.games.tanks.server.entities.game.Field;

public class Logic extends Thread{
    private Field field;

    public Logic(Field field){
        this.field=field;
    }

    public void run(){

    }
}
