import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Random;

public class MinefieldPanel extends JPanel implements ActionListener
{
    private Minefield minefield;
    private Mine[][] field;

    public MinefieldPanel(Minefield minefield)
    {
        this.minefield = minefield;
        this.initComponent();
    }

    public void reinitialize()
    {
        this.initialize(this.minefield);
    }

    public void initialize(Minefield minefield)
    {
        this.minefield = minefield;
        this.minefield.reset();
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
                field[i][j] = new Mine(i, j, this.minefield.getValue(i, j));
                field[i][j].addActionListener(this);
                this.add(field[i][j]);
                if (this.minefield.isExposed(i, j))
                {
                    field[i][j].showValue();
                }
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
                source.setBackground(Color.GRAY);
                this.minefield.setExposed(source.getFieldX(), source.getFieldY());

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
                            else if (this.minefield.getValue(i, j) < 0)
                            {
                                field[i][j].setBackground(Color.BLUE);
                                field[i][j].showValue();
                            }
                        }
                    }
                }
                else if (this.minefield.getExposedCount() == (this.minefield.getWidth() * this.minefield.getHeight() - this.minefield.getMines()))
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
                    int value = this.minefield.getValue(source.getFieldX(), source.getFieldY());
                    if (source.isAlone())
                    {
                        //TODO: Clear out neighbors when an empty space is clicked
                        System.out.println("Empty space clicked");
                    }
                    else if (value == Minefield.POWERUP_ADD)
                    {
                        System.out.println("POWERUP - Add");
                        while (true)
                        {
                            Random rand = new Random();
                            int x = rand.nextInt(this.minefield.getWidth());
                            int y = rand.nextInt(this.minefield.getHeight());
                            if (field[x][y].isHidden() && this.minefield.getValue(x, y) >= 0)
                            {
                                field[x][y].setValue(Minefield.MINE);
                                this.minefield.setValue(x, y, Minefield.MINE);
                                System.out.println("    Mine added at " + x + ", " + y);
                                this.minefield.printField();
                                break;
                            }
                        }
                    }
                    else if (value == Minefield.POWERUP_REMOVE)
                    {
                        System.out.println("POWERUP - Remove");
                    }
                    else if (value == Minefield.POWERUP_SCRAMBLE)
                    {
                        System.out.println("POWERUP - Scramble");
                    }
                }

                this.revalidate();
                this.repaint();
            }
        }
    }
}

