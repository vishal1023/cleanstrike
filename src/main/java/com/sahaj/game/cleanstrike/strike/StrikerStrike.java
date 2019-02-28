package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;

public class StrikerStrike implements Strike {
    @Override
    public String getType() {
        return "StrikerStrike";
    }

    @Override
    public int getPoints() {
        return -1;
    }

    @Override
    public void pocketCoins(CarromBoard carromBoard) {

    }

}
