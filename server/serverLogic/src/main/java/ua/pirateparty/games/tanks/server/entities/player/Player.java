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
import ua.pirateparty.games.tanks.common.PlayerState;

public class Player {

    protected Channel channel;
    protected PlayerState state;
    protected Gson gson;


    public Player (Channel channel){
        this.channel = channel;
        this.state = PlayerState.NOT_LOGGED_IN;
        this.gson = new Gson();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public boolean hasState(PlayerState state){
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
