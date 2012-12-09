package ua.pirateparty.games.tanks.commands.tank;

import ua.pirateparty.games.tanks.commands.Command;
import ua.pirateparty.games.tanks.server.entities.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 17.10.12
 * Time: 22:28
 */
public class CChooseTank extends Command {

    private int tankId;

    public int getTankId() {
        return tankId;
    }

    public void setTankId(int tankId) {
        this.tankId = tankId;
    }

    @Override
    public void execute(Player player) {
/*        ITankDao tankDao = new PgTankDao();
        tankDao.update(player);
        player.setTank(tankDao.read(this.tankId));
        player.getTank().setTankStatistic(tankDao.read(player));*/
    }

    @Override
    public String toString() {
        return "CChooseTank{"+
                "tankId = "+this.tankId+
                "}";
    }
}
