package com.sahaj.game.cleanstrike.resources;

import java.util.ArrayList;
import java.util.List;

public class CarromBoard {

    private Striker striker;
    private List<Coin> pocket;
    private List<Coin> blackCoins;
    private Coin redCoin;

    public CarromBoard(List<Coin> blackCoins, Coin redCoin, Striker striker) {
        this.blackCoins = blackCoins;
        this.redCoin = redCoin;
        this.striker = striker;
        this.pocket = new ArrayList<>();
    }

    public Striker getStriker() {
        return striker;
    }

    public List<Coin> getBlackCoinsOnBoard() {
        return blackCoins;
    }

    public List<Coin> getPocket() {
        return pocket;
    }

    public Coin getRedCoin() {
        return redCoin;
    }

    public void setRedCoin(Coin redCoin) {
        this.redCoin = redCoin;
    }
}
