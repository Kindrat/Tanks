package ua.pirateparty.games.tanks.protocols.amf.responses.error;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 13.10.12
 * Time: 21:15
 */

public class ErrorMessages implements IErrors{

    public static Map<Integer, String> errors = new HashMap<>();

    static {
        errors.put(UNREGISTERED,"Unregistered player");
        errors.put(ALREADY_IN_GAME,"Already in game");
        errors.put(EVERYTHING_IS_OK, "Everything is OK");
        errors.put(UNKNOWN,"Unknown error");
    }
}
