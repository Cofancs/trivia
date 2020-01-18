package com.adaptionsoft.games.uglytrivia;

public class Player {

    private String name;
    private int purse;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPurse() {
        return purse;
    }

    public void getCoin() {
        purse++;
    }
}
