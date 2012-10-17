package ua.pirateparty.games.tanks.server.moves;

import ua.pirateparty.games.tanks.server.entities.player.Player;

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
