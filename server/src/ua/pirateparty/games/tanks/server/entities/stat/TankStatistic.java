package ua.pirateparty.games.tanks.server.entities.stat;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 15.10.12
 * Time: 1:38
 */
public class TankStatistic {
    private long tankId;
    private long games;
    private long kills;
    private long deaths;

    public long getTankId() {
        return tankId;
    }

    public void setTankId(long tankId) {
        this.tankId = tankId;
    }

    public long getGames() {
        return games;
    }

    public void setGames(long games) {
        this.games = games;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }
}
