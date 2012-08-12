import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mine extends JButton
{
    private static final String MINE = "!!";
    public String value;

    public Mine(int value)
    {
        super();
        
        this.setValue(value);

        this.initComponent();
    }

    public boolean isMine()
    {
        return this.value.equals(Mine.MINE);
    }

    public void setValue(int value)
    {
        switch(value)
        {
            case Minefield.MINE : 
                this.value = Mine.MINE;
                break;

            case 0 : 
                this.value = ""; 
                break;

            default: 
                this.value = Integer.toString(value);
        }
    }

    public void showValue()
    {
        this.setText(this.value);
    }

    public void hideValue()
    {
        this.setText("");
        this.setBackground(Color.BLACK);
    }

    private void initComponent()
    {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }
}
