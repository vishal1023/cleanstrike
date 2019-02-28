package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Coin;

import java.util.List;

public class RedStrike implements Strike {

    private int numberOfBlackCoinsToPutInPocket;

    public RedStrike(int numberOfBlackCoinsToPutInPocket) {
        this.numberOfBlackCoinsToPutInPocket = numberOfBlackCoinsToPutInPocket;
    }

    @Override
    public String getType() {
        return "RedStrike";
    }

    @Override
    public int getPoints() {
        return 3;
    }

    @Override
    public void pocketCoins(CarromBoard carromBoard) {
        List<Coin> pocket = carromBoard.getPocket();
        Coin redCoin = carromBoard.getRedCoin();
        List<Coin> blackCoinsOnBoard = carromBoard.getBlackCoinsOnBoard();
        if (redCoin == null || blackCoinsOnBoard.size() < numberOfBlackCoinsToPutInPocket) {
            throw new RuntimeException("Invalid input - Either No RED coin on board OR " +
                    "As number of black coins on board" + blackCoinsOnBoard.size()
                    + "less than coins to put in pocket" + numberOfBlackCoinsToPutInPocket);
        }
        pocket.add(redCoin);
        carromBoard.setRedCoin(null);
    }

}
