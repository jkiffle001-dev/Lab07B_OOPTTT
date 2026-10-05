/**
 * TTTGame runs one game of Tic Tac Toe. It owns the TTTBoard, keeps track
 * of the current player and the move count, and decides the result of each
 * move. The GUI only has to show what the game tells it.
 */
public class TTTGame
{
    private static final int MOVES_FOR_WIN = 5;
    private static final int MOVES_FOR_TIE = 7;

    private final TTTBoard board = new TTTBoard();
    private String currentPlayer;
    private int moveCount;

    public TTTGame()
    {
        reset();
    }

    /** Starts a new game: clears the board and X moves first. */
    public void reset()
    {
        board.clear();
        currentPlayer = "X";
        moveCount = 0;
    }

    public String getCurrentPlayer()
    {
        return currentPlayer;
    }

    public int getMoveCount()
    {
        return moveCount;
    }

    public TTTBoard getBoard()
    {
        return board;
    }

    /**
     * Plays a move for the current player. On CONTINUE the turn passes to
     * the other player; on WIN or TIE the current player stays the same so
     * the GUI can report who won.
     *
     * @return the result of the move
     */
    public MoveResult play(int row, int col)
    {
        if (!board.isValidMove(row, col))
        {
            return MoveResult.ILLEGAL;
        }
        board.setMove(row, col, currentPlayer);
        moveCount++;
        if (moveCount >= MOVES_FOR_WIN && board.isWin(currentPlayer))
        {
            return MoveResult.WIN;
        }
        if (moveCount >= MOVES_FOR_TIE && board.isTie())
        {
            return MoveResult.TIE;
        }
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        return MoveResult.CONTINUE;
    }
}
