package com.sahaj.game.cleanstrike.monitor;

import com.sahaj.game.cleanstrike.resources.GameResult;
import com.sahaj.game.cleanstrike.resources.Player;
import com.sahaj.game.cleanstrike.strike.Strike;

public class GameMonitor {
    private GamePointsMonitor gamePointsMonitor;
    private RuleManager ruleManager;

    public GameMonitor(GamePointsMonitor gamePointsMonitor, RuleManager ruleManager) {
        this.gamePointsMonitor = gamePointsMonitor;
        this.ruleManager = ruleManager;
    }

    public void updatePointsFor(Player player, Strike strike) {
        gamePointsMonitor.updatePointsFor(player, strike);
    }

    public GameResult getGameResult(Player playerOne, Player playerTwo) {
        return ruleManager.getGameResult(playerOne, playerTwo);
    }

    public GameResult getFinalResult(Player playerOne, Player playerTwo) {
        return ruleManager.getEndResult(playerOne, playerTwo);
    }
}
