import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MinefieldMenubar extends JMenuBar
{
    JMenu gameMenu;
    JMenuItem newGame;
    JMenuItem reset;

    public MinefieldMenubar()
    {
        super();

        this.initComponent();
    }

    private void initComponent()
    {
        this.gameMenu = new JMenu("Game");
        this.newGame = new JMenuItem("New Game");
        this.reset = new JMenuItem("Reset");
        this.gameMenu.add(this.newGame);
        this.gameMenu.add(this.reset);

        this.add(gameMenu);
    }

    public void addNewGameListener(ActionListener al)
    {
        this.newGame.addActionListener(al);
    }

    public void addResetListener(ActionListener al)
    {
        this.reset.addActionListener(al);
    }
}
