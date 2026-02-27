package edu.ucr.C5C089_C5I146.reversedots.model;

import java.io.Serializable;
/**
 * Representa el tablero del juego y sus piezas.
 */
public class Board implements Serializable {
    private final Piece[][] cells;
    private final int size;
    /**
     * Crea un tablero cuadrado del tamaño dado.
     * El tamaño debe ser par y mayor o igual a 4.
     * @param size tamaño del tablero
     */
    public Board(int size) {
        if (size < 4 || size % 2 != 0) {
            //Se lanza "IllegalArgumentException" porque
            // se le puede pasar un dato incorrecto al metodo
            throw new IllegalArgumentException("El tamaño debe ser par y mayor e igual que 4.");
        }
        this.size = size;
        this.cells = new Piece[size][size];
        init();
    }
    /** Coloca las piezas iniciales del juego */
    public void init() {
        //Recorre el tablero y pone todas las piezas vacias
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = Piece.EMPTY;
            }

        }
        //Colocar en el centro en orden cruzado
        int mid = size/2;
        cells [mid-1][mid-1] = Piece.WHITE;
        cells [mid-1][mid] = Piece.BLACK;
        cells [mid][mid-1] = Piece.BLACK;
        cells [mid][mid] = Piece.WHITE;

    }
    /**
     * Devuelve la pieza en una posición dada.
     * @param row fila
     * @param col columna
     * @return pieza en la posición
     */
    public Piece getPiece(int row, int col) {
        return cells[row][col];
    }
    /**
     * Coloca una pieza en la posición dada.
     * @param row fila
     * @param col columna
     * @param piece pieza a colocar
     */
    public void setPiece(int row, int col, Piece piece) {
        cells[row][col] = piece;
    }
    /**
     * Devuelve el tamaño del tablero.
     * @return tamaño del tablero
     */
    public int getSize() {
        return size;
    }
}
