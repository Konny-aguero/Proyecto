package edu.ucr.C5C089_C5I146.reversedots.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private Player player1, player2;
    private Player color;
    private boolean isGameOver;

    public Game(Board board, Player player1, Player player2, boolean isGameOver) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.isGameOver = isGameOver;
        firstPlayer();
    }

    public void firstPlayer() {
        if (player1.getColor() == Piece.BLACK) {
            color = player1;
        } else {
            color = player2;
        }
    }

    public Player getCurrentPlayer() {
        return color;
    }

    public void switchTurn() {
        if (color == player1) {
            color = player2;
        } else {
            color = player1;
        }
    }

    //metodo de encerrar
    private boolean canCaptureInDirection(int startRow, int startCol, int dRow, int dCol, Piece mine, Piece enemy) {
        int row = startRow + dRow;
        int col = startCol + dCol;
        boolean hasEnemyInBetween = false;

        // va celda por celda usando un for para el recorrido
        while (row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize()) {
            Piece current = board.getPiece(row, col);

            // ver que hay en la celda
            if (current == enemy) {
                hasEnemyInBetween = true; // hay un enemigo, avanza a buscar mas
            } else if (current == mine) {
                return hasEnemyInBetween; // si hay enemigo antes, true, sino, es false.
            } else {
                return false; // es Piece.EMPTY, la línea se rompe
            }
            row += dRow;
            col += dCol;
        }
        return false;
    }

    public boolean isValidMove(int row, int col, Piece color) {
        //verificar que esté en el tablero.
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            return false;
        }
        //verificar que no esté vacío.
        if (board.getPiece(row, col) != Piece.EMPTY) {
            return false;
        }
        //si el color del juga
        Piece myColor = color;
        Piece enemyColor;
        if (myColor == Piece.BLACK) {
            enemyColor = Piece.WHITE;
        } else {
            enemyColor = Piece.BLACK;
        }

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // Arriba, Abajo, Izquierda, Derecha
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Las 4 diagonales
        };
        for (int i = 0; i < directions.length; i++) {
            int dirRow = directions[i][0]; //indice de fila "0"
            int dirCol = directions[i][1]; //indice de columna "1"

            if (canCaptureInDirection(row, col, dirRow, dirCol, myColor, enemyColor)) {
                return true;
            }
        }
        return false;
    }

    public List<int[]> getValidMoves(Piece color) {
        List<int[]> moves = new ArrayList<>();
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (isValidMove(row, col, color)) {
                    moves.add(new int[]{row, col});
                }
            }
        }
        return moves;
    }

    public boolean makeMove(int row, int col, Piece color) {
        if (!isValidMove(row, col, color)) {
            return false;
        }

        Piece myColor = color;
        Piece enemyColor;
        if (myColor == Piece.BLACK) {
            enemyColor = Piece.WHITE;
        } else {
            enemyColor = Piece.BLACK;
        }

        // colocar la pieza inicial
        board.setPiece(row, col, myColor);

        // definir direcciones
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        // voltea piezas en todas las direcciones validas
        for (int i = 0; i < directions.length; i++) {
            int dirRow = directions[i][0];
            int dirCol = directions[i][1];

            if (canCaptureInDirection(row, col, dirRow, dirCol, myColor, enemyColor)) {
                flipPieces(row, col, dirRow, dirCol, myColor, enemyColor);
            }
        }

        // cambia el turno
        switchTurn();
        return true;
    }

    private void flipPieces(int startRow, int startCol, int dRow, int dCol, Piece mine, Piece enemy) {
        int row = startRow + dRow;
        int col = startCol + dCol;

        // es una dirección válida por el canCaptureInDirection
        while (row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize() && board.getPiece(row, col) == enemy) {
            board.setPiece(row, col, mine); // voltea la pieza
            row += dRow;
            col += dCol;
        }
    }

    public boolean checkGameOver(Piece color) {
        // si hay movimientos posibles para el jugador actual entonces no ha terminado
        if (!getValidMoves(color).isEmpty()) {
            return false;
        }

        // si el actual no puede entonces verificamos si el otro puede
        switchTurn();
        boolean canEnemyMoves = !getValidMoves(color).isEmpty();
        switchTurn(); // regresa al turno a como estaba

        if (!canEnemyMoves) {
            this.isGameOver = true;
            return true;
        }

        return false;
    }

    public int[] getScores() {
        int blackScore = 0;
        int whiteScore = 0;

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Piece piece = board.getPiece(row, col);
                if (piece == Piece.BLACK) {
                    blackScore++;
                } else if (piece == Piece.WHITE) {
                    whiteScore++;
                }
            }
        }
        // retorna un arreglo donde [0] es negro y [1] es blanco
        return new int[]{blackScore, whiteScore};
    }

}
