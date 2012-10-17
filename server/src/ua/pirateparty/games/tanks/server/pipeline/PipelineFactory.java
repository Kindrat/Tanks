package ua.pirateparty.games.tanks.server.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import ua.pirateparty.games.tanks.server.GameServerHandler;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 14.10.12
 * Time: 22:21
 */
public class PipelineFactory implements ChannelPipelineFactory {

    @Override
    public ChannelPipeline getPipeline() throws Exception {

        AmfDecoder decoder = new AmfDecoder();
        AmfEncoder encoder = new AmfEncoder();

        ChannelPipeline pipeline = Channels.pipeline();

        pipeline.addLast("decoder", decoder);
        pipeline.addLast("encoder", encoder);
        pipeline.addLast("handler", new GameServerHandler());

        return pipeline;
    }
}