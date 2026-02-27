package edu.ucr.C5C089_C5I146.reversedots;

import edu.ucr.C5C089_C5I146.reversedots.model.Board;
import edu.ucr.C5C089_C5I146.reversedots.model.Game;
import edu.ucr.C5C089_C5I146.reversedots.model.Piece;
import edu.ucr.C5C089_C5I146.reversedots.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class GameTest {

    private Game createDefaultGame() {
        Board board = new Board(8);
        Player player = new Player("P1", Piece.BLACK);
        Player player2 = new Player("P2", Piece.WHITE);
        return new Game(board, player, player2, false);
    }

    @Test
    public void testCurrentPlayerIsBlackFirst() {
        Game game = createDefaultGame();
        assertEquals(Piece.BLACK, game.getCurrentPlayer().getColor());
    }

    @Test
    public void testSwitchTurn() {
        Game game = createDefaultGame();
        game.switchTurn();
        assertEquals(Piece.WHITE, game.getCurrentPlayer().getColor());
    }

    @Test
    public void testValidMoveDetection() {
        Game game = createDefaultGame();
        assertTrue(game.isValidMove(2, 3, Piece.BLACK));
    }

    @Test
    public void testGetValidMovesNotEmpty() {
        Game game = createDefaultGame();
        List<int[]> moves = game.getValidMoves(Piece.BLACK);
        assertFalse(moves.isEmpty());
    }

    @Test
    public void testGameEndsWhenNoMoves() {
        Game game = createDefaultGame();
        game.checkGameOver();
        assertFalse(game.isGameOver());
    }
}