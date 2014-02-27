package ua.pirateparty.games.tanks.server.protocol;

import ua.pirateparty.games.tanks.server.protocol.commands.CAuth;
import ua.pirateparty.games.tanks.server.entities.player.Player;
import tanks.util.log.Loggers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum CommandManager {
    AUTH {
        @Override
        protected Class<? extends Command> getFactory() {
            return CAuth.class;
        }
    };

    private static Set<CommandManager> commands = new HashSet<>(Arrays.asList(CommandManager.values()));

    protected abstract Class<? extends Command> getFactory ();

    public static void execute(Player player, String object) {
        Command command = player.getGson().fromJson(object, Command.class);
        if (commands.contains(command.getId())){
            command = player.getGson().fromJson(object, command.getId().getFactory());
            try{
                command.executeCommand(player);
            } catch (Exception e){
                Loggers.globalLogger.error("CommandManager.execute\t"+e.getMessage());
            }
        } else {
            player.onDisconnect();
        }
    }
}
