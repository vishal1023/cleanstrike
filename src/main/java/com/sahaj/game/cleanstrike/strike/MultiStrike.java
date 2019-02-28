package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Coin;

import java.util.List;

public class MultiStrike implements Strike {

    private int numberOfCoinsToPutInPocket;

    public MultiStrike(int numberOfCoinsToPutInPocket) {
        this.numberOfCoinsToPutInPocket = numberOfCoinsToPutInPocket;
    }

    @Override
    public String getType() {
        return "MultiStrike";
    }

    @Override
    public int getPoints() {
        return 2;
    }

    @Override
    public void pocketCoins(CarromBoard carromBoard) {
        List<Coin> pocket = carromBoard.getPocket();
        List<Coin> blackCoinsOnBoard = carromBoard.getBlackCoinsOnBoard();

        if (blackCoinsOnBoard.size() < numberOfCoinsToPutInPocket) {
            throw new RuntimeException("Invalid Input As number of black coins on board" + blackCoinsOnBoard.size() +
                    " less than coins to put in pocket " + numberOfCoinsToPutInPocket);
        }

        pocket.add(blackCoinsOnBoard.remove(0));
        pocket.add(blackCoinsOnBoard.remove(0));
    }

}
