package edu.ucr.C5C089_C5I146.reversedots.model;

public class Game {
    private Board board;
    private Player player1, player2;
    private Player currentPlayer;
    private boolean isGameOver;

    public Game(Board board, Player player1, Player player2, boolean isGameOver) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.isGameOver = false;
    }
    public void FirstPlayer(Player player1, Player player2){
        if(player1.getColor() == Piece.BLACK){
            currentPlayer = player1;
        }
        else{
            currentPlayer = player2;
        }
    }
    public boolean isValidMove(int row, int col, Piece color){
        //verificar que no esté vacío.
        if(board.getPiece(row, col) != Piece.EMPTY){
            return false;
        }
        int[][] direcciones = {
                {-1, 0}, {1, 0},  {0, -1}, {0, 1}, // Arriba, Abajo, Izquierda, Derecha
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Las 4 diagonales
        };
        //TODO Falta terminar metodo
        return true;
    }
}
