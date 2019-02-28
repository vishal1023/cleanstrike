package com.sahaj.game.cleanstrike.monitor;

import com.sahaj.game.cleanstrike.resources.GameResult;
import com.sahaj.game.cleanstrike.resources.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RuleManagerTest {

    @Test
    public void shouldReturnPlayerOneAsWinningPlayerInGameResult() {
        Player playerOne = new Player(10, "playerOne");
        Player playerTwo = new Player(7, "playerTwo");
        RuleManager ruleManager = new RuleManager();
        GameResult expectedGameResult = new GameResult(playerOne);

        GameResult actualGameResult = ruleManager.getGameResult(playerOne, playerTwo);

        assertEquals(expectedGameResult.getWinningPlayer(), actualGameResult.getWinningPlayer());
    }

    @Test
    public void shouldReturnPlayerTwoAsWinningPlayerInGameResult() {
        Player playerOne = new Player(7, "playerOne");
        Player playerTwo = new Player(10, "playerTwo");
        RuleManager ruleManager = new RuleManager();
        GameResult expectedGameResult = new GameResult(playerTwo);

        GameResult actualGameResult = ruleManager.getGameResult(playerOne, playerTwo);

        assertEquals(expectedGameResult.getWinningPlayer(), actualGameResult.getWinningPlayer());
    }

    @Test
    public void shouldReturnNullWhenThereIsNoWinningPlayer() {
        Player playerOne = new Player(8, "playerOne");
        Player playerTwo = new Player(10, "playerTwo");
        RuleManager ruleManager = new RuleManager();

        GameResult actualGameResult = ruleManager.getGameResult(playerOne, playerTwo);

        assertNull(actualGameResult.getWinningPlayer());
    }
}