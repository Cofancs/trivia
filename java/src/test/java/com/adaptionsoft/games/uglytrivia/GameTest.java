package com.adaptionsoft.games.uglytrivia;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new Game();
    }

    @Test
    public void wasCorrectlyAnsweredAddsCoinToCurrentPlayer() {
        //GIVEN
        underTest.add("testPlayer");
        //WHEN
        underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertEquals(1, underTest.purses[0]);
    }

    @Test
    public void nextPlayerWasCalledAfterCorrectAnswer() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.add("testPlayer2");
        //WHEN
        underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertEquals(1, underTest.currentPlayer);
    }

    @Test
    public void nextPlayerIsFirstPlayerWhenLastPlayerAnsweredCorrectly() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.add("testPlayer2");
        underTest.add("testPlayer3");
        underTest.currentPlayer = 2;
        //WHEN
        underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertEquals(0, underTest.currentPlayer);
    }

    @Test
    public void gettingOutFromPenaltyBoxWithCorrectAnswerAddsCoin() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.inPenaltyBox[0] = true;
        underTest.isGettingOutOfPenaltyBox = true;
        //WHEN
        underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertEquals(1, underTest.purses[0]);
    }

    @Test
    public void gettingOutFromPenaltyBoxWithCorrectAnswerCallsNextPlayer() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.add("testPlayer2");
        underTest.inPenaltyBox[0] = true;
        underTest.isGettingOutOfPenaltyBox = true;
        //WHEN
        underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertEquals(1, underTest.currentPlayer);
    }

    @Test
    public void gettingOutFromPenaltyBoxWithLastPlayerCallsFirstPlayer() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.add("testPlayer2");
        underTest.add("testPlayer3");
        underTest.currentPlayer = 2;
        underTest.inPenaltyBox[2] = true;
        underTest.isGettingOutOfPenaltyBox = true;
        //WHEN
        underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertEquals(0, underTest.currentPlayer);
    }

    @Test
    public void penaltyBoxWithoutGettingOutFlagCallsNextPlayer() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.add("testPlayer2");
        underTest.inPenaltyBox[0] = true;
        underTest.isGettingOutOfPenaltyBox = false;
        //WHEN
        underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertEquals(1, underTest.currentPlayer);
    }

    @Test
    public void penaltyBoxWithoutGettingOutFlagWithLastPlayerCallsFirstPlayer() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.add("testPlayer2");
        underTest.add("testPlayer3");
        underTest.currentPlayer = 2;
        underTest.inPenaltyBox[2] = true;
        underTest.isGettingOutOfPenaltyBox = false;
        //WHEN
        underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertEquals(0, underTest.currentPlayer);
    }
}
