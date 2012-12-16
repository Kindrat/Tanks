package ua.pirateparty.games.tanks.server.handlers;

import org.jboss.netty.channel.*;
import ua.pirateparty.games.tanks.protocols.amf.commands.Command;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import static ua.pirateparty.games.tanks.server.logic.OnlineUsersManager.authorized;
import static ua.pirateparty.games.tanks.server.logic.OnlineUsersManager.connected;
import static ua.pirateparty.games.tanks.util.log.Loggers.globalLogger;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 15:34
 */

public class GameServerHandler extends SimpleChannelHandler {

    public synchronized void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
    {
        Channel channel=e.getChannel();
        Player player = new Player(channel);
        connected.put(channel.getId(), player);
        globalLogger.debug("Channel connected "+channel.getId());
    }

    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
    {
        Channel channel=e.getChannel();
        Player player=connected.get(channel.getId());

        //TODO обработка дисконнекта для плеера;
        if (player != null && !player.hasStatus(Player.NOT_LOGGED_IN)){
            if (player.hasStatus(Player.IN_GAME)){

            }
            authorized.remove(player.getId());
        }
        connected.remove(channel.getId());
        globalLogger.debug("Channel disconnected "+channel.getId());
        player = null;
        channel = null;
    }

    public synchronized void messageReceived (ChannelHandlerContext ctx, MessageEvent e) throws Exception
    {
        Channel channel=e.getChannel();
        Player player=connected.get(channel.getId());
        Command command = (Command)e.getMessage();
        command.execute(player);

    }

    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception
    {
        globalLogger.error(e);
    }
}
