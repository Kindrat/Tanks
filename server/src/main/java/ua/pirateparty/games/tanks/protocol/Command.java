package ua.pirateparty.games.tanks.protocol;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.pirateparty.games.tanks.server.entities.player.Player;

public class Command {
    protected CommandManager id;

    public CommandManager getId() {
        return id;
    }

    public void setId(CommandManager id) {
        this.id = id;
    }

    public void executeCommand(Player player){
        throw new NotImplementedException();
    }
}
