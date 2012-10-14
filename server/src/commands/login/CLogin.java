package commands.login;

import commands.Command;
import dao.implementations.PgPlayerDao;
import dao.interfaces.IPlayerDao;
import entities.player.Player;
import response.error.IErrors;
import response.error.SError;

import static global.Static.authorized;

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
            IPlayerDao playerDao = new PgPlayerDao();
            player = playerDao.read(player);
            if (player.getId()!=0){
                authorized.put(player.getId(), player);
            }else{
                player.sendData(new SError(IErrors.UNREGISTERED));
            }
        }else{
            player.sendData(new SError(IErrors.ALREADY_IN_GAME));
        }
    }
}
