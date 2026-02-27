package edu.ucr.C5C089_C5I146.reversedots.model;

import java.io.Serializable;
/**
 * Representa un jugador del juego con su nombre, color y estadísticas.
 */
public class Player implements Serializable {

    private final String name;
    private final Piece color;

    private int wins;
    private int losses;
    private int draws;
    /**
     * Crea un jugador con nombre y color dados
     * @param name nombre del jugador
     * @param color color asignado al jugador
     */
    public Player(String name, Piece color) {
        this.name = name;
        this.color = color;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }
    /** @return nombre del jugador */
    public String getName() {
        return name;
    }
    /** @return color asignado al jugador */
    public Piece getColor() {
        return color;
    }
    /** @return cantidad de victorias */
    public int getWins() {
        return wins;
    }
    /** @param wins nuevas victorias del jugador */
    public void setWins(int wins) {
        this.wins = wins;
    }
    /** @return cantidad de derrotas */
    public int getLosses() {
        return losses;
    }
    /** @param losses nuevas derrotas del jugador */
    public void setLosses(int losses) {
        this.losses = losses;
    }
    /** @return cantidad de empates */
    public int getDraws() {
        return draws;
    }
    /** @param draws nuevos empates del jugador */
    public void setDraws(int draws) {
        this.draws = draws;
    }
    /** Incrementa en 1 la cantidad de victorias del jugador */
    public void addWin() {
        this.wins++;
    }
    /** Incrementa en 1 la cantidad de derrotas del jugador */
    public void addLoss() {
        this.losses++;
    }
    /** Incrementa en 1 la cantidad de empates del jugador */
    public void addDraw() {
        this.draws++;
    }
}