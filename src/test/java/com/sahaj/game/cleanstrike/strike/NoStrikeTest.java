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

public class NoStrikeTest {
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
    public void shouldStrikeAndPutASingleBlackCoinInPocket() {
        Strike strike = new NoStrike();

        strike.pocketCoins(carromBoard);

        assertEquals(0, carromBoard.getPocket().size());
        assertEquals(2, carromBoard.getBlackCoinsOnBoard().size());
        assertNotNull(carromBoard.getRedCoin());
    }

    @Test
    public void shouldReturnOnePointForStrike() {
        Strike strike = new SingleStrike();

        int points = strike.getPoints();

        assertEquals(1, points);
    }

}