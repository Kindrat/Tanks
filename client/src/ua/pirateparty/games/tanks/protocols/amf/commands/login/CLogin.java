/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pirateparty.games.tanks.protocols.amf.commands.login;

import ua.pirateparty.games.tanks.protocols.amf.commands.Command;

/**
 *
 * @author legioner
 */
public class CLogin extends Command{
    
    private String login;
    private String passHash;
    
    public CLogin(String login, String passHash){
        this.login=login;
        this.passHash=passHash;
    }
    
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
    public String toString() {
        return "CLogin{"+
                "login="+this.login+
                ", passHash="+this.passHash+
                "}";
    }    
}
