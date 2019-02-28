package com.sahaj.game.cleanstrike.monitor;

import com.sahaj.game.cleanstrike.resources.Player;
import com.sahaj.game.cleanstrike.strike.StrikerStrike;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameMonitorTest {

    private GameMonitor monitor;
    private GamePointsMonitor gamePointsMonitor;
    private RuleManager ruleManager;

    @Before
    public void setUp() {
        gamePointsMonitor = mock(GamePointsMonitor.class);
        ruleManager = mock(RuleManager.class);
        monitor = new GameMonitor(gamePointsMonitor, ruleManager);
    }

    @Test
    public void shouldCallGamePointsManagerToUpdatePlayersPoints() {
        Player player = new Player(5, "playerOne");
        StrikerStrike strike = new StrikerStrike();
        monitor.updatePointsFor(player, strike);

        verify(gamePointsMonitor).updatePointsFor(player, strike);
    }

    @Test
    public void shouldCallGameResultMonitorToGetGameResult() {
        Player playerOne = new Player(5, "playerOne");
        Player playerTwo = new Player(5, "playerTwo");

        monitor.getGameResult(playerOne, playerTwo);

        verify(ruleManager).getGameResult(playerOne, playerTwo);
    }

    @Test
    public void shouldCallGetEndResultToGetEndResultOfGame() {
        Player playerOne = new Player(5, "playerOne");
        Player playerTwo = new Player(5, "playerTwo");

        monitor.getFinalResult(playerOne, playerTwo);

        verify(ruleManager).getEndResult(playerOne, playerTwo);
    }
}