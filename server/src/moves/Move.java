package moves;

import entities.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.09.12
 * Time: 0:50
 */

public class Move {

    public void execute(Integer direction, Player player) {
        player.getTank().move(direction);
    }
}
