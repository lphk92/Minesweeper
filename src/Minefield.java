import java.util.Random;

public class Minefield
{
    public static final int BLANK = 0;
    public static final int MINE = -1;
    public static final int POWERUP_ADD = -2;
    public static final int POWERUP_REMOVE = -3;
    public static final int POWERUP_SCRAMBLE = -4;


    private int[][] field;
    private int[][] initialField;
    private boolean[][] exposed;
    private int mines;
    private int exposedCount;

    public Minefield()
    {
        this(5, 5, 5);
    }

    public Minefield(int width, int height, int mines)
    {
        this.field = new int[width][height];
        this.exposed = new boolean[width][height];
        this.mines = mines;
        this.exposedCount = 0;

        // Plant all of the mines
        Random rand = new Random();
        for (int i = 0 ; i < mines ; i++)
        {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if (this.field[x][y] == 0)
            {
               this.field[x][y] = Minefield.MINE; 
            }
            else
            {
               i--; 
            }
        }

        // Add powerups
        for (int i = 0 ; i < 3 ; i++)
        {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            int num = rand.nextInt(2);
            if (this.field[x][y] == 0)
            {
               this.field[x][y] = num % 2 == 0 ? Minefield.POWERUP_ADD : Minefield.POWERUP_REMOVE;
            }
            else
            {
               i--;
            }
        }

        // Initialize the rest of the minefield with approriate numbers
        refreshBoard();

        // Save initial board for resetting
        initialField = field.clone();
        for (int i = 0 ; i < field.length ; i++)
        {
            initialField[i] = field[i].clone();
        }
    }

    public void reset()
    {
        field = initialField.clone();
        for (int i = 0 ; i < initialField.length ; i++)
        {
            field[i] = initialField[i].clone();
        }
        exposedCount = 0;
        for (int i = 0 ; i < this.getWidth() ; i++)
        {
            for (int j = 0 ; j < this.getWidth() ; j++)
            {
                exposed[i][j] = false;
            }
        }
    }

    public int getValue(int x, int y)
    {
        return field[x][y];
    }

    public int getMines()
    {
        return this.mines;
    }

    public int getWidth()
    {
        return field.length;
    }

    public int getHeight()
    {
        return field[0].length;
    }

    public int getExposedCount()
    {
        return exposedCount;
    }

    public boolean isExposed(int x, int y)
    {
        return exposed[x][y];
    }

    public void setValue(int x, int y, int value)
    {
        field[x][y] = value;
        refreshBoard();
    }

    public void setExposed(int x, int y)
    {
        if (!exposed[x][y])
        {
            exposedCount++;
        }
        exposed[x][y] = true;
    }

    public void printField()
    {
        for (int i = 0 ; i < field.length ; i++)
        {
            for (int j = 0 ; j < field[0].length ; j++)
            {
                System.out.print(field[i][j] + " ");
            }

            System.out.println();
        }
    }

    private void refreshBoard()
    {
        int width = this.getWidth();
        int height = this.getHeight();

        for (int i = 0 ; i < width ; i++)
        {
            for (int j = 0 ; j < height ; j++)
            {
                if (field[i][j] != Minefield.MINE &&
                    field[i][j] != Minefield.POWERUP_ADD &&
                    field[i][j] != Minefield.POWERUP_REMOVE &&
                    field[i][j] != Minefield.POWERUP_SCRAMBLE)
                {
                    int val = 0;

                    val += (i-1 >= 0 && j-1 >= 0) ? (field[i-1][j-1] == Minefield.MINE ? 1 : 0) : 0;
                    val += (i+1 < width && j-1 >= 0) ? (field[i+1][j-1] == Minefield.MINE ? 1 : 0) : 0;
                    val += (i-1 >= 0 && j+1 < height) ? (field[i-1][j+1] == Minefield.MINE ? 1 : 0) : 0;
                    val += (i+1 < width && j+1 < height) ? (field[i+1][j+1] == Minefield.MINE ? 1 : 0) : 0;
                    val += (j-1 >= 0) ? (field[i][j-1] == Minefield.MINE ? 1 : 0) : 0;
                    val += (j+1 < height) ? (field[i][j+1] == Minefield.MINE ? 1 : 0) : 0;
                    val += (i-1 >= 0) ? (field[i-1][j] == Minefield.MINE ? 1 : 0) : 0;
                    val += (i+1 < width) ? (field[i+1][j] == Minefield.MINE ? 1 : 0) : 0;

                    field[i][j] =  val;
                }
            }
        }
    }
}
