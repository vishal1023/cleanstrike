package com.sahaj.game.cleanstrike.resources;

import com.sahaj.game.cleanstrike.strike.MultiStrike;
import com.sahaj.game.cleanstrike.strike.RedStrike;
import com.sahaj.game.cleanstrike.strike.SingleStrike;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StrikerTest {

    private Striker striker;
    private CarromBoard carromBoard;

    @Before
    public void setUp() {
        List<Coin> blackCoins = new ArrayList<>(Arrays.asList(
                new Coin("Black"),
                new Coin("Black"),
                new Coin("Black")
        ));
        Coin redCoin = new Coin("Red");

        striker = new Striker();
        carromBoard = new CarromBoard(blackCoins, redCoin, striker);
    }

    @Test
    public void shouldStrikeOnBoardAndPutOneBlackCoinInPocket() {
        striker.strikeOn(carromBoard, new SingleStrike());

        assertEquals(1, carromBoard.getPocket().size());
        assertEquals(2, carromBoard.getBlackCoinsOnBoard().size());
    }

    @Test
    public void shouldMultiStrikeOnBoardAndPutTwoBlackCoinsInPocket() {
        striker.strikeOn(carromBoard, new MultiStrike(2));

        assertEquals(2, carromBoard.getPocket().size());
        assertEquals(1, carromBoard.getBlackCoinsOnBoard().size());
    }

    @Test
    public void shouldRedStrikeOnBoardAndPutRedCoinInPocket() {
        striker.strikeOn(carromBoard, new RedStrike(0));

        assertNull(carromBoard.getRedCoin());
        assertEquals(1, carromBoard.getPocket().size());
    }
}