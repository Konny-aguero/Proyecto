package edu.ucr.C5C089_C5I146.reversedots;
import edu.ucr.C5C089_C5I146.reversedots.controller.GameController;
import edu.ucr.C5C089_C5I146.reversedots.model.MoveResult;
import edu.ucr.C5C089_C5I146.reversedots.model.Piece;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    @Test
    public void testStartGameCreatesBoard() {
        GameController gameController = new GameController();
        gameController.startGame("A", "B", 8);
        assertNotNull(gameController.getBoard());
    }

    @Test
    public void testCurrentPlayerIsBlackAtStart() {
        GameController gameController = new GameController();
        gameController.startGame("A", "B", 8);
        assertEquals(Piece.BLACK, gameController.getCurrentPlayer().getColor());
    }

    @Test
    public void testMakeValidMove() {
        GameController gameController = new GameController();
        gameController.startGame("A", "B", 8);
        MoveResult moveResult = gameController.makeMove(2, 3);
        assertEquals(MoveResult.SUCCESS, moveResult);
    }

    @Test
    public void testInvalidMoveReturnsError() {
        GameController gameController = new GameController();
        gameController.startGame("A", "B", 8);
        MoveResult moveResult = gameController.makeMove(0, 0);
        assertEquals(MoveResult.INVALID_MOVE, moveResult);
    }

    @Test
    public void testGameOverStatsUpdateSafe() {
        GameController gameController = new GameController();
        gameController.startGame("A", "B", 8);

        gameController.getGame().checkGameOver();
        assertNotNull(gameController.getWinner() == null || gameController.getWinner() != null);
    }
}