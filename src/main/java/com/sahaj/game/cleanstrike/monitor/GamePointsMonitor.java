package com.sahaj.game.cleanstrike.monitor;

import com.sahaj.game.cleanstrike.resources.Player;
import com.sahaj.game.cleanstrike.strike.Strike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePointsMonitor {
    private static final Logger logger = LoggerFactory.getLogger(GamePointsMonitor.class);
    private static final int MAX_FOULS_LIMIT = 3;
    private static final int INITIAL_FOULS = 0;
    private static final int FOUL_POINTS = 1;
    private static final int MAX_MISS_STRIKE_LIMIT = 3;
    private static final int THREE_MISSED_FOUL_POINTS = 1;
    private static final int INITIAL_MISS = 0;
    private Map<Player, Integer> numberOfMiss;
    private Map<Player, Integer> fouls;

    public GamePointsMonitor(List<Player> players) {
        initNumberOfMissForEachPlayer(players);
        initFoulsForEachPlayer(players);
    }

    public void updatePointsFor(Player player, Strike strike) {
        player.addPoint(strike.getPoints());
        checkIfFoulAndUpdatePoints(player, strike);
        checkNumberOfFoulsAndUpdatePoints(player);
        logger.info(player.getName() + "'s current points [" + player.getPoints() + "]");
    }

    private void checkIfFoulAndUpdatePoints(Player player, Strike strike) {
        boolean isSuccessiveMissAndUpdatePoints = checkIsSuccessiveMissAndUpdatePoints(player, strike);
        if (strike.getPoints() < 0 || isSuccessiveMissAndUpdatePoints) {
            addFoulFor(player);
        }
    }

    private void checkNumberOfFoulsAndUpdatePoints(Player player) {
        if (fouls.get(player) == MAX_FOULS_LIMIT) {
            player.minusPoints(FOUL_POINTS);
            fouls.put(player, INITIAL_FOULS);
        }
    }

    private boolean checkIsSuccessiveMissAndUpdatePoints(Player player, Strike strike) {
        if (isMissedStrike(strike)) {
            numberOfMiss.put(player, numberOfMiss.get(player) + 1);
        }

        Integer totalNumberOfMissedStrike = numberOfMiss.get(player);
        if (totalNumberOfMissedStrike == MAX_MISS_STRIKE_LIMIT) {
            player.minusPoints(THREE_MISSED_FOUL_POINTS);
            numberOfMiss.put(player, INITIAL_MISS);
            return true;
        }
        return false;
    }

    private boolean isMissedStrike(Strike strike) {
        return strike.getPoints() == 0;
    }

    private void initFoulsForEachPlayer(List<Player> players) {
        this.fouls = new HashMap<>();
        players.forEach(player -> fouls.put(player, INITIAL_FOULS));
    }

    private void initNumberOfMissForEachPlayer(List<Player> players) {
        this.numberOfMiss = new HashMap<>();
        players.forEach(player -> numberOfMiss.put(player, INITIAL_MISS));
    }

    private void addFoulFor(Player player) {
        fouls.put(player, fouls.get(player) + 1);
    }
}
