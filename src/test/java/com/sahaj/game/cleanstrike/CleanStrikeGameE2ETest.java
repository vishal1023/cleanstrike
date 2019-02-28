package com.sahaj.game.cleanstrike;

import com.sahaj.game.cleanstrike.resources.*;
import com.sahaj.game.cleanstrike.monitor.GameMonitor;
import com.sahaj.game.cleanstrike.monitor.GamePointsMonitor;
import com.sahaj.game.cleanstrike.monitor.RuleManager;
import com.sahaj.game.cleanstrike.strike.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CleanStrikeGameE2ETest {

    private static final Strike SINGLE_STRIKE = new SingleStrike();
    private static final Strike MULTI_STRIKE = new MultiStrike(2);
    private static final Strike RED_STRIKE = new RedStrike(0);
    private static final Strike STRIKER_STRIKE = new StrikerStrike();
    private static final Strike NO_STRIKE = new NoStrike();
    private static final Strike DEFUNCT_COIN = new DefunctCoin();


    private CarromBoard carromBoard;
    private Player playerOne;
    private Player playerTwo;
    private GameMonitor gameMonitor;
    private List<Coin> blackCoins;

    @Before
    public void setUp() {
        playerOne = new Player(0, "John");
        playerTwo = new Player(0, "Mike");
        GamePointsMonitor gamePointsMonitor = new GamePointsMonitor(asList(playerOne, playerTwo));
        RuleManager ruleManager = new RuleManager();
        gameMonitor = new GameMonitor(gamePointsMonitor, ruleManager);
        carromBoard = getCarromBoard();
    }

    @Test
    public void playerOneShouldWin() {
        List<Strike> strikes = new ArrayList<>(asList(
                SINGLE_STRIKE,
                MULTI_STRIKE,
                MULTI_STRIKE,
                SINGLE_STRIKE,
                RED_STRIKE
        ));

        CleanStrikeGame game = new CleanStrikeGame(carromBoard, playerOne, playerTwo, gameMonitor, strikes);
        GameResult gameResult = game.play();

        assertEquals(playerOne, gameResult.getWinningPlayer());
    }

    @Test
    public void playerTwoShouldWin() {
        List<Strike> strikes = new ArrayList<>(asList(
                MULTI_STRIKE,
                SINGLE_STRIKE,
                NO_STRIKE,
                MULTI_STRIKE,
                NO_STRIKE,
                SINGLE_STRIKE,
                NO_STRIKE,
                SINGLE_STRIKE
            ));

        CleanStrikeGame game = new CleanStrikeGame(carromBoard, playerOne, playerTwo, gameMonitor, strikes);
        GameResult gameResult = game.play();

        assertEquals(playerTwo, gameResult.getWinningPlayer());
    }

    @Test
    public void gameShouldDraw() {
        List<Strike> strikes = new ArrayList<>(asList(
                SINGLE_STRIKE,
                MULTI_STRIKE,
                SINGLE_STRIKE,
                DEFUNCT_COIN,
                STRIKER_STRIKE
        ));

        CleanStrikeGame game = new CleanStrikeGame(carromBoard, playerOne, playerTwo, gameMonitor, strikes);
        GameResult gameResult = game.play();

        assertNull(gameResult.getWinningPlayer());
    }

    private CarromBoard getCarromBoard() {
        Coin redCoin = new Coin("Red");
        Striker striker = new Striker();
        initBlackCoins();
        return new CarromBoard(blackCoins, redCoin, striker);
    }

    private void initBlackCoins() {
        blackCoins = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            blackCoins.add(new Coin("Black"));
        }
    }
}