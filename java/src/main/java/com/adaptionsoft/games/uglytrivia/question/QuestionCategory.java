package com.adaptionsoft.games.uglytrivia.question;

public enum QuestionCategory {
  POP("Pop"), SIENCE("Science"), SPORTS("Sports"), ROCK("Rock");

  private String displayName;

  QuestionCategory(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
