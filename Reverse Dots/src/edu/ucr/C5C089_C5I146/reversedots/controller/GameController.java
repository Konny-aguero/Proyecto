package edu.ucr.C5C089_C5I146.reversedots.controller;

import edu.ucr.C5C089_C5I146.reversedots.model.*;
import edu.ucr.C5C089_C5I146.reversedots.repository.GameRepository;
import edu.ucr.C5C089_C5I146.reversedots.repository.PlayerRepository;
import edu.ucr.C5C089_C5I146.reversedots.repository.TextGameRepository;
import edu.ucr.C5C089_C5I146.reversedots.repository.TextPlayerRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la partida, crea el juego, procesa movimientos
 * y coordina guardado/carga de datos y estadísticas.
 */
public class GameController {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    private Game game;

    /**
     * Crea el controlador usando repositorios dados.
     * @param gameRepository repositorio de partidas
     * @param playerRepository repositorio de jugadores
     */
    public GameController(GameRepository gameRepository,
                          PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    /**Crea el controlador usando repositorios por defecto*/
    public GameController() {
        this(new TextGameRepository(), new TextPlayerRepository());
    }

    /**
     * Inicia una nueva partida con dos jugadores y un tablero del tamaño dado.
     * @param name1 nombre del jugador 1
     * @param name2 nombre del jugador 2
     * @param size tamaño del tablero
     */
    public void startGame(String name1, String name2, int size) {

        Board board = new Board(size);

        Player player1 = new Player(name1, Piece.BLACK);
        Player player2 = new Player(name2, Piece.WHITE);

        game = new Game(board, player1, player2, false);
    }
    /**
     * Intenta realizar un movimiento en la casilla indicada.
     * @param row fila donde se quiere jugar
     * @param col columna donde se quiere jugar
     * @return resultado del intento
     */
    public MoveResult makeMove(int row, int col) {

        if (game == null) {
            return MoveResult.GAME_ALREADY_FINISHED;
        }

        Piece currentColor = game.getCurrentPlayer().getColor();
        MoveResult result = game.makeMove(row, col, currentColor);

        if (result == MoveResult.SUCCESS && game.isGameOver()) {
            updatePlayerStats();
        }

        return result;
    }
    /** @return el tablero actual o null si no hay partida */
    public Board getBoard() {
        return game == null ? null : game.getBoard();
    }
    /** @return el jugador actual o null si no hay partida */
    public Player getCurrentPlayer() {
        return game == null ? null : game.getCurrentPlayer();
    }
    /** @return true si la partida ya terminó */
    public boolean isGameOver() {
        return game != null && game.isGameOver();
    }
    /** @return el ganador o null si fue empate */
    public Player getWinner() {
        return game == null ? null : game.getWinner();
    }
    /** @return puntaje del jugador 1 */
    public int getPlayer1Score() {
        int[] scores = game.getScores();
        return (game.getPlayer1().getColor() == Piece.BLACK)
                ? scores[0]
                : scores[1];
    }
    /** @return puntaje del jugador 2 */
    public int getPlayer2Score() {
        int[] scores = game.getScores();
        return (game.getPlayer2().getColor() == Piece.BLACK)
                ? scores[0]
                : scores[1];
    }
    /** @return Nombre del jugador 1 */
    public String getPlayer1Name() {
        return game.getPlayer1().getName();
    }
    /** @return Nombre del jugador 2 */
    public String getPlayer2Name() {
        return game.getPlayer2().getName();
    }
    /** @return la partida completa */
    public Game getGame() {
        return game;
    }
    /** @return Lista de movimientos válidos para el turno actual */
    public List<int[]> getValidMoves() {
        if (game == null) return new ArrayList<>();
        return game.getValidMoves(game.getCurrentPlayer().getColor());
    }
    /**
     * Carga una partida desde un archivo
     * @param file archivo desde el cual cargar
     * @return true si se cargó bien
     */
    public boolean loadGame(File file) {
        Game loaded = gameRepository.load(file.getAbsolutePath());

        if (loaded == null) {
            return false;
        }

        this.game = loaded;
        return true;
    }
    /**
     * Versión alternativa de carga usada por la vista.
     * @param file archivo desde el cual cargar
     * @return true si se cargó correctamente
     */
    public boolean loadEGame(File file) {
        return loadGame(file);
    }
    /**
     * Guarda la partida con manejo simple de errores.
     * @param file archivo donde guardar
     * @return true si se guardó correctamente
     */
    public boolean saveEGame(File file) {
        if (game == null) {
            return false;
        }

        try {
            gameRepository.save(file.getAbsolutePath(), game);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void updatePlayerStats() {

        Player winner = game.getWinner();

        Player p1 = game.getPlayer1();
        Player p2 = game.getPlayer2();

        // Si no existen en archivos, guardarlos primero
        if (!playerRepository.exists(p1.getName())) {
            playerRepository.save(p1);
        } else {
            Player stored = playerRepository.load(p1.getName());
            p1.setWins(stored.getWins());
            p1.setLosses(stored.getLosses());
            p1.setDraws(stored.getDraws());
        }

        if (!playerRepository.exists(p2.getName())) {
            playerRepository.save(p2);
        } else {
            Player stored = playerRepository.load(p2.getName());
            p2.setWins(stored.getWins());
            p2.setLosses(stored.getLosses());
            p2.setDraws(stored.getDraws());
        }

        if (winner == null) {
            p1.addDraw();
            p2.addDraw();
        } else if (winner == p1) {
            p1.addWin();
            p2.addLoss();
        } else {
            p2.addWin();
            p1.addLoss();
        }

        playerRepository.update(p1);
        playerRepository.update(p2);
    }
    /**
     * Devuelve información de los jugadores registrados.
     * @return texto con nombres y estadísticas
     */
    public String getRegisteredPlayersInfo() {

        StringBuilder sb = new StringBuilder();

        java.io.File folder = new java.io.File("players");

        if (!folder.exists() || folder.listFiles() == null) {
            return "No hay jugadores registrados.";
        }

        java.io.File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            return "No hay jugadores registrados.";
        }
        for (java.io.File file : files) {

            String name = file.getName().replace(".txt", "");

            Player player = playerRepository.load(name);

            if (player != null) {
                sb.append("Jugador: ").append(player.getName()).append("\n");
                sb.append("Ganadas: ").append(player.getWins()).append("\n");
                sb.append("Perdidas: ").append(player.getLosses()).append("\n");
                sb.append("Empates: ").append(player.getDraws()).append("\n");
                sb.append("----------------------------\n");
            }
        }

        return sb.toString();
    }

}