/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pirateparty.games.tanks.client.handlers;

import ua.pirateparty.games.tanks.protocols.amf.responses.Response;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 *
 * @author legioner
 */
public class AmfChannelHandler extends SimpleChannelHandler{
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Response response = (Response)e.getMessage();
        response.process();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
