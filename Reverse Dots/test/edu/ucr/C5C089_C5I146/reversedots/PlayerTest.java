package edu.ucr.C5C089_C5I146.reversedots;

import edu.ucr.C5C089_C5I146.reversedots.model.Piece;
import edu.ucr.C5C089_C5I146.reversedots.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testCreatePlayer() {
        Player player = new Player("Ana", Piece.BLACK);
        assertEquals("Ana", player.getName());
        assertEquals(Piece.BLACK, player.getColor());
    }

    @Test
    public void testInitialStatsAreZero() {
        Player player = new Player("Ana", Piece.BLACK);
        assertEquals(0, player.getWins());
        assertEquals(0, player.getLosses());
        assertEquals(0, player.getDraws());
    }

    @Test
    public void testAddWin() {
        Player player = new Player("Ana", Piece.BLACK);
        player.addWin();
        assertEquals(1, player.getWins());
    }

    @Test
    public void testAddLoss() {
        Player player = new Player("Ana", Piece.BLACK);
        player.addLoss();
        assertEquals(1, player.getLosses());
    }

    @Test
    public void testAddDraw() {
        Player player = new Player("Ana", Piece.BLACK);
        player.addDraw();
        assertEquals(1, player.getDraws());
    }
}