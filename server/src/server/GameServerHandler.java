package server;

import commands.Command;
import entities.player.Player;
import org.jboss.netty.channel.*;

import static global.Static.authorized;
import static global.Static.connected;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 15:34
 */

public class GameServerHandler extends SimpleChannelHandler {

    private Channel channel;
    private Player player;



    public synchronized void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
    {
        channel=e.getChannel();
        player = new Player(channel);
        connected.put(channel.getId(), player);
    }

    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
    {
        channel=e.getChannel();
        player=connected.get(channel.getId());

        //TODO обработка дисконнекта для плеера;
        if (player != null && !player.hasStatus(Player.NOT_LOGGED_IN)){
            if (player.hasStatus(Player.IN_GAME)){

            }
            authorized.remove(player.getId());
        }
        connected.remove(channel.getId());
        player = null;
        channel = null;
    }

    public synchronized void messageReceived (ChannelHandlerContext ctx, MessageEvent e) throws Exception
    {
        channel=e.getChannel();
        player=connected.get(channel.getId());
        Command command = (Command)e.getMessage();
        command.execute(player);

    }

    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception
    {
        channel = e.getChannel();
        player = connected.get(channel.getId());

        e.getCause().printStackTrace(System.out);
    }
}
