package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;

public class NoStrike implements Strike {
    @Override
    public String getType() {
        return "NoStrike";
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public void pocketCoins(CarromBoard carromBoard) {
        //Do nothing
    }

}
