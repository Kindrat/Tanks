package server.moves;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.09.12
 * Time: 1:21
 */

public class MovesMap {
    public static final Map<Integer, Object> moves = new HashMap<>();

    static {
        moves.put(1, new Move());
        moves.put(2, new Search());
        moves.put(3, new Attack());
    }
}
