package server;

import commands.Command;
import commands.CommandsMap;
import entities.Player;
import global.Config;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static global.Static.outLn;
import static java.lang.System.arraycopy;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 15:34
 */

public class GameServerHandler extends SimpleChannelHandler {

    private Channel channel;
    private Player player;

    private static Map<Integer, Player> connected = new HashMap<>();
    private static Map<Long, Player> authorized = new HashMap<>();
    private static Map<Long, Player> zombie = new HashMap<>();

    private final String COMMAND_DELIMITER = ":";
    private final String PARAM_DELIMITER = ";";

    private Map<Integer, byte[]> commandsBytes = new HashMap<>();


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

        ChannelBuffer cb = (ChannelBuffer) e.getMessage();
        byte[] bytes = cb.toByteBuffer().array();

        String request = getCommand(bytes);

        if (Config.DEBUG)
            outLn(request);

        if (request!=null){
            String[] commandsStr = request.split(COMMAND_DELIMITER);
            for (String commandStr : commandsStr) {
                int index = commandStr.indexOf(PARAM_DELIMITER);
                String key;
                String param;

                if(index != -1)
                {
                    key = commandStr.substring(0, index);
                    param = commandStr.substring(index + 1, commandStr.length());
                } else
                {
                    key = commandStr;
                    param = "";
                }
                Command command;
                if((command = CommandsMap.get(key)) != null)
                {
                    command.execute(param, player);
                }
            }

        }

    }

    private String getCommand(byte[] bytes) throws UnsupportedEncodingException
    {
        bytes = getCommandsBytes(bytes);

        String request = new String(bytes, "UTF-8");
        if(request.endsWith(COMMAND_DELIMITER))
            return request;

        int indexOfDelimiter = request.lastIndexOf(COMMAND_DELIMITER);

        if(indexOfDelimiter == -1)
        {
            addCommandsBytes(bytes);
            return null;
        }

        if(indexOfDelimiter != request.length())
        {
            String extra = request.substring(indexOfDelimiter, request.length());
            addCommandsBytes(extra.getBytes());
            return getCommand(request.substring(0, indexOfDelimiter + 1).getBytes());
        } else
        {
            return request;
        }
    }

    private byte[] getCommandsBytes(byte[] bytes)
    {
        byte existBytes[] = commandsBytes.remove(channel.getId());

        if(existBytes != null)
        {
            byte[] tempBytes = new byte[bytes.length + existBytes.length];
            arraycopy(existBytes, 0, tempBytes, 0, existBytes.length);
            arraycopy(bytes, 0, tempBytes, existBytes.length, bytes.length);
            return tempBytes;
        } else
        {
            return bytes;
        }
    }

    private void addCommandsBytes(byte[] bytes) throws UnsupportedEncodingException
    {
        byte[] existBytes = commandsBytes.remove(channel.getId());

        if(existBytes == null)
        {
            if(isCommandValid(bytes))
                commandsBytes.put(channel.getId(), bytes);
        } else
        {
            byte[] tempBytes = new byte[bytes.length + existBytes.length];
            arraycopy(existBytes, 0, tempBytes, 0, existBytes.length);
            arraycopy(bytes, 0, tempBytes, existBytes.length, bytes.length);
            commandsBytes.put(channel.getId(), tempBytes);
        }
    }

    private boolean isCommandValid(byte[] bytes) throws UnsupportedEncodingException
    {
        String str = new String(bytes, "UTF-8");
        int index = str.indexOf(";");
        if(index == -1)
            index = str.length();
        return CommandsMap.contains(str.substring(0, index));
    }

    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception
    {
        channel = e.getChannel();
        player = connected.get(channel.getId());

        e.getCause().printStackTrace(System.out);
    }
}
