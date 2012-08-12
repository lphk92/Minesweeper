import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Mine extends JButton implements MouseListener
{
    private static final String MINE = "!!";
    private static final String FLAG = "FF";
    private static final String ALONE = "";
    private String value;
    private boolean hidden;
    private boolean flagged;

    public Mine(int value)
    {
        super();
        
        this.flagged = false;
        this.hidden = true;

        this.setValue(value);
        this.addMouseListener(this);

        this.initComponent();
    }

    public void setValue(int value)
    {
        switch(value)
        {
            case Minefield.MINE : 
                this.value = Mine.MINE;
                break;

            case 0 : 
                this.value = Mine.ALONE; 
                break;

            default: 
                this.value = Integer.toString(value);
        }
    }

    public boolean isAlone()
    {
        return this.value.equals(Mine.ALONE);
    }

    public boolean isMine()
    {
        return this.value.equals(Mine.MINE);
    }

    public boolean isFlagged()
    {
        return this.flagged;
    }

    public void showValue()
    {
        this.setText(this.value);
        this.hidden = false;
    }

    public void hideValue()
    {
        this.setText("");
        this.setBackground(Color.BLACK);
        this.hidden = true;
    }

    private void initComponent()
    {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }

    @Override
    public void mouseClicked(MouseEvent me)
    { 
        if (this.hidden && me.getButton() == MouseEvent.BUTTON3)
        {
            System.out.println("Mine Flagged");
            this.flagged = !this.flagged;

            if (flagged)
                this.setText(Mine.FLAG);
            else
                this.setText("");
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) { }

    @Override
    public void mouseExited(MouseEvent me) { }
    
    @Override
    public void mousePressed(MouseEvent me) { }

    @Override
    public void mouseReleased(MouseEvent me) { }
}
