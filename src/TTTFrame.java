import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * TTTFrame is the Swing window for Tic Tac Toe. It looks and acts the same as
 * the Ass 01 version, but it no longer holds the game logic: the tile
 * listener just asks the TTTGame to play the move and then shows the result.
 */
public class TTTFrame extends JFrame
{
    private final TTTGame game = new TTTGame();
    private final TicTacToeTile[][] tiles = new TicTacToeTile[TTTBoard.ROWS][TTTBoard.COLS];
    private final JLabel statusLbl = new JLabel("", SwingConstants.CENTER);

    public TTTFrame()
    {
        super("Tic Tac Toe");
        JPanel mainPnl = new JPanel(new BorderLayout(10, 10));
        mainPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statusLbl.setFont(new Font("SansSerif", Font.BOLD, 22));
        mainPnl.add(statusLbl, BorderLayout.NORTH);
        mainPnl.add(createBoardPanel(), BorderLayout.CENTER);
        mainPnl.add(createControlPanel(), BorderLayout.SOUTH);

        add(mainPnl);
        setSize(520, 620);
        setLocationRelativeTo(null);
        startNewGame();
    }

    private JPanel createBoardPanel()
    {
        JPanel boardPnl = new JPanel(new GridLayout(TTTBoard.ROWS, TTTBoard.COLS, 4, 4));
        boardPnl.setBackground(Color.DARK_GRAY);
        boardPnl.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
        Font tileFont = new Font("SansSerif", Font.BOLD, 72);
        for (int r = 0; r < TTTBoard.ROWS; r++)
        {
            for (int c = 0; c < TTTBoard.COLS; c++)
            {
                TicTacToeTile tile = new TicTacToeTile(r, c);
                tile.setFont(tileFont);
                tile.setFocusPainted(false);
                tile.addActionListener(ae -> tileClicked((TicTacToeTile) ae.getSource()));
                tiles[r][c] = tile;
                boardPnl.add(tile);
            }
        }
        return boardPnl;
    }

    private JPanel createControlPanel()
    {
        JPanel controlPnl = new JPanel();
        JButton quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        quitBtn.addActionListener(ae ->
        {
            int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit",
                    JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });
        controlPnl.add(quitBtn);
        return controlPnl;
    }

    /**
     * Asks the game to play the clicked square and shows the result.
     */
    private void tileClicked(TicTacToeTile tile)
    {
        String player = game.getCurrentPlayer();
        MoveResult result = game.play(tile.getRow(), tile.getCol());
        switch (result)
        {
            case ILLEGAL:
                JOptionPane.showMessageDialog(this, "That square is already taken. Try again.", "Illegal Move",
                        JOptionPane.WARNING_MESSAGE);
                return;
            case WIN:
                tile.setText(player);
                askPlayAgain("Player " + player + " wins!");
                return;
            case TIE:
                tile.setText(player);
                askPlayAgain(game.getBoard().isFull() ? "It's a tie!" : "It's a tie! Nobody can win now.");
                return;
            default:
                tile.setText(player);
                updateStatus();
        }
    }

    private void askPlayAgain(String msg)
    {
        statusLbl.setText(msg);
        int answer = JOptionPane.showConfirmDialog(this, msg + "\nDo you want to play again?", "Game Over",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION)
        {
            startNewGame();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Thanks for playing!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    private void startNewGame()
    {
        game.reset();
        for (TicTacToeTile[] row : tiles)
        {
            for (TicTacToeTile t : row)
            {
                t.setText(" ");
            }
        }
        updateStatus();
    }

    private void updateStatus()
    {
        statusLbl.setText("Player " + game.getCurrentPlayer() + "'s turn");
    }
}
