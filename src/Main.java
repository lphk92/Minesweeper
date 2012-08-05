import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Begin");
        
        Minefield minefield = new Minefield();
        MinefieldPanel panel = new MinefieldPanel(minefield);
        MinefieldMenubar menubar = new MinefieldMenubar();

        EventCoordinator coordinator = new EventCoordinator(panel, menubar);

        JFrame frame = new JFrame("Minesweeper");
        frame.setJMenuBar(menubar);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
