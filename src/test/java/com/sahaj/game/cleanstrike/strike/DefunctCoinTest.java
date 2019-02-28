package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Coin;
import com.sahaj.game.cleanstrike.resources.Striker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class DefunctCoinTest {

    private CarromBoard carromBoard;

    @Before
    public void setUp() {
        List<Coin> blackCoins = new ArrayList<>(asList(
                new Coin("Black"),
                new Coin("Black")
        ));
        Coin redCoin = new Coin("Red");

        Striker striker = new Striker();
        carromBoard = new CarromBoard(blackCoins, redCoin, striker);
    }

    @Test
    public void shouldNotPutAnyCoinInPocket() {
        Strike strike = new DefunctCoin();

        strike.pocketCoins(carromBoard);

        assertEquals(0, carromBoard.getPocket().size());
        assertEquals(1, carromBoard.getBlackCoinsOnBoard().size());
        assertNotNull(carromBoard.getRedCoin());
    }

    @Test
    public void shouldReturnOnePointForStrike() {
        Strike strike = new DefunctCoin();

        int points = strike.getPoints();

        assertEquals(-2, points);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenStrikeIsDefunctCoinAndThereIsNoCoinONBoard() {
        Strike strike = new DefunctCoin();
        carromBoard = new CarromBoard(asList(), null, new Striker());

        strike.pocketCoins(carromBoard);
    }

    @Test
    public void shouldNotThrowExceptionWhenThereIsAtLeastOneRedOrBlackCoinOnBoard() {
        Strike strike = new DefunctCoin();
        carromBoard = new CarromBoard(new ArrayList<>(), new Coin("RED"), new Striker());

        strike.pocketCoins(carromBoard);
        assertNull(carromBoard.getRedCoin());
    }


}