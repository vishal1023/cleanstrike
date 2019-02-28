package com.sahaj.game.cleanstrike.resources;

import com.sahaj.game.cleanstrike.strike.Strike;

public class Striker {

    public void strikeOn(CarromBoard carromBoard, Strike strike) {
        strike.pocketCoins(carromBoard);
    }
}
