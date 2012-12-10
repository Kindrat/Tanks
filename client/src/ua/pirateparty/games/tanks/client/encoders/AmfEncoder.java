/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pirateparty.games.tanks.client.encoders;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Output;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import ua.pirateparty.games.tanks.client.TanksClient;
import ua.pirateparty.games.tanks.protocols.amf.commands.Command;
import ua.pirateparty.games.tanks.protocols.amf.responses.Response;

/**
 *
 * @author legioner
 */
public class AmfEncoder extends OneToOneEncoder{
    
    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        SerializationContext context = SerializationContext.getSerializationContext();

        Amf3Output amfOut = new Amf3Output(context);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        amfOut.setOutputStream(stream);

        try {
            amfOut.writeObject(msg);
            amfOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = stream.toByteArray();
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeBytes(bytes);
        
        StringBuilder out = new StringBuilder().append("Command\t>>\t").append(((Command)msg).toString()).append("\n");
        TanksClient.jConsole.append(out.toString());
        TanksClient.jConsole.setCaretPosition(TanksClient.jConsole.getDocument().getLength());
        amfOut.close();
        return buffer;
    }
}
