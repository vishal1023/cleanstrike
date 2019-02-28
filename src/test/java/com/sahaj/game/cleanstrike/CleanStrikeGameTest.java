package com.sahaj.game.cleanstrike;

import com.sahaj.game.cleanstrike.resources.*;
import com.sahaj.game.cleanstrike.monitor.GameMonitor;
import com.sahaj.game.cleanstrike.strike.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

public class CleanStrikeGameTest {

    private Player playerOne;
    private Player playerTwo;
    private CarromBoard carromBoard;
    private CleanStrikeGame game;
    private Strike singleStrike;
    private GameMonitor monitor;

    @Before
    public void setUp() {

        singleStrike = new SingleStrike();
        List<Strike> gameInputs = singletonList(singleStrike);
        Striker striker = mock(Striker.class);
        monitor = mock(GameMonitor.class);
        playerOne = mock(Player.class);
        playerTwo = mock(Player.class);
        carromBoard = mock(CarromBoard.class);
        game = new CleanStrikeGame(carromBoard, playerOne, playerTwo, monitor, gameInputs);
        when(carromBoard.getStriker()).thenReturn(striker);
    }

    @Test
    public void shouldAttemptToStrikeACoinUsingStriker() {
        when(monitor.getGameResult(playerOne, playerTwo)).thenReturn(new GameResult(playerOne));

        game.play();

        verify(playerOne).turn(carromBoard, singleStrike);
    }

    @Test
    public void teamPlayerShouldGetAlternateTurn() {
        int numberOfPlayers = 2;
        Team teamOne = new Team("FirstTeam", numberOfPlayers);


    }

    @Test
    public void playerShouldGetAnAlternateAttemptToStrikeACoin() {
        when(monitor.getGameResult(playerOne, playerTwo)).thenReturn(new GameResult(playerOne));
        for (int i = 1; i < 3; i++) {
            game.play();
            verify(playerOne, times(i)).turn(carromBoard, singleStrike);
            game.play();
            verify(playerTwo, times(i)).turn(carromBoard, singleStrike);
        }


    }
}