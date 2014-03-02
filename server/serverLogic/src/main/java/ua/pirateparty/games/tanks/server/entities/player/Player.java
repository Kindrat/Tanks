package ua.pirateparty.games.tanks.server.entities.player;

import org.jboss.netty.channel.Channel;
import ua.pirateparty.games.tanks.common.PlayerState;

public class Player {

    protected Channel channel;
    protected PlayerState state;


    public Player (Channel channel){
        this.channel = channel;
        this.state = PlayerState.NOT_LOGGED_IN;
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

    public void onDisconnect() {
        channel.close();
    }

    public void sendData(Object obj) {
    }
}
