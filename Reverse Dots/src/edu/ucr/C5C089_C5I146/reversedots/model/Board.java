package edu.ucr.C5C089_C5I146.reversedots.model;

public class Board {
    private Piece[][] cells;
    private int size;

    public Board(int size) {
        if (size <= 4 || size % 2 != 0) {
            //Se lanza "IllegalArgumentException" porque
            // se le puede pasar un dato incorrecto al metodo
            throw new IllegalArgumentException("El tamaño debe ser par y mayor e igual que 4.");
        }
        this.size = size;
        this.cells = new Piece[size][size];
        init();
    }

    public void init() {
        //Recorrer el tablero y poner todas las piezas vacias
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

    public Piece getPiece(int row, int col) {
        return cells[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        cells[row][col] = piece;
    }

    public int getSize() {
        return size;
    }
}
