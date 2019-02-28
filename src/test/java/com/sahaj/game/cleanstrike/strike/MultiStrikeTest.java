package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Coin;
import com.sahaj.game.cleanstrike.resources.Striker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MultiStrikeTest {

    private CarromBoard carromBoard;

    @Before
    public void setUp() {
        List<Coin> blackCoins = new ArrayList<>(Arrays.asList(
                new Coin("Black"),
                new Coin("Black")
        ));
        Coin redCoin = new Coin("Red");

        Striker striker = new Striker();
        carromBoard = new CarromBoard(blackCoins, redCoin, striker);
    }

    @Test
    public void shouldMultiStrikeAndPutTwoBlackCoinInPocket() {
        Strike strike = new MultiStrike(2);

        strike.pocketCoins(carromBoard);

        assertEquals(2, carromBoard.getPocket().size());
        assertEquals(0, carromBoard.getBlackCoinsOnBoard().size());
        assertNotNull(carromBoard.getRedCoin());
    }

    @Test
    public void shouldReturnOnePointForStrike() {
        Strike strike = new MultiStrike(2);

        int points = strike.getPoints();

        assertEquals(2, points);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenNumberOfBlackCoinsToPutInPocketAreGreaterThanBlackCoinsOnBoard() {
        Strike strike = new MultiStrike(3);
        strike.pocketCoins(carromBoard);
    }
}