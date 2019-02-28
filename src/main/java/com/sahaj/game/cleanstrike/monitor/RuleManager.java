package com.sahaj.game.cleanstrike.monitor;

import com.sahaj.game.cleanstrike.resources.GameResult;
import com.sahaj.game.cleanstrike.resources.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleManager {
    private static final Logger logger = LoggerFactory.getLogger(RuleManager.class);

    private static final int MIN_LEAD_POINTS = 3;
    private static final int MIN_POINTS_TO_WIN = 5;

    public GameResult getGameResult(Player playerOne, Player playerTwo) {
        Player winningPlayer = getWinningPlayer(playerOne, playerTwo);
        return new GameResult(winningPlayer);
    }

    public GameResult getEndResult(Player playerOne, Player playerTwo) {
        Player winningPlayer = null;
        if (isPlayerWon(playerOne, playerTwo)) {
            winningPlayer = playerOne;
        }
        if (isPlayerWon(playerTwo, playerOne)) {
            winningPlayer = playerTwo;
        }
        logger.info("Final Scores \t" + playerOne.getName() + "[" + playerOne.getPoints() + "] \t"
                + playerTwo.getName() + "[" + playerTwo.getPoints() + "]");
        return new GameResult(winningPlayer);
    }

    private Player getWinningPlayer(Player playerOne, Player playerTwo) {
        Player winningPlayer = null;
        if (isWinningConditionFor(playerOne, playerTwo)) {
            winningPlayer = playerOne;
        }
        if (isWinningConditionFor(playerTwo, playerOne)) {
            winningPlayer = playerTwo;
        }
        return winningPlayer;
    }

    private boolean isWinningConditionFor(Player playerOne, Player playerTwo) {
        int playerOnePoints = playerOne.getPoints();
        return (playerOnePoints >= MIN_POINTS_TO_WIN && playerOnePoints >= playerTwo.getPoints() + MIN_LEAD_POINTS);
    }

    private boolean isPlayerWon(Player playerOne, Player playerTwo) {
        boolean isPlayerWon = false;
        int playerOnePoints = playerOne.getPoints();
        int playerTwoPoints = playerTwo.getPoints();

        if (playerOnePoints > playerTwoPoints) {
            isPlayerWon = (playerOnePoints >= playerTwoPoints + MIN_LEAD_POINTS) ||
                    (playerOnePoints >= MIN_POINTS_TO_WIN);
        }
        return isPlayerWon;
    }
}
