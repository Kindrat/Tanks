package response.error;

import response.Response;
import static response.error.ErrorMessages.errors;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:06
 */

public class SError extends Response {
    private int type;
    private String message;

    public  SError(int type){
        this.type=type;
        this.message=errors.get(type);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SError{"+
                "type="+this.type+
                ", message"+this.message+
                "}";
    }
}
