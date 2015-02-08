import java.util.Random;

public class Minefield
{
    public static final int BLANK = 0;
    public static final int MINE = -1;
    public static final int POWERUP_ADD = -2;
    public static final int POWERUP_REMOVE = -3;
    public static final int POWERUP_SCRAMBLE = -4;
    public static final int POWERUP_REVEAL = -5;


    private int[][] field;
    private int[][] initialField;
    private boolean[][] exposed;
    private int mines;
    private int exposedCount;

    public Minefield()
    {
        this(10, 10, 15);
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
        int numPowerups = (int)(width * height * 0.15);
        for (int i = 0 ; i < numPowerups ; i++)
        {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if (this.field[x][y] == 0)
            {
                int num = rand.nextInt(4);
                switch(num)
                {
                    case 3: this.field[x][y] = Minefield.POWERUP_REVEAL;
                            break;
                    case 2: this.field[x][y] = Minefield.POWERUP_ADD;
                            break;
                    case 1: this.field[x][y] = Minefield.POWERUP_REMOVE;
                            break;
                    case 0: this.field[x][y] = Minefield.POWERUP_SCRAMBLE;
                            break;
                }
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
            for (int j = 0 ; j < this.getHeight() ; j++)
            {
                this.exposed[i][j] = false;
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

    public int getHiddenOpenSpaces()
    {
        int count = 0;
        for (int x = 0 ; x < getWidth() ; x++)
        {
            for (int y = 0 ; y < getHeight() ; y++)
            {
                if (!exposed[x][y] && field[x][y] >= 0)
                {
                    count++;
                }
            }
        }
        return count;
    }

    public void adjustMines(int adjust)
    {
        this.mines += adjust;
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

    public void floodExpose(int x, int y)
    {
        if (!exposed[x][y])
        {
            setExposed(x, y);
            if (field[x][y] == 0)
            {
                for (int i = -1 ; i <= 1 ; i++)
                {
                    for (int j = -1; j <= 1 ; j++)
                    {
                        int newX = x+i;
                        int newY = y+j;
                        if (isValid(newX, newY) && field[newX][newY] >= 0)
                        {
                            floodExpose(newX, newY);
                        }
                    }
                }
            }
        }
    }

    private boolean isValid(int x, int y)
    {
        return x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight();
    }

    private void refreshBoard()
    {
        int width = this.getWidth();
        int height = this.getHeight();

        for (int i = 0 ; i < width ; i++)
        {
            for (int j = 0 ; j < height ; j++)
            {
                if (field[i][j] >= 0)
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
