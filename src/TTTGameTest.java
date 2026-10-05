import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TTTGameTest
{
    private TTTGame g;

    @Before
    public void setUp()
    {
        g = new TTTGame();
    }

    @Test
    public void xMovesFirstAndPlayersAlternate()
    {
        assertEquals("X", g.getCurrentPlayer());
        assertEquals(MoveResult.CONTINUE, g.play(0, 0));
        assertEquals("O", g.getCurrentPlayer());
        assertEquals(MoveResult.CONTINUE, g.play(1, 1));
        assertEquals("X", g.getCurrentPlayer());
        assertEquals(2, g.getMoveCount());
    }

    @Test
    public void illegalMoveKeepsSamePlayer()
    {
        g.play(0, 0);
        assertEquals(MoveResult.ILLEGAL, g.play(0, 0));
        assertEquals("O", g.getCurrentPlayer());
        assertEquals(1, g.getMoveCount());
    }

    @Test
    public void xWinsTopRow()
    {
        g.play(0, 0); g.play(1, 0);
        g.play(0, 1); g.play(1, 1);
        assertEquals(MoveResult.WIN, g.play(0, 2));
        assertEquals("X", g.getCurrentPlayer());
    }

    @Test
    public void earlyTieIsDetected()
    {
        // after 8 moves every line holds an X and an O, so nobody can win
        int[][] moves = {{0, 0}, {0, 1}, {0, 2}, {1, 1}, {1, 0}, {1, 2}, {2, 1}};
        for (int[] m : moves)
            assertEquals(MoveResult.CONTINUE, g.play(m[0], m[1]));
        assertEquals(MoveResult.TIE, g.play(2, 0));
    }

    @Test
    public void resetStartsOver()
    {
        g.play(0, 0);
        g.reset();
        assertEquals("X", g.getCurrentPlayer());
        assertEquals(0, g.getMoveCount());
        assertTrue(g.getBoard().isValidMove(0, 0));
    }
}
