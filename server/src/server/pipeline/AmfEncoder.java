package server.pipeline;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Output;
import global.Config;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static global.Static.outLn;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 14.10.12
 * Time: 22:20
 */
public class AmfEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        SerializationContext context = SerializationContext
                .getSerializationContext();

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

        if (Config.DEBUG){
            outLn("Message: "+msg.toString()+ "; size: "+bytes.length+" bytes");
        }

        amfOut.close();
        return buffer;
    }
}