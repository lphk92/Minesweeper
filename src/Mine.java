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
    private static final String ADD = "+";
    private static final String REMOVE = "-";
    private static final String SCRAMBLE = "!$?";

    private String value;
    private boolean hidden;
    private boolean flagged;
    private int fieldX;
    private int fieldY;

    public Mine(int x, int y, int value)
    {
        super();
        
        this.flagged = false;
        this.hidden = true;
        this.fieldX = x;
        this.fieldY = y;

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

            case Minefield.POWERUP_ADD:
                this.value = Mine.ADD;
                break;

            case Minefield.POWERUP_REMOVE:
                this.value = Mine.REMOVE;
                break;

            case Minefield.POWERUP_SCRAMBLE:
                this.value = Mine.SCRAMBLE;
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

    public boolean isHidden()
    {
        return this.hidden;
    }

    public int getFieldX()
    {
        return this.fieldX;
    }

    public int getFieldY()
    {
        return this.fieldY;
    }

    public void showValue()
    {
        this.setText(this.value);
        if (this.value.equals(Mine.MINE))
        {
            this.setBackground(Color.RED);
        }
        else
        {
            this.setBackground(Color.GRAY);
        }
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
