package com.sahaj.game.cleanstrike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Player;
import com.sahaj.game.cleanstrike.resources.Team;
import com.sahaj.game.cleanstrike.strike.Strike;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TeamTest {

    @Test
    public void shouldAddTwoPlayers() {
        Team team = new Team("MyTeam", 2);

        Player playerOne = new Player(0, "PlayerOne");
        Player playerTwo = new Player(0, "PlayerTwo");

        team.add(playerOne);
        team.add(playerTwo);

        List<Player> players = team.getPlayers();
        assertEquals(2, players.size());
    }

    @Test
    public void shouldCallPlayersTurn() {
        Team team = new Team("MyTeam", 2);
        Player playerOne = mock(Player.class);
        Player playerTwo = mock(Player.class);
        CarromBoard carromBoard = mock(CarromBoard.class);
        Strike strike = mock(Strike.class);
        team.add(playerOne);
        team.add(playerTwo);

        team.turn(carromBoard, strike);

        verify(playerOne).turn(any(), any());
    }
}