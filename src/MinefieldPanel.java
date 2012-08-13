import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MinefieldPanel extends JPanel implements ActionListener
{
    private Minefield minefield;
    private Mine[][] field;
    private int clickCount;

    public MinefieldPanel(Minefield minefield)
    {
        this.minefield = minefield;
        this.clickCount = 0;
        this.initComponent();
    }

    public void reinitialize()
    {
        this.initialize(this.minefield);
    }

    public void initialize(Minefield minefield)
    {
        this.clickCount = 0;
        this.minefield = minefield;
        this.initComponent();
    }

    private void initComponent()
    {
        this.removeAll();
        this.updateUI();

        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridLayout(this.minefield.getWidth(), this.minefield.getHeight()));

        this.field = new Mine[this.minefield.getWidth()][this.minefield.getHeight()];

        for (int i = 0 ; i < this.minefield.getWidth() ; i++)
        {
            for (int j = 0 ; j < this.minefield.getHeight() ; j++)
            {
                field[i][j] = new Mine(this.minefield.getValue(i, j));
                field[i][j].addActionListener(this);
                this.add(field[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Mine source = (Mine)ae.getSource();

        if (!source.isFlagged())
        {
            if (source.isHidden())
            {
                source.showValue();
                this.clickCount++;
                System.out.println("clickCount = " + this.clickCount);
            }

            if (source.isMine())
            {
                source.setBackground(Color.RED);
                for (int i = 0 ; i < field.length ; i++)
                {
                    for (int j = 0 ; j < field[0].length ; j++)
                    {
                        field[i][j].setEnabled(false);
                        if (field[i][j].isMine())
                        {
                            field[i][j].setBackground(Color.RED);
                            field[i][j].showValue();
                        }
                    }
                }
            }
            else if (this.clickCount == (this.minefield.getWidth() * this.minefield.getHeight() - this.minefield.getMines()))
            {
                for (int i = 0 ; i < field.length ; i++)
                {
                    for (int j = 0 ; j < field[0].length ; j++)
                    {
                        field[i][j].setEnabled(false);

                        if (!field[i][j].isMine())
                            field[i][j].setBackground(Color.GREEN);
                    }
                }
            }
            else
            {
                source.setBackground(Color.GRAY);
                if (source.isAlone())
                {
                    //TODO: Clear out neighbors when an empty space is clicked
                    System.out.println("Empty space clicked");                                
                }
            }

            this.revalidate();
            this.repaint();
        }
    }
}

