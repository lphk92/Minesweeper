import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventCoordinator
{
    private MinefieldPanel minefield;
    private MinefieldMenubar menubar;

    public EventCoordinator(MinefieldPanel minefield, MinefieldMenubar menubar)
    {
        this.minefield = minefield;
        this.menubar = menubar;

        this.menubar.addNewGameListener(new NewGameListener());
        this.menubar.addResetListener(new ResetListener());
    }

    private class NewGameListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("New Game");
            EventCoordinator.this.minefield.initialize(new Minefield());
        }
    }

    private class ResetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("Reset");
            EventCoordinator.this.minefield.initialize();
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
