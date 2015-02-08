import java.awt.Color;
import java.util.HashMap;

public class ColorMap
{
    private static HashMap<Integer, Color> map;

    static
    {
        map = new HashMap<Integer, Color>();
        map.put(1, Color.BLUE);
        map.put(2, Color.GREEN);
        map.put(3, Color.RED);
        map.put(4, Color.ORANGE);
        map.put(5, Color.MAGENTA);
        map.put(6, Color.CYAN);
        map.put(7, Color.BLACK);
        map.put(8, Color.YELLOW);
    }

    public static Color getColor(int num)
    {
        return map.get(num);
    }
}
