package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;

public interface Strike {

    String getType();

    int getPoints();

    void pocketCoins(CarromBoard carromBoard);

}
