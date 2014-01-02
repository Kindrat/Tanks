package ua.pirateparty.games.tanks.server.entities.player;

import com.google.gson.Gson;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import ua.pirateparty.games.tanks.util.log.Loggers;

public class Player {

    protected Channel channel;
    protected State state;
    protected Gson gson;


    public Player (Channel channel){
        this.channel = channel;
        this.state = State.NOT_LOGGED_IN;
        this.gson = new Gson();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean hasState(State state){
        return this.state.equals(state);
    }

    public Gson getGson() {
        return gson;
    }

    public void onDisconnect() {
        channel.close();
    }

    public void sendData(Object obj) {
        String json = gson.toJson(obj);
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        response.setContent(buffer);
        buffer.writeBytes(json.getBytes());
        if (channel.isConnected() && channel.isOpen()) {
            Loggers.globalLogger.debug("Player.sendData\t>>\t"+json);
            ChannelFuture f = channel.write(response);
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    future.getChannel().close();
                }
            });
        }
    }
}
