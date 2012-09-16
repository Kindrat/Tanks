package objects.login;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 07.09.12
 * Time: 17:41
 */

public class OPing {

    private String status;

    public OPing (String status){
        this.status = status;
    }

    public String toString (){
        String version = "0.0.1";
        return "OPing{" +
                "version='" + version + '\'' +
                ", status='" + this.status + '\'' +
                "}";
    }
}
