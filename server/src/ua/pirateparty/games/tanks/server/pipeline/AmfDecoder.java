package ua.pirateparty.games.tanks.server.pipeline;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import ua.pirateparty.games.tanks.commands.Command;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static ua.pirateparty.games.tanks.util.tracer.Tracer.trace;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 14.10.12
 * Time: 22:17
 */
public class AmfDecoder extends ReplayingDecoder {

    public AmfDecoder(){
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, Enum state) throws Exception {
        SerializationContext context = SerializationContext.getSerializationContext();
        context.instantiateTypes = true;
        context.supportRemoteClass = true;

        Amf3Input amfIn = new Amf3Input(context);

        byte[] bytes = this.cumulation.array();



        InputStream is = new ByteArrayInputStream(bytes);
        amfIn.reset();
        amfIn.setInputStream(is);

        Object obj = null;
        int readBytes;

        try {
            obj = amfIn.readObject();
            readBytes = bytes.length-amfIn.available();
            buffer.readerIndex(readBytes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.reset();
        amfIn.close();

        trace((Command) obj);

        return obj;    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        ctx.sendUpstream(e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        ctx.sendUpstream(e);
    }


}
