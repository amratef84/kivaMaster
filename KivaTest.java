import edu.duke.FileResource;
import edu.duke.Point;

/**
 * Write a description of class KivaTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KivaTest
{

    public KivaTest()
    {
        // initialise instance variables
    }
    FileResource fileResource = new FileResource("sample_floor_map2.txt");
    String inputMap = fileResource.asString();
    FloorMap defaultMap = new FloorMap(inputMap);
    public void testSingleArgumentConstructor() {
    
            Kiva kiva = new Kiva(defaultMap);

            Point initialLocation = kiva.getCurrentLocation();
            Point expectedLocation = new Point(2, 4);
            if (sameLocation(initialLocation, expectedLocation)) 
            {
                System.out.println("testSingleArgumentConstructor SUCCESS");
            }
            else
            {
                System.out.println(String.format("testSingleArgumentConstructor FAIL: %s != (2,4)!", initialLocation));
            }
    }
    public void testTowArgumentConstructor(Point point) {
    
            Kiva kiva = new Kiva(defaultMap);

            Point initialLocation = kiva.getCurrentLocation();
            Point expectedLocation = point;
            if (sameLocation(initialLocation, expectedLocation)) 
            {
                System.out.println("testTowArgumentConstructor SUCCESS");
            }
            else
            {
                System.out.println(String.format("testTowArgumentConstructor FAIL: %s != %z !", initialLocation,expectedLocation));
            }
    }
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }
}
