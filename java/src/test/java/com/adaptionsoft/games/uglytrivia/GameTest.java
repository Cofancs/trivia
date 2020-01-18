package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    private Game underTest;

    @Before
    public void setUp() {
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
    public void testAdd() {
        //GIVEN

        //WHEN
        boolean actual = underTest.add("test");
        //THEN
        assertFalse(underTest.players.isEmpty());
        int[] places = underTest.places;
        int[] expectedPlaces = new int[6];
        expectedPlaces[1] = 0;
        assertArrayEquals(expectedPlaces, places);
        assertTrue(true);

    }

    @Test
    public void testHowManyPlayer() {
        //GIVEN
        ArrayList players = underTest.players;
        players.add("test");
        players.add("test2");

        //WHEN
        int actual = underTest.howManyPlayers();
        //THEN
        assertEquals(2, actual);
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

    @Test
    public void wrongAnswerPutsPlayerToPenaltyBox() {
        //GIVEN
        underTest.add("testPlayer");
        //WHEN
        underTest.wrongAnswer();
        //THEN
        Assert.assertTrue(underTest.inPenaltyBox[0]);
    }

    @Test
    public void nextPlayerIsCalledAfterWrongAnswer() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.add("testPlayer2");
        //WHEN
        underTest.wrongAnswer();
        //THEN
        Assert.assertEquals(1, underTest.currentPlayer);
    }

    @Test
    public void firstPlayerIsCalledAfterLastPlayerWrongAnswer() {
        //GIVEN
        underTest.add("testPlayer1");
        underTest.add("testPlayer2");
        underTest.add("testPlayer3");
        underTest.currentPlayer = 2;
        //WHEN
        underTest.wrongAnswer();
        //THEN
        Assert.assertEquals(0, underTest.currentPlayer);
    }

    @Test
    public void playerPlaceIsIncrementedWithRoll(){
        //GIVEN
        underTest.add("testPlayer1");
        int testRoll = 1;
        //WHEN
        underTest.roll(testRoll);
        //THEN
        Assert.assertEquals(1, underTest.places[0]);
    }

    @Test
    public void playerStartedANewRoundWhenPlaceIsGreaterThanTwelve(){
        //GIVEN
        underTest.add("testPlayer1");
        int boardSize = 12;
        int currentPlace = 9;
        int testRoll = 4;
        underTest.places[0] = currentPlace;
        int expectedPlaceAfterRoll = currentPlace + testRoll - boardSize;
        //WHEN
        underTest.roll(testRoll);
        //THEN
        Assert.assertEquals(expectedPlaceAfterRoll, underTest.places[0]);
    }

    @Test
    public void playerIsGettingOutOfPenaltyBoxWithOddRoll(){
        //GIVEN
        underTest.add("testPlayer");
        underTest.inPenaltyBox[0] = true;
        int oddRoll = 1;
        //WHEN
        underTest.roll(oddRoll);
        //THEN
        Assert.assertTrue(underTest.isGettingOutOfPenaltyBox);
    }

    @Test
    public void playerIsGettingOutOfPenaltyBoxWithEvenRoll(){
        //GIVEN
        underTest.add("testPlayer");
        underTest.inPenaltyBox[0] = true;
        int evenRoll = 2;
        //WHEN
        underTest.roll(evenRoll);
        //THEN
        Assert.assertFalse(underTest.isGettingOutOfPenaltyBox);
    }


    @Test
    public void playerIsGettingOutOfPenaltyBoxStartsANewRoundWhenPlaceIsGreaterThanTwelve(){
        //GIVEN
        underTest.add("testPlayer");
        underTest.inPenaltyBox[0] = true;
        int boardSize = 12;
        int currentPlace = 9;
        underTest.places[0] = currentPlace;
        int oddRoll = 5;
        int expectedPlaceAfterRoll = currentPlace + oddRoll - boardSize;
        //WHEN
        underTest.roll(oddRoll);
        //THEN
        Assert.assertEquals(expectedPlaceAfterRoll, underTest.places[0]);
    }

    @Test
    public void playerWinsWhenGainsSixCoins(){
        //GIVEN
        underTest.add("testPlayer");
        underTest.purses[0] = 5;
        //WHEN
        boolean isWinner = underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertFalse(isWinner);
    }

    @Test
    public void playerWinsWhenGainsSixCoinsFromPenaltyBox(){
        //GIVEN
        underTest.add("testPlayer");
        underTest.purses[0] = 5;
        underTest.inPenaltyBox[0] = true;
        underTest.isGettingOutOfPenaltyBox = true;
        //WHEN
        boolean isWinner = underTest.wasCorrectlyAnswered();
        //THEN
        Assert.assertFalse(isWinner);
    }
}
