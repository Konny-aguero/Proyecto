package edu.ucr.C5C089_C5I146.reversedots.model;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private Piece color;

    private int wins;
    private int losses;
    private int draws;

    public Player(String name, Piece color) {
        this.name = name;
        this.color = color;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    // 🔹 Getters básicos (probablemente ya los usa Game o View)

    public String getName() {
        return name;
    }

    public Piece getColor() {
        return color;
    }

//    public void setColor(Piece color) {
//        this.color = color;
//    }

    // 🔹 Estadísticas

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    // 🔹 Métodos auxiliares útiles y simples

    public void addWin() {
        this.wins++;
    }

    public void addLoss() {
        this.losses++;
    }

    public void addDraw() {
        this.draws++;
    }
}