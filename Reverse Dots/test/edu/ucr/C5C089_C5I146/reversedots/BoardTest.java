package edu.ucr.C5C089_C5I146.reversedots;

import edu.ucr.C5C089_C5I146.reversedots.model.Board;
import edu.ucr.C5C089_C5I146.reversedots.model.Piece;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testValidBoardSize() {
        Board board = new Board(8);
        assertEquals(8, board.getSize());
    }

    @Test
    public void testInvalidSmallBoard() {
        assertThrows(IllegalArgumentException.class, () -> new Board(2));
    }

    @Test
    public void testInvalidOddBoard() {
        assertThrows(IllegalArgumentException.class, () -> new Board(5));
    }

    @Test
    public void testInitialCenterPieces() {
        Board b = new Board(8);
        int mid = 4;

        assertEquals(Piece.WHITE, b.getPiece(mid - 1, mid - 1));
        assertEquals(Piece.BLACK, b.getPiece(mid - 1, mid));
        assertEquals(Piece.BLACK, b.getPiece(mid, mid - 1));
        assertEquals(Piece.WHITE, b.getPiece(mid, mid));
    }

    @Test
    public void testSetAndGetPiece() {
        Board b = new Board(8);
        b.setPiece(0, 0, Piece.BLACK);
        assertEquals(Piece.BLACK, b.getPiece(0, 0));
    }
}