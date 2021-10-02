import edu.duke.Point;
import edu.duke.FileResource;
/**
 * Write a description of class KivaMoveTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KivaMoveTest
{
    // instance variables - replace the example below with your own
 // Define the FloorMap we'll use for all the tests
     // String defaultLayout = ""
                             // + "-------------\n"
                             // + " P *\n"
                             // + " ** *\n"
                             // + " ** *\n"
                             // + " K D *\n"
                             // + " * * * * * **\n"
                             // + "-------------\n";
                             
     FileResource fileResource = new FileResource("sample_floor_map3.txt");
     String defaultLayout = fileResource.asString();
     FloorMap defaultMap = new FloorMap(defaultLayout);
     
     public void testForwardFromUp() {
         // GIVEN
         // A Kiva built with the default map we defined earlier
         Kiva kiva = new Kiva(defaultMap);
         // WHEN
         // We move one space forward
        kiva.move(KivaCommand.FORWARD);
        
        /// THEN
        // The Kiva has moved one space  
         verifyKivaState("testForwardFromUp",kiva, new Point(2, 3), FacingDirection.UP, false, false);
     }
    
     // For you: create all the other tests and call verifyKivaState() for each
     private boolean sameLocation(Point a, Point b) 
     {
         return a.getX() == b.getX() && a.getY() == b.getY();
     }
     private void verifyKivaState(
         String testName,
         Kiva actual,
         Point expectLocation,
         FacingDirection expectDirection,
         boolean expectCarry,
         boolean expectDropped) 
         {
             
         Point actualLocation = actual.getCurrentLocation();
         if (sameLocation(actualLocation, expectLocation))
         {
             System.out.println(
             String.format("%s: current location SUCCESS", testName));
         }
         else 
         {
             System.out.println(
             String.format("%s: current location FAIL!", testName));
             System.out.println(
             String.format("Expected %s, got %s",
             expectLocation, actualLocation));
         }
         
         FacingDirection actualDirection = actual.getDirectionFacing();         
         if (actualDirection == expectDirection) 
         {
             System.out.println(
             String.format("%s: facing direction SUCCESS", testName));
         }
         else
         {
             System.out.println(
             String.format("%s: facing direction FAIL!", testName));
             System.out.println(
             String.format("Expected %s, got %s",
             expectDirection, actualDirection));
         }
         
         boolean actualCarry = actual.isCarryingPod();         
         if (actualCarry == expectCarry) 
         {
             System.out.println(
             String.format("%s: carrying pod SUCCESS", testName));
         }
         else {
             System.out.println(
             String.format("%s: carrying pod FAIL!", testName));
             System.out.println(
             String.format("Expected %s, got %s",
             expectCarry, actualCarry));
         }
         
         boolean actualDropped = actual.isSuccessfullyDropped();
         
         if(actualDropped == expectDropped) 
         {
             System.out.println(
             String.format("%s: successfully dropped SUCCESS", testName));
         }
         else 
         {
             System.out.println(
             String.format("%s: successfully dropped FAIL!", testName));
             System.out.println(
             String.format("Expected %s, got %s",
             expectDropped, actualDropped));
             }
         }

}
