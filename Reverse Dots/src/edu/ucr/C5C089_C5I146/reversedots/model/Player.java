package edu.ucr.C5C089_C5I146.reversedots.model;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private Piece color;
    private int gamesWon;
    private int gamesLost;

    public Player(String name, Piece color) {
        this.name = name;
        this.color = color;
        this.gamesWon = 0;
        this.gamesLost = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Piece getColor() {
        return color;
    }

    public void setColor(Piece color) {
        this.color = color;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }
}
