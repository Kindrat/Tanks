package moves;

import entities.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.09.12
 * Time: 0:45
 */

public abstract class AMove {
    private final String PARAM_DELIMITER = ";";

    public abstract void execute (String[] args, Player player);

    public String[] splitParams(String args){
        return args.split(PARAM_DELIMITER);
    }
}
