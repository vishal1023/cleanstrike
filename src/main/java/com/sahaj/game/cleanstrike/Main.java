package com.sahaj.game.cleanstrike;

import com.sahaj.game.cleanstrike.resources.CarromBoard;
import com.sahaj.game.cleanstrike.resources.Coin;
import com.sahaj.game.cleanstrike.resources.Player;
import com.sahaj.game.cleanstrike.resources.Striker;
import com.sahaj.game.cleanstrike.monitor.GameMonitor;
import com.sahaj.game.cleanstrike.monitor.GamePointsMonitor;
import com.sahaj.game.cleanstrike.monitor.RuleManager;
import com.sahaj.game.cleanstrike.strike.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class Main {

    private static final Strike SINGLE_STRIKE = new SingleStrike();
    private static final Strike MULTI_STRIKE = new MultiStrike(2);
    private static final Strike RED_STRIKE = new RedStrike(0);
    private static final Strike STRIKER_STRIKE = new StrikerStrike();
    private static final Strike NO_STRIKE = new NoStrike();
    private static final Strike DEFUNCT_COIN = new DefunctCoin();

    public static void main(String[] args) {
        Player playerOne = new Player(0, "Vishal");
        Player playerTwo = new Player(0, "Wajeed");
        GamePointsMonitor gamePointsMonitor = new GamePointsMonitor(asList(playerOne, playerTwo));
        RuleManager ruleManager = new RuleManager();
        GameMonitor gameMonitor = new GameMonitor(gamePointsMonitor, ruleManager);
        CarromBoard carromBoard = getCarromBoard();
        List<Strike> strikes = getInputStrikes();
        CleanStrikeGame game = new CleanStrikeGame(carromBoard, playerOne, playerTwo, gameMonitor, strikes);
        game.play();
    }

    private static List<Strike> getInputStrikes() {
        return Arrays.asList(
                NO_STRIKE,
                DEFUNCT_COIN,
                NO_STRIKE,
                SINGLE_STRIKE,
                NO_STRIKE,
                RED_STRIKE,
                MULTI_STRIKE,
                NO_STRIKE,
                STRIKER_STRIKE,
                STRIKER_STRIKE
        );
    }


    private static CarromBoard getCarromBoard() {
        Coin redCoin = new Coin("Red");
        Striker striker = new Striker();
        List<Coin> blackCoins = new LinkedList<>();
        initBlackCoins(blackCoins);
        return new CarromBoard(blackCoins, redCoin, striker);
    }

    private static void initBlackCoins(List<Coin> blackCoins) {
        for (int i = 0; i < 9; i++) {
            blackCoins.add(new Coin("Black"));
        }
    }
}
