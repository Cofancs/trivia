package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.adaptionsoft.games.uglytrivia.question.QuestionCategory.POP;
import static com.adaptionsoft.games.uglytrivia.question.QuestionCategory.ROCK;
import static com.adaptionsoft.games.uglytrivia.question.QuestionCategory.SIENCE;
import static com.adaptionsoft.games.uglytrivia.question.QuestionCategory.SPORTS;

public class Game {
    GameBoard gameBoard;
    List<Player> players = new ArrayList();
    //FIXME: extract magic number to constan - 6 max player count
    List<Player> penaltyBox = new ArrayList();
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        gameBoard = new GameBoard(12);
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    //TODO: should be in GameRunner
    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public void add(String playerName) {
        //TODO: check for max player size
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (penaltyBox.contains(currentPlayer)) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                gameBoard.movePlayer(currentPlayer, roll);

                System.out.println(currentPlayer.getName()
                        + "'s new location is "
                        + currentPlayer.getPosition());
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            gameBoard.movePlayer(currentPlayer, roll);
            System.out.println(currentPlayer.getName()
                    + "'s new location is "
                    + currentPlayer.getPosition());
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == POP.getDisplayName())
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == SIENCE.getDisplayName())
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == SPORTS.getDisplayName())
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == ROCK.getDisplayName())
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (currentPlayer.getPosition() == 0) return POP.getDisplayName();
        if (currentPlayer.getPosition() == 4) return POP.getDisplayName();
        if (currentPlayer.getPosition() == 8) return POP.getDisplayName();
        if (currentPlayer.getPosition() == 1) return SIENCE.getDisplayName();
        if (currentPlayer.getPosition() == 5) return SIENCE.getDisplayName();
        if (currentPlayer.getPosition() == 9) return SIENCE.getDisplayName();
        if (currentPlayer.getPosition() == 2) return SPORTS.getDisplayName();
        if (currentPlayer.getPosition() == 6) return SPORTS.getDisplayName();
        if (currentPlayer.getPosition() == 10) return SPORTS.getDisplayName();
        return ROCK.getDisplayName();
    }

    public boolean wasCorrectlyAnswered() {
        if (penaltyBox.contains(currentPlayer)) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.getCoin();
                System.out.println(currentPlayer.getName()
                        + " now has "
                        + currentPlayer.getPurse()
                        + " Gold Coins.");
                penaltyBox.remove(currentPlayer);
                boolean winner = didPlayerWin();
                moveToNextPlayer();
                return winner;
            } else {
                moveToNextPlayer();
                return true;
            }

        } else {
            System.out.println("Answer was corrent!!!!");
            currentPlayer.getCoin();
            System.out.println(currentPlayer.getName()
                    + " now has "
                    + currentPlayer.getPurse()
                    + " Gold Coins.");
            boolean winner = didPlayerWin();
            moveToNextPlayer();
            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        penaltyBox.add(currentPlayer);
        moveToNextPlayer();
        return true;
    }

    private void moveToNextPlayer() {
        int i = players.indexOf(currentPlayer);
        if (i < players.size() - 1) {
            currentPlayer = players.get(i + 1);
        } else {
            currentPlayer = players.get(0);
        }
    }


    private boolean didPlayerWin() {
        //TODO: condition unreadable
        return !(currentPlayer.getPurse() == 6);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
