package com.adaptionsoft.games.uglytrivia;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameTest {

  private Game underTest;

  @Before
  public void setUp() throws Exception {
    underTest = new Game();
  }

  @Test
  public void wasCorrectlyAnsweredAddsCoinToCurrentPlayer(){
    //GIVEN
    underTest.add("testPlayer");
    //WHEN
    underTest.wasCorrectlyAnswered();
    //THEN
    Assert.assertEquals(1, underTest.purses[0]);
  }

  @Test
  public void nextPlayerWasCalledAfterCorrectAnswer(){
    //GIVEN
    underTest.add("testPlayer1");
    underTest.add("testPlayer2");
    //WHEN
    underTest.wasCorrectlyAnswered();
    //THEN
    Assert.assertEquals(1, underTest.currentPlayer);
  }

  @Test
  public void nextPlayerIsFirstPlayerWhenLastPlayerAnsweredCorrectly(){
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
}
