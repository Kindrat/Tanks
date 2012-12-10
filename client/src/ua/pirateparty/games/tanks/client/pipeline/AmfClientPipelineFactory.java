/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pirateparty.games.tanks.client.pipeline;

import ua.pirateparty.games.tanks.client.decoders.AmfDecoder;
import ua.pirateparty.games.tanks.client.encoders.AmfEncoder;
import ua.pirateparty.games.tanks.client.handlers.AmfChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

/**
 *
 * @author legioner
 */
public class AmfClientPipelineFactory implements ChannelPipelineFactory{

    @Override
    public ChannelPipeline getPipeline() throws Exception {
                AmfDecoder decoder = new AmfDecoder();
		AmfEncoder encoder = new AmfEncoder();

		ChannelPipeline pipeline = Channels.pipeline();

		pipeline.addLast("decoder", decoder);
		pipeline.addLast("encoder", encoder);
		pipeline.addLast("handler", new AmfChannelHandler());

		return pipeline;
    }
    
}
