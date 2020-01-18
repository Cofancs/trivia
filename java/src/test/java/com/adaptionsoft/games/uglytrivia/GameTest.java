package com.adaptionsoft.games.uglytrivia;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

  private Game undertest;

  @Before
  public void setUp() throws Exception {
    undertest = new Game();
  }

  @Test
  public void wasCorrectlyAnsweredAddsCoinToCurrentPlayer(){
    //GIVEN
    undertest.add("testPlayer");
    //WHEN
    undertest.wasCorrectlyAnswered();
    //THEN
    Assert.assertEquals(1, undertest.purses[0]);
  }

  @Test
  public void nextPlayerWasCalledAfterCorrectAnswer(){
    //GIVEN
    undertest.add("testPlayer");
    //WHEN
    undertest.wasCorrectlyAnswered();
    //THEN
    Assert.assertEquals(1, undertest.currentPlayer);
  }

  @Test
  public void nextPlayerIsFirstPlayerWhenLastPlayerAnsweredCorrectly(){
    //GIVEN
    undertest.add("testPlayer1");
    undertest.add("testPlayer2");
    undertest.add("testPlayer3");
    undertest.currentPlayer = 2;
    //WHEN
    undertest.wasCorrectlyAnswered();
    //THEN
    Assert.assertEquals(0, undertest.currentPlayer);
  }
}
