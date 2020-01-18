package com.adaptionsoft.games.trivia.runner;

public class ConsoleGameStatusHandlerImpl implements GameStatusHandler {

  @Override
  public void displayStatus(String status) {
    System.out.println(status);
  }
}
