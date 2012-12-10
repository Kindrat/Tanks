package ua.pirateparty.games.tanks.protocols.amf.commands.login;

import ua.pirateparty.games.tanks.dao.implementations.PlayerDao;
import ua.pirateparty.games.tanks.protocols.amf.commands.Command;
import ua.pirateparty.games.tanks.server.entities.player.Player;

import static ua.pirateparty.games.tanks.util.mail.EmailValidator.isValid;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 10.12.12
 * Time: 1:42
 */
public class CRegister extends Command {
    private String name;
    private char[] passHash;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char[] getPassHash() {
        return passHash;
    }

    public void setPassHash(char[] passHash) {
        this.passHash = passHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void execute(Player player) {
        if (player.hasStatus(Player.NOT_LOGGED_IN)){
            if (new PlayerDao().read(name, passHash) && isValid(email)){

            }
        }
    }

    @Override
    public String toString() {
        return "CRegister{"+
                "name='"+this.name+"\'"+
                ", pass='"+this.passHash.toString()+"\'"+
                ", email='"+this.email+"\'"+
                "}";
    }
}

class ProcessCreateUser extends Thread{
    public void run(){

    }
}
