package net.jesspetersen.wvwtoolbox;

/**
 * Created by jessi on 8/07/2016.
 */
public class Matchup {

    MatchupWorld red;
    MatchupWorld blue;
    MatchupWorld green;

    public Matchup(MatchupWorld red, MatchupWorld blue, MatchupWorld green)
    {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public MatchupWorld getRed() {
        return red;
    }

    public MatchupWorld getBlue() {
        return blue;
    }

    public MatchupWorld getGreen() {
        return green;
    }
}
