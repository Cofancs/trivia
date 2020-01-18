package com.adaptionsoft.games.uglytrivia;

public class GameBoard {

    int maxBoardSize;

    public GameBoard(int maxBoardSize) {
        this.maxBoardSize = maxBoardSize;
    }

    void movePlayer(Player player, int steps) {
        int position = player.getPosition();
        int newPosition = position + steps;
        if (newPosition < maxBoardSize - 1) {
            player.setPosition(newPosition);
        } else {
            player.setPosition(newPosition - maxBoardSize);
        }
    }

}
