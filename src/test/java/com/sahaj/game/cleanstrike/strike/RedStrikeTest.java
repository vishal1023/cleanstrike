package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Striker;
import com.sahaj.game.cleanstrike.resources.Coin;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RedStrikeTest {

    private CarromBoard carromBoard;

    @Before
    public void setUp() {
        List<Coin> blackCoins = new ArrayList<>(Arrays.asList(
                new Coin("Black")
        ));
        Coin redCoin = new Coin("Red");

        Striker striker = new Striker();
        carromBoard = new CarromBoard(blackCoins, redCoin, striker);
    }

    @Test
    public void shouldStrikeAndPutASingleBlackCoinInPocket() {
        Strike strike = new RedStrike(0);

        strike.pocketCoins(carromBoard);

        assertEquals(1, carromBoard.getPocket().size());
        assertEquals(1, carromBoard.getBlackCoinsOnBoard().size());
        assertNull(carromBoard.getRedCoin());
    }

    @Test
    public void shouldReturnOnePointForStrike() {
        Strike strike = new RedStrike(0);

        int points = strike.getPoints();

        assertEquals(3, points);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenStrikeForRedCoinAndThereIsNoRedCoinOnBoard() {
        List<Coin> blackCoins = new ArrayList<>();
        Striker striker = new Striker();
        carromBoard = new CarromBoard(blackCoins, null, striker);
        Strike strike = new RedStrike(0);

        strike.pocketCoins(carromBoard);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenNumberOfBlackCoinsToPutInPocketAreGreateThanBlackCoinsOnBoard() {
        List<Coin> blackCoins = new ArrayList<>();
        Striker striker = new Striker();
        carromBoard = new CarromBoard(blackCoins, new Coin("RED"), striker);
        Strike strike = new RedStrike(1);

        strike.pocketCoins(carromBoard);
    }
}