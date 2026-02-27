package edu.ucr.C5C089_C5I146.reversedots.repository;

import edu.ucr.C5C089_C5I146.reversedots.model.*;

import java.io.*;
/**
 * Guarda y carga partidas usando archivos de texto
 */
public class TextGameRepository implements GameRepository {

    @Override
    public void save(String path, Game game) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            writer.write("SIZE=" + game.getBoard().getSize());
            writer.newLine();

            writer.write("CURRENT=" + game.getCurrentPlayer().getColor());
            writer.newLine();

            writer.write("GAMEOVER=" + game.isGameOver());
            writer.newLine();
            writer.newLine();

            writer.write("PLAYER1=" + game.getPlayer1().getName());
            writer.newLine();

            writer.write("PLAYER2=" + game.getPlayer2().getName());
            writer.newLine();
            writer.newLine();

            writer.write("BOARD=");
            writer.newLine();

            Board board = game.getBoard();
            int size = board.getSize();

            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    writer.write(board.getPiece(row, col).name());
                    if (col < size - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar", e);
        }
    }

    @Override
    public Game load(String path) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            int size = Integer.parseInt(reader.readLine().split("=")[1]);
            Piece currentColor = Piece.valueOf(reader.readLine().split("=")[1]);
            boolean gameOver = Boolean.parseBoolean(reader.readLine().split("=")[1]);

            reader.readLine();

            String player1Name = reader.readLine().split("=")[1];
            String player2Name = reader.readLine().split("=")[1];

            reader.readLine();
            reader.readLine();

            Board board = new Board(size);

            for (int row = 0; row < size; row++) {
                String[] rowData = reader.readLine().split(" ");
                for (int col = 0; col < size; col++) {
                    board.setPiece(row, col, Piece.valueOf(rowData[col]));
                }
            }

            reader.close();

            Player player1 = new Player(player1Name, Piece.BLACK);
            Player player2 = new Player(player2Name, Piece.WHITE);

            Game game = new Game(board, player1, player2, gameOver);

            if (game.getCurrentPlayer().getColor() != currentColor) {
                game.switchTurn();
            }

            return game;

        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar", e);
        }
    }

    @Override
    public boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }
}