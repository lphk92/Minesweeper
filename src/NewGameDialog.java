import java.awt.Dialog;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class NewGameDialog extends JDialog
{
    private static JTextField x;
    private static JTextField y;
    private static JTextField mines;
    private static JComponent[] inputs;

    static
    {
        NewGameDialog.x = new JTextField();
        NewGameDialog.y = new JTextField();
        NewGameDialog.mines = new JTextField();

        inputs = new JComponent[] {
            new JLabel("Width"),
            NewGameDialog.x,
            new JLabel("Height"),
            NewGameDialog.y,
            new JLabel("Mines"),
            NewGameDialog.mines
        };
    }

    public static Minefield newGame(int initialX, int initialY, int initialMines)
    {
        NewGameDialog.x.setText(Integer.toString(initialX));
        NewGameDialog.y.setText(Integer.toString(initialY));
        NewGameDialog.mines.setText(Integer.toString(initialMines));

        int result = JOptionPane.showConfirmDialog(null, inputs, "New Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION)
        {
            int width = Integer.parseInt(NewGameDialog.x.getText());
            int height = Integer.parseInt(NewGameDialog.y.getText());
            int mines = Integer.parseInt(NewGameDialog.mines.getText());
            return new Minefield(width, height, mines);
        }
        else
        {
            return null;
        }
    }
}
