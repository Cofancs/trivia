
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;

import java.util.Random;


public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        play(new Random());
    }

    public static void play(Random rand) {
        Game aGame = new Game(new ConsoleGameStatusHandlerImpl());

        aGame.add(new Player("Chet"));
        aGame.add(new Player("Pat"));
        aGame.add(new Player("Sue"));

        do {
            aGame.setFirstPlayer();
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}
