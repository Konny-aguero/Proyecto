package edu.ucr.C5C089_C5I146.reversedots.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Maneja la lógica principal del juego como turnos, movimientos y resultado
 */
public class Game implements Serializable {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player color;
    private boolean isGameOver;
    /**
     * Crea un juego con tablero y jugadores dados
     * @param board tablero del juego
     * @param player1 jugador 1
     * @param player2 jugador 2
     * @param isGameOver estado inicial del juego
     */
    public Game(Board board, Player player1, Player player2, boolean isGameOver) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.isGameOver = isGameOver;
        firstPlayer();
    }
    /** Define cuál jugador juega primero, empieza el color negro */
    public void firstPlayer() {
        if (player1.getColor() == Piece.BLACK) {
            color = player1;
        } else {
            color = player2;
        }
    }
    /**
     * Devuelve el jugador del turno actual.
     * @return jugador actual
     */
    public Player getCurrentPlayer() {
        return color;
    }
    /** Cambia el turno entre los dos jugadores */
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
    /**
     * Indica si un movimiento es válido en la casilla dada.
     * @param row fila a evaluar
     * @param col columna a evaluar
     * @param color color del jugador
     * @return true si el movimiento es válido
     */
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
        Piece enemyColor;
        if (color == Piece.BLACK) {
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

            if (canCaptureInDirection(row, col, dirRow, dirCol, color, enemyColor)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Devuelve la lista de movimientos válidos para un color.
     * @param color color a evaluar
     * @return lista de posiciones válidas
     */
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
    /**
     * Indica si el juego terminó.
     * @return true si terminó
     */
    public boolean isGameOver() {
        return isGameOver;
    }
    /**
     * Indica si un color aún puede jugar movimientos válidos.
     * @param color color a evaluar
     * @return true si aún puede jugar
     */
    public boolean hasValidMoves(Piece color) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (isValidMove(row, col, color)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Intenta realizar un movimiento y devuelve el resultado.
     * @param row fila donde se quiere jugar
     * @param col columna donde se quiere jugar
     * @param moveColor color del jugador
     * @return resultado del movimiento
     */
public MoveResult makeMove(int row, int col, Piece moveColor) {

    // Si el juego ya terminó
    if (isGameOver) {
        return MoveResult.GAME_ALREADY_FINISHED;
    }

    // Verificar posición válida
    if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
        return MoveResult.INVALID_POSITION;
    }

    // 3️⃣ Verificar turno
    if (moveColor != color.getColor()) {
        return MoveResult.NOT_YOUR_TURN;
    }

    // Verificar que esté vacía
    if (board.getPiece(row, col) != Piece.EMPTY) {
        return MoveResult.CELL_NOT_EMPTY;
    }

    //Verificar que sea movimiento válido (encierre piezas)
    if (!isValidMove(row, col, moveColor)) {
        return MoveResult.INVALID_MOVE;
    }

    Piece enemyColor = (moveColor == Piece.BLACK) ? Piece.WHITE : Piece.BLACK;

    //Colocar pieza
    board.setPiece(row, col, moveColor);

    int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    // Voltear piezas
    for (int[] dir : directions) {
        if (canCaptureInDirection(row, col, dir[0], dir[1], moveColor, enemyColor)) {
            flipPieces(row, col, dir[0], dir[1], moveColor, enemyColor);
        }
    }

    // Cambiar turno
    switchTurn();

    // Verificar si el nuevo jugador puede jugar
    if (!hasValidMoves(color.getColor())) {
        switchTurn();
    }

    // Verificar fin del juego
    checkGameOver();

    return MoveResult.SUCCESS;
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
    /**
     * Verifica si el juego ya no tiene movimientos posibles.
     */
public void checkGameOver() {

    Piece currentColor = color.getColor();
    Piece enemyColor = (currentColor == Piece.BLACK) ? Piece.WHITE : Piece.BLACK;

    boolean currentHasMoves = hasValidMoves(currentColor);
    boolean enemyHasMoves = hasValidMoves(enemyColor);

    if (!currentHasMoves && !enemyHasMoves) {
        isGameOver = true;
    }

}
    /**
     * Devuelve los puntajes de negro y blanco.
     * @return arreglo donde [0] es negro y [1] es blanco
     */
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
    /**
     * Devuelve el jugador ganador o null si hay empate.
     * @return jugador ganador o null
     */
    public Player getWinner() {

        if (!isGameOver) {
            return null;
        }

        int[] scores = getScores();

        if (scores[0] > scores[1]) {
            return (player1.getColor() == Piece.BLACK) ? player1 : player2;
        } else if (scores[1] > scores[0]) {
            return (player1.getColor() == Piece.WHITE) ? player1 : player2;
        } else {
            return null; // empate
        }
    }
    /** @return el tablero */
    public Board getBoard() {
        return board;
    }
    /** @return Jugador 1 */
    public Player getPlayer1() {
        return player1;
    }
    /** @return Jugador 2 */
    public Player getPlayer2() {
        return player2;
    }


}
