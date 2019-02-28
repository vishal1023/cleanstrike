package com.sahaj.game.cleanstrike.strike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Coin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefunctCoin implements Strike {
    private static final Logger logger = LoggerFactory.getLogger(DefunctCoin.class);

    @Override
    public String getType() {
        return "DefunctCoin";
    }

    @Override
    public int getPoints() {
        return -2;
    }

    @Override
    public void pocketCoins(CarromBoard carromBoard) {
        List<Coin> blackCoinsOnBoard = carromBoard.getBlackCoinsOnBoard();
        Coin redCoin = carromBoard.getRedCoin();
        if (blackCoinsOnBoard.size() <= 0 && redCoin == null) {
            throw new RuntimeException("There is no black coin on the board");
        }
        if(blackCoinsOnBoard.size() > 0) {
            logger.info("A BLACK coin is out the game");
            blackCoinsOnBoard.remove(0);
        }else {
            logger.info("A RED coin is out the game");
            carromBoard.setRedCoin(null);
        }
    }
}
