/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pirateparty.games.tanks.client.decoders;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import ua.pirateparty.games.tanks.client.TanksClient;
import ua.pirateparty.games.tanks.protocols.amf.responses.Response;

/**
 *
 * @author legioner
 */
public class AmfDecoder extends FrameDecoder{

    public AmfDecoder() {
    }

    @Override
    protected Object decode(ChannelHandlerContext chc, Channel chnl, ChannelBuffer cb) throws Exception {
        SerializationContext context = SerializationContext.getSerializationContext();
        context.instantiateTypes = true;
        context.supportRemoteClass = true;

        Amf3Input amfIn = new Amf3Input(context);

        byte[] bytes = cb.toByteBuffer().array();

        InputStream is = new ByteArrayInputStream(bytes);
        amfIn.setInputStream(is);

        Object obj = null;
        int readedBytes = 0;

        amfIn.reset();

        try {
            obj = amfIn.readObject();
            readedBytes = bytes.length - amfIn.available();
            cb.readerIndex(readedBytes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        amfIn.close();
        StringBuilder out = new StringBuilder().append("Response\t>>\t").append(((Response)obj).toString()).append("\n");
        TanksClient.jConsole.append(out.toString());
        TanksClient.jConsole.setCaretPosition(TanksClient.jConsole.getDocument().getLength());
        return obj;
    }
}
