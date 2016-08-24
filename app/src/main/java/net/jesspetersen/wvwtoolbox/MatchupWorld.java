package net.jesspetersen.wvwtoolbox;

import java.util.List;

/**
 * Created by jessi on 8/07/2016.
 */
public class MatchupWorld {

    String mainWorldID;
    List<String> worldIDs;
    String points;
    String kills;
    String deaths;

    public MatchupWorld(String mainWorldID, List<String> worldIDs, String points, String kills, String deaths)
    {
        this.mainWorldID = mainWorldID;
        this.worldIDs = worldIDs;
        this.points = points;
        this.kills = kills;
        this.deaths = deaths;
    }

    public String getMainWorldID() {
        return mainWorldID;
    }

    public List<String> getWorldIDs() {
        return worldIDs;
    }

    public String getPoints() {
        return points;
    }

    public String getKills() {
        return kills;
    }

    public String getDeaths() {
        return deaths;
    }
}
