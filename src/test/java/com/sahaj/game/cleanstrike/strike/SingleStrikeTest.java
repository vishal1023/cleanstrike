package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Coin;
import com.sahaj.game.cleanstrike.resources.Striker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingleStrikeTest {

    private CarromBoard carromBoard;
    private Striker striker = new Striker();

    @Before
    public void setUp() {
        List<Coin> blackCoins = new ArrayList<>(asList(
                new Coin("Black"), new Coin("Black")
        ));
        Coin redCoin = new Coin("Red");

        carromBoard = new CarromBoard(blackCoins, redCoin, striker);
    }

    @Test
    public void shouldStrikeAndPutASingleBlackCoinInPocket() {
        Strike strike = new SingleStrike();

        strike.pocketCoins(carromBoard);

        assertEquals(1, carromBoard.getPocket().size());
        assertEquals(1, carromBoard.getBlackCoinsOnBoard().size());
        assertNotNull(carromBoard.getRedCoin());
    }

    @Test
    public void shouldReturnOnePointForStrike() {
        Strike strike = new SingleStrike();

        int points = strike.getPoints();

        assertEquals(1, points);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowAnExceptionWhenStrikeToPocketCoinAndThereIsNoBlackCoinOnBord() {
        Strike strike = new SingleStrike();
        carromBoard = new CarromBoard(new ArrayList<>(), null, striker);
        strike.pocketCoins(carromBoard);
    }
}