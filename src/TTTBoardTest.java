import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TTTBoardTest
{
    private TTTBoard b;

    @Before
    public void setUp()
    {
        b = new TTTBoard();
    }

    @Test
    public void newBoardIsEmpty()
    {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                assertEquals(" ", b.getSquare(r, c));
    }

    @Test
    public void validAndInvalidMoves()
    {
        assertTrue(b.isValidMove(1, 1));
        b.setMove(1, 1, "X");
        assertFalse(b.isValidMove(1, 1));
        assertFalse(b.isValidMove(3, 0));
        assertFalse(b.isValidMove(-1, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotTakeUsedSquare()
    {
        b.setMove(0, 0, "X");
        b.setMove(0, 0, "O");
    }

    @Test
    public void rowColAndDiagonalWins()
    {
        b.setMove(0, 0, "X"); b.setMove(0, 1, "X"); b.setMove(0, 2, "X");
        assertTrue(b.isWin("X"));
        b.clear();
        b.setMove(0, 2, "O"); b.setMove(1, 2, "O"); b.setMove(2, 2, "O");
        assertTrue(b.isWin("O"));
        b.clear();
        b.setMove(0, 2, "X"); b.setMove(1, 1, "X"); b.setMove(2, 0, "X");
        assertTrue(b.isWin("X"));
        assertFalse(b.isWin("O"));
    }

    @Test
    public void fullBoardTie()
    {
        String[][] s = {{"X", "O", "X"}, {"X", "O", "O"}, {"O", "X", "X"}};
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                b.setMove(r, c, s[r][c]);
        assertFalse(b.isWin("X"));
        assertFalse(b.isWin("O"));
        assertTrue(b.isTie());
        assertTrue(b.isFull());
    }

    @Test
    public void openLineIsNotATie()
    {
        b.setMove(0, 0, "X");
        b.setMove(1, 1, "O");
        assertFalse(b.isTie());
    }

    @Test
    public void clearEmptiesBoard()
    {
        b.setMove(2, 2, "O");
        b.clear();
        assertTrue(b.isValidMove(2, 2));
    }
}
