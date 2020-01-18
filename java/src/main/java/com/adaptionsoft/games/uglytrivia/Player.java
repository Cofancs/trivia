package com.adaptionsoft.games.uglytrivia;

public class Player {

    private String name;
    private int purse;
    private int position;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", purse=" + purse +
                ", position=" + position +
                '}';
    }
}
