/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pirateparty.games.tanks.protocols.amf.responses.login;

import ua.pirateparty.games.tanks.client.TanksClient;
import ua.pirateparty.games.tanks.protocols.amf.responses.Response;

/**
 *
 * @author legioner
 */
public class SPing extends Response{
    
    private String version;
    private String status;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void process() {
    }

    @Override
    public String toString() {
        return "SPing{" +
                "version='" + this.version + '\'' +
                ", status='" + this.status + '\'' +
                "}";
    }
    
}
