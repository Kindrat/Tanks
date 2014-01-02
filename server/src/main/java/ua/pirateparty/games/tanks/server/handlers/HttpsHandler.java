package ua.pirateparty.games.tanks.server.handlers;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.ssl.SslHandler;
import ua.pirateparty.games.tanks.protocol.CommandManager;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import static ua.pirateparty.games.tanks.util.log.Loggers.globalLogger;

public class HttpsHandler extends SimpleChannelHandler {
    private Player player;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        Channel channel = e.getChannel();
        player = new Player(channel);
        globalLogger.debug("HttpsHandler.channelConnected\t" + channel.getId());
        SslHandler sslHandler = ctx.getPipeline().get(SslHandler.class);
        sslHandler.handshake();
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        globalLogger.debug("HttpsHandler.channelDisconnected\t" + e.getChannel().getId());
    }

    @Override
    public void messageReceived (ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Object msg = e.getMessage();
        /*Будем обрабатывать сообщение только если это http запрос*/
        if (msg instanceof HttpRequest) {
            handleHttpRequest((HttpRequest) msg, e.getChannel());
        }
    }

    private void handleHttpRequest(HttpRequest req, Channel channel) throws Exception {
        if (req.getMethod() == HttpMethod.POST) {
            ChannelBuffer content = req.getContent().copy();
            String request = new String(content.array(), "UTF-8");
            globalLogger.debug("HttpsHandler.handleHttpRequest. Command\t>>\t"+ request);
            CommandManager.execute(player, request);
        }else{
            channel.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        globalLogger.error("HttpsHandler.exceptionCaught\t"+e);
    }
}
