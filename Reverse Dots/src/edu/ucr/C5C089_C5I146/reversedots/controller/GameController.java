package edu.ucr.C5C089_C5I146.reversedots.controller;

import edu.ucr.C5C089_C5I146.reversedots.model.*;
import edu.ucr.C5C089_C5I146.reversedots.repository.FileGameRepository;

import java.io.File;
import java.io.IOException;

public class GameController {

    private FileGameRepository repository = new FileGameRepository();

    private Game game;

    // Constructor vacío
    public GameController() {
    }

    // Crear una nueva partida
    public void startGame(String name1, String name2, int size) {

        Board board = new Board(size);

        Player player1 = new Player(name1, Piece.BLACK);
        Player player2 = new Player(name2, Piece.WHITE);

        game = new Game(board, player1, player2, false);
    }

    //  Realizar movimiento
    public MoveResult makeMove(int row, int col) {

        if (game == null) {
            return MoveResult.GAME_ALREADY_FINISHED;
        }

        Piece currentColor = game.getCurrentPlayer().getColor();

        return game.makeMove(row, col, currentColor);
    }

    //  Obtener tablero
    public Board getBoard() {
        if (game == null) {
            return null;
        }
        return game.getBoard();
    }

    // Obtener jugador actual
    public Player getCurrentPlayer() {
        if (game == null) {
            return null;
        }
        return game.getCurrentPlayer();
    }

    //  Verificar si terminó
    public boolean isGameOver() {
        if (game == null) {
            return false;
        }
        return game.isGameOver();
    }

    //  Obtener ganador
    public Player getWinner() {
        if (game == null) {
            return null;
        }
        return game.getWinner();
    }

    //  Obtener puntajes
    public int[] getScores() {
        if (game == null) {
            return null;
        }
        return game.getScores();
    }

    public int getPlayer1Score() {
        int[] scores = game.getScores();
        return (game.getPlayer1().getColor() == Piece.BLACK)
                ? scores[0]
                : scores[1];
    }

    public int getPlayer2Score() {
        int[] scores = game.getScores();
        return (game.getPlayer2().getColor() == Piece.BLACK)
                ? scores[0]
                : scores[1];
    }

    public String getPlayer1Name() {
        return game.getPlayer1().getName();
    }

    public String getPlayer2Name() {
        return game.getPlayer2().getName();
    }

    public Game getGame() {
        return game;
    }

    public void saveGame(File file) throws IOException {
        repository.save(file, game);
    }

    public void loadGame(File file) throws IOException, ClassNotFoundException {
        game = repository.load(file);
    }

    public boolean saveEGame(java.io.File file) {
        // Por ahora solo simulamos que guarda bien
        System.out.println("Guardando partida en: " + file.getAbsolutePath());
        return true;
    }


}