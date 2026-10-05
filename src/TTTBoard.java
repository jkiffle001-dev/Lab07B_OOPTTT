/**
 * TTTBoard is the 3 x 3 Tic Tac Toe board. The static board methods from
 * the console / Ass 01 versions now belong to this object: clearing the
 * board, checking for a legal move, recording a move, and checking for a
 * win or a tie. It has no GUI code so it can be unit tested.
 */
public class TTTBoard
{
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static final String EMPTY = " ";

    private final String[][] board = new String[ROWS][COLS];

    public TTTBoard()
    {
        clear();
    }

    /** Sets every square back to empty. */
    public void clear()
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                board[r][c] = EMPTY;
            }
        }
    }

    /**
     * @return true if the position is on the board and the square is empty
     */
    public boolean isValidMove(int row, int col)
    {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col].equals(EMPTY);
    }

    /**
     * Records a move.
     *
     * @throws IllegalArgumentException if the square is not a valid move
     */
    public void setMove(int row, int col, String player)
    {
        if (!isValidMove(row, col))
        {
            throw new IllegalArgumentException("Square " + row + "," + col + " is not available");
        }
        board[row][col] = player;
    }

    public String getSquare(int row, int col)
    {
        return board[row][col];
    }

    /** @return true if the player has three in a row, column or diagonal */
    public boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private boolean isRowWin(String p)
    {
        for (int r = 0; r < ROWS; r++)
        {
            if (board[r][0].equals(p) && board[r][1].equals(p) && board[r][2].equals(p))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin(String p)
    {
        for (int c = 0; c < COLS; c++)
        {
            if (board[0][c].equals(p) && board[1][c].equals(p) && board[2][c].equals(p))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String p)
    {
        return (board[0][0].equals(p) && board[1][1].equals(p) && board[2][2].equals(p))
                || (board[0][2].equals(p) && board[1][1].equals(p) && board[2][0].equals(p));
    }

    /**
     * A tie happens when every one of the 8 win vectors holds both an X and
     * an O, so nobody can win any more. Catches full-board and early ties.
     */
    public boolean isTie()
    {
        for (int i = 0; i < 3; i++)
        {
            if (!isBlocked(board[i][0], board[i][1], board[i][2]) || !isBlocked(board[0][i], board[1][i], board[2][i]))
            {
                return false;
            }
        }
        return isBlocked(board[0][0], board[1][1], board[2][2]) && isBlocked(board[0][2], board[1][1], board[2][0]);
    }

    private boolean isBlocked(String a, String b, String c)
    {
        boolean hasX = a.equals("X") || b.equals("X") || c.equals("X");
        boolean hasO = a.equals("O") || b.equals("O") || c.equals("O");
        return hasX && hasO;
    }

    /** @return true if no squares are empty */
    public boolean isFull()
    {
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                if (board[r][c].equals(EMPTY))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
