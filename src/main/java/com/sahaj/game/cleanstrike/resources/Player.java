package com.sahaj.game.cleanstrike.resources;

import com.sahaj.game.cleanstrike.strike.Strike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);
    private int points;

    public String getName() {
        return name;
    }

    private String name;

    public Player(int points, String name) {
        this.points = points;
        this.name = name;
    }

    public void turn(CarromBoard carromBoard, Strike strike) {
        logger.info(this.name + "'s turn for : [" + strike.getType() + "]");
        Striker striker = carromBoard.getStriker();
        striker.strikeOn(carromBoard, strike);
    }

    public int getPoints() {
        return points;
    }

    public void minusPoints(int points) {
        this.points -= points;
    }

    public void addPoint(int points) {
        this.points += points;
    }
}
