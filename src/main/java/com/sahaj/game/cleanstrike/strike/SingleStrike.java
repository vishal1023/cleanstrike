package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Coin;

import java.util.List;

public class SingleStrike implements Strike {

    @Override
    public String getType() {
        return "SingleStrike";
    }

    @Override
    public int getPoints() {
        return 1;
    }

    @Override
    public void pocketCoins(CarromBoard carromBoard) {
        List<Coin> pocket = carromBoard.getPocket();
        List<Coin> blackCoinsOnBoard = carromBoard.getBlackCoinsOnBoard();
        if (blackCoinsOnBoard.size() <= 0) {
            throw new RuntimeException("There is no black coin on the board");
        }
        pocket.add(blackCoinsOnBoard.remove(0));
    }

}
