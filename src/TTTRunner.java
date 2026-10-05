import javax.swing.JFrame;

/** Starts the OOP Tic Tac Toe game. */
public class TTTRunner
{
    public static void main(String[] args)
    {
        TTTFrame frame = new TTTFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
