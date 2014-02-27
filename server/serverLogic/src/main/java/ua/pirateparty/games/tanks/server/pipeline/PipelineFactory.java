package ua.pirateparty.games.tanks.server.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.ssl.SslHandler;
import ua.pirateparty.games.tanks.server.SslContext;
import ua.pirateparty.games.tanks.server.handlers.HttpsHandler;

import javax.net.ssl.SSLEngine;

public class PipelineFactory implements ChannelPipelineFactory {

    /**
     * Переопределяем метод предка getPipeline
     * @return {@link ChannelPipeline}
     * @throws Exception
     */
    @Override
    public ChannelPipeline getPipeline() throws Exception {
        /**
         * Отталкиваемся от стандартного пайплайна
         * и поочерёдно добавляем в него:
         * декодер {@link org.jboss.netty.handler.codec.http.HttpRequestDecoder},
         * аггрегатор {@link org.jboss.netty.handler.codec.http.HttpChunkAggregator},
         * энкодер {@link org.jboss.netty.handler.codec.http.HttpResponseEncoder},
         * хендлер {@link com.globalplaygames.casino.slot.server.handlers.HttpHandler}
         **/
        ChannelPipeline pipeline = Channels.pipeline();
        SSLEngine sslEngine = SslContext.getInstance().getServerContext().createSSLEngine();
        sslEngine.setUseClientMode(false);
        pipeline.addLast("ssl", new SslHandler(sslEngine));
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("aggregator", new HttpChunkAggregator(65536));
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("handler", new HttpsHandler());

        return pipeline;
    }
}