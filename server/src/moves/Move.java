package moves;

import entities.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.09.12
 * Time: 0:50
 */

public class Move extends AMove {

    @Override
    public void execute(String[] args, Player player) {
        if (testArgs(args)){
            player.getTank().move(Integer.valueOf(args[0]));
        } else{
            player.hang();
        }

    }

    private boolean testArgs(String[] args) {
        return false;
    }
}
