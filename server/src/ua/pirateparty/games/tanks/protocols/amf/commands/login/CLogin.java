package ua.pirateparty.games.tanks.protocols.amf.commands.login;

import ua.pirateparty.games.tanks.dao.implementations.PlayerDao;
import ua.pirateparty.games.tanks.dao.interfaces.IPlayerDao;
import ua.pirateparty.games.tanks.protocols.amf.commands.Command;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import static ua.pirateparty.games.tanks.server.logic.OnlineUsersManager.authorized;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:03
 */

public class CLogin extends Command{

    private String login;
    private String passHash;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    @Override
    public void execute(Player player) {
        if (player.hasStatus(Player.NOT_LOGGED_IN)){
            player.setName(login);
            player.setPassHash(passHash);
            IPlayerDao playerDao = new PlayerDao();
            player = playerDao.read(player);
            if (player.getId()!=0){
                authorized.put(player.getId(), player);
                //TODO Старт игры
            }
        }
    }

    @Override
    public String toString() {
        return "CLogin{"+
                "login="+this.login+
                ", passHash="+this.passHash+
                "}";
    }
}
