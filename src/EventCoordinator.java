import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class EventCoordinator
{
    private Minefield minefield;
    private MinefieldPanel minefieldPanel;
    private MinefieldMenubar menubar;

    public EventCoordinator(Minefield minefield, MinefieldPanel minefieldPanel, MinefieldMenubar menubar)
    {
        this.minefield = minefield;
        this.minefieldPanel = minefieldPanel;
        this.menubar = menubar;

        this.menubar.addNewGameListener(new NewGameListener());
        this.menubar.addResetListener(new ResetListener());
    }

    private class NewGameListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("New Game");

            int width = EventCoordinator.this.minefield.getWidth();
            int height = EventCoordinator.this.minefield.getHeight();
            int mines = EventCoordinator.this.minefield.getMines();

            Minefield newMinefield = NewGameDialog.newGame(width, height, mines);
            if (newMinefield != null)
            {
                EventCoordinator.this.minefield = newMinefield;
                EventCoordinator.this.minefieldPanel.initialize(EventCoordinator.this.minefield);
            }
        }
    }

    private class ResetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("Reset");
            EventCoordinator.this.minefieldPanel.reinitialize();
        }
    }
/*
    private class NewGameListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {

        }
    }
*/
}
