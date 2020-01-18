package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

  private Game underTest;

  @Before
  public void setUp() throws Exception {
    underTest = new Game();
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
