package com.sahaj.game.cleanstrike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.GameResult;
import com.sahaj.game.cleanstrike.resources.Player;
import com.sahaj.game.cleanstrike.monitor.GameMonitor;
import com.sahaj.game.cleanstrike.strike.Strike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CleanStrikeGame {

    private static final Logger logger = LoggerFactory.getLogger(CleanStrikeGame.class);
    private boolean isPlayerOnesTurn = true;
    private Player playerOne;
    private Player playerTwo;
    private CarromBoard carromBoard;
    private GameMonitor gameMonitor;
    private List<Strike> strikes;

    public CleanStrikeGame(CarromBoard carromBoard, Player playerOne, Player playerTwo, GameMonitor gameMonitor,
                           List<Strike> strikes) {
        this.carromBoard = carromBoard;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gameMonitor = gameMonitor;
        this.strikes = strikes;
    }

    public GameResult play() {
        GameResult result;
        for (Strike strike : strikes) {
            Player player = getNextPlayer();
            nextTurn(player, strike);
            result = gameMonitor.getGameResult(playerOne, playerTwo);
            if (isWinningCondition(result) || isEndGameCondition()) {
                logger.info(" *********** Game End ********* \n Final Result : " + result.toString());
                return result;
            }
        }
        GameResult finalResult = gameMonitor.getFinalResult(playerOne, playerTwo);
        logger.info(" *********** Game End ********* \n Final Result : " + finalResult.toString());
        return finalResult;
    }

    private boolean isEndGameCondition() {
        return carromBoard.getBlackCoinsOnBoard().size() <= 0;
    }

    private boolean isWinningCondition(GameResult result) {
        return result.getWinningPlayer() != null;
    }

    private void nextTurn(Player player, Strike strike) {
        player.turn(carromBoard, strike);
        gameMonitor.updatePointsFor(player, strike);
    }

    private Player getNextPlayer() {
        Player player = this.isPlayerOnesTurn ? playerOne : playerTwo;
        this.isPlayerOnesTurn = !isPlayerOnesTurn;
        return player;
    }
}
