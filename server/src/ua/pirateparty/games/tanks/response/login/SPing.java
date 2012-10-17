package ua.pirateparty.games.tanks.response.login;

import ua.pirateparty.games.tanks.response.Response;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 17:41
 */

public class SPing extends Response {

    private String version;
    private String status;

    public SPing(String version, String status){
        this.version=version;
        this.status = status;
    }

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

    public String toString (){
        String version = "0.0.1";
        return "SPing{" +
                "version='" + version + '\'' +
                ", status='" + this.status + '\'' +
                "}";
    }
}
