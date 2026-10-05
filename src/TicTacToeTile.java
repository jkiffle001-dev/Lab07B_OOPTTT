import javax.swing.JButton;

/**
 * TicTacToeTile is the JButton subclass from Ass 01. It remembers its row
 * and column so one shared ActionListener can tell which square was clicked.
 */
public class TicTacToeTile extends JButton
{
    private final int row;
    private final int col;

    public TicTacToeTile(int row, int col)
    {
        super();
        this.row = row;
        this.col = col;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }
}
