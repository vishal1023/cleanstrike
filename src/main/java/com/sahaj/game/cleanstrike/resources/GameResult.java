package com.sahaj.game.cleanstrike.resources;

public class GameResult {

    private Player winningPlayer;

    public GameResult(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    @Override
    public String toString() {
        if (winningPlayer != null) {
            return winningPlayer.getName() + " Won the game with points " + winningPlayer.getPoints();
        } else {
            return "Game is draw";
        }
    }
}
