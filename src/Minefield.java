import java.util.Random;

public class Minefield
{
    public static final int MINE = -1;

    private int[][] field;
    private int mines;

    public Minefield()
    {
        this(5, 5, 5);
    }

    public Minefield(int width, int height, int mines)
    {
        this.field = new int[width][height];
        this.mines = mines;

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

        // Initialize the rest of the minefield with approriate numbers
        for (int i = 0 ; i < width ; i++)
        {
            for (int j = 0 ; j < height ; j++)
            {
                if (field[i][j] != Minefield.MINE)
                {
                    int num = 0;

                    num += (i-1 >= 0 && j-1 >= 0) ? (field[i-1][j-1] == Minefield.MINE ? 1 : 0) : 0;
                    num += (i+1 < width && j-1 >= 0) ? (field[i+1][j-1] == Minefield.MINE ? 1 : 0) : 0;
                    num += (i-1 >= 0 && j+1 < height) ? (field[i-1][j+1] == Minefield.MINE ? 1 : 0) : 0;
                    num += (i+1 < width && j+1 < height) ? (field[i+1][j+1] == Minefield.MINE ? 1 : 0) : 0;
                    num += (j-1 >= 0) ? (field[i][j-1] == Minefield.MINE ? 1 : 0) : 0;
                    num += (j+1 < height) ? (field[i][j+1] == Minefield.MINE ? 1 : 0) : 0;
                    num += (i-1 >= 0) ? (field[i-1][j] == Minefield.MINE ? 1 : 0) : 0;
                    num += (i+1 < width) ? (field[i+1][j] == Minefield.MINE ? 1 : 0) : 0;

                    field[i][j] = num;
                }
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
}
