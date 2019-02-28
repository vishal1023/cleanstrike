package com.sahaj.game.cleanstrike.resources;

import com.sahaj.game.cleanstrike.strike.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PlayerTest {

    private Player player;
    private CarromBoard carromBoard;
    private Striker striker;

    @Before
    public void setUp() {
        int points = 0;
        player = new Player(points, "playerOne");
        carromBoard = mock(CarromBoard.class);
        striker = mock(Striker.class);
        when(carromBoard.getStriker()).thenReturn(striker);
    }

    @Test
    public void shouldStrikeAndReturnOnePoint() {
        SingleStrike strike = new SingleStrike();
        player.turn(carromBoard, strike);
        verify(striker).strikeOn(carromBoard, strike);
    }

    @Test
    public void shouldMultiStrikeAndReturnTwoPoints() {
        MultiStrike multiStrike = new MultiStrike(2);
        player.turn(carromBoard, multiStrike);
        verify(striker).strikeOn(carromBoard, multiStrike);
    }

    @Test
    public void shouldStrikeARedCoinAndReturnThreePoints() {
        RedStrike redStrike = new RedStrike(0);
        player.turn(carromBoard, redStrike);
        verify(striker).strikeOn(carromBoard, redStrike);
    }

    @Test
    public void shouldStrikeAStrikerAndReturnMinusOnePoint() {
        StrikerStrike strikerStrike = new StrikerStrike();
        player.turn(carromBoard, strikerStrike);
        verify(striker).strikeOn(carromBoard, strikerStrike);
    }

    @Test
    public void shouldStrikeToDefunctCoinAndReturnMinusTwoPoints() {
        DefunctCoin defunctCoin = new DefunctCoin();
        player.turn(carromBoard, defunctCoin);
        verify(striker).strikeOn(carromBoard, defunctCoin);
    }

}