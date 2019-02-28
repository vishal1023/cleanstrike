package com.sahaj.game.cleanstrike.monitor;

import com.sahaj.game.cleanstrike.resources.Player;
import com.sahaj.game.cleanstrike.strike.*;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GamePointsMonitorTest {

    private Player player;
    private GamePointsMonitor gamePointsMonitor;

    @Before
    public void setUp() {
        player = new Player(10, "playerOne");
        gamePointsMonitor = new GamePointsMonitor(asList(player));
    }

    @Test
    public void shouldLoosePointWhenPlayerFoulsThreeTime() {
        Strike defunctCoin = new DefunctCoin();
        Strike strikerStrike = new StrikerStrike();

        gamePointsMonitor.updatePointsFor(player, strikerStrike);
        gamePointsMonitor.updatePointsFor(player, defunctCoin);
        gamePointsMonitor.updatePointsFor(player, strikerStrike);

        assertEquals(5, player.getPoints());
    }

    @Test
    public void shouldLooseAPointWhenPocketsNoCoinInThreeSuccessiveTurns() {
        Strike strike = new NoStrike();

        for (int i = 0; i < 4; i++)
            gamePointsMonitor.updatePointsFor(player, strike);

        assertEquals(9, player.getPoints());
    }

    @Test
    public void shouldAddPointForPlayerWhenSingleStrike() {
        Strike strike = new SingleStrike();

        gamePointsMonitor.updatePointsFor(player, strike);

        assertEquals(11, player.getPoints());
    }

    @Test
    public void shouldAddTwoPointForPlayerWhenMultiStrike() {
        Strike strike = new MultiStrike(2);

        gamePointsMonitor.updatePointsFor(player, strike);

        assertEquals(12, player.getPoints());
    }

    @Test
    public void shouldAddThreePointForPlayerWhenRedStrike() {
        Strike strike = new RedStrike(0);

        gamePointsMonitor.updatePointsFor(player, strike);

        assertEquals(13, player.getPoints());
    }

    @Test
    public void shouldMinusAPointForPlayerWhenStrikerStrike() {
        Strike strike = new StrikerStrike();

        gamePointsMonitor.updatePointsFor(player, strike);

        assertEquals(9, player.getPoints());
    }

    @Test
    public void shouldMinusTwoPointsForPlayerWhenDefunctCoin() {
        Strike strike = new DefunctCoin();

        gamePointsMonitor.updatePointsFor(player, strike);

        assertEquals(8, player.getPoints());
    }

    @Test
    public void shouldNotPointsForPlayerWhenNoStrike() {
        Strike strike = new NoStrike();

        gamePointsMonitor.updatePointsFor(player, strike);

        assertEquals(10, player.getPoints());
    }
}