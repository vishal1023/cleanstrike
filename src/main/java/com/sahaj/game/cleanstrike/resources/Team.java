package com.sahaj.game.cleanstrike.resources;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Player;
import com.sahaj.game.cleanstrike.strike.Strike;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private List<Player> players;

    public Team(String firstTeam, int numberOfPlayers) {
        players = new ArrayList<>();
    }

    public void add(Player playerOne) {
        players.add(playerOne);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void turn(CarromBoard carromBoard, Strike strike) {
        Player player = players.get(0);
        player.turn(carromBoard, strike);
    }
}
