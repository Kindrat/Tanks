package ua.pirateparty.games.tanks.server.entities.lobby;

import ua.pirateparty.games.tanks.server.entities.game.Field;

import java.util.HashMap;
import java.util.Map;

import static ua.pirateparty.games.tanks.server.conf.Constants.DEFAULT_ROOM_SIZE;

public class Room {

    private int id;
    private String name;
    private int fields;
    private byte fieldType;

    private Map<Integer, Field> gameFields;

    public Room (int id, String name, int fields, byte fieldType){
        this.id=id;
        this.name=name;
        this.fields=fields;
        this.fieldType=fieldType;

        if (fields==0){
            initDefaultGameFields();
        }else{
            initGameFields();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Field> getGameFields() {
        return gameFields;
    }

    private void initDefaultGameFields() {
        gameFields = new HashMap<>(DEFAULT_ROOM_SIZE);
        for (int field = 0; field < DEFAULT_ROOM_SIZE; field++){
            Field newField = new Field(this.fieldType);
            int hashCode = newField.hashCode();
            gameFields.put(hashCode, newField);
            newField.setHashCode(hashCode);
        }
    }

    private void initGameFields() {
        gameFields = new HashMap<>(fields);
        for (int field = 0; field < fields; field++){
            Field newField = new Field(this.fieldType);
            int hashCode = newField.hashCode();
            gameFields.put(hashCode, newField);
            newField.setHashCode(hashCode);
        }
    }

    public void addField(){
        Field newField = new Field(this.fieldType);
        int hashCode = newField.hashCode();
        gameFields.put(hashCode, newField);
        newField.setHashCode(hashCode);
    }

    public void removeField(int code){
        this.gameFields.remove(code);
    }
}
