import edu.duke.FileResource;
import edu.duke.Point;
/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 *
 * This is starter code that may or may not work. You will need to update the code to
 * complete the project.
 */
public class RemoteControl {
    KeyboardResource keyboardResource;
    FloorMap defaultMap;
    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * [Here's the method you'll execute from within BlueJ. It may or may not run successfully
     * as-is, but you'll definitely need to add more to complete the project.]
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource("sample_floor_map2.txt");
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);

        var intitialKinaLocation = floorMap.getInitialKivaLocation();
        System.out.println("Current Kiva Robot location: " + intitialKinaLocation );//+ intitialKinaLocation.getX()+"  " + intitialKinaLocation.getY());

        Kiva kiva = new Kiva(floorMap);
      //  var face = kiva.getDirectionFacinginit();
      //  System.out.println("Facing: "  +  face ); //+ face.getDelta()
    // var PodLocation = floorMap.getPodLocation();
    // var DropZoneLocation = floorMap.getDropZoneLocation();
    // var allOpjectLocation = floorMap.getObjectAtLocation(new Point(1,1));

    // System.out.println("allOpjectLocation : "  + allOpjectLocation);
    // System.out.println("PodLocation : "  + PodLocation);
    // System.out.println("DropZoneLocation : "  + DropZoneLocation);
        //FacingDirection
        
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        var charDir =  directions.toUpperCase().toCharArray();
       
        //kiva.getDirectionFacing(charDir[0]);
        
        for(char item : charDir)
        {    
            System.out.println("item: "  +  item + convertToKivaCommands(item));
            if(item=='F' || item=='R' || item=='L' || item=='D' || item=='T' )
            { 
                 kiva.move(convertToKivaCommands(item));    
            }
        }
        if(kiva.isSuccessfullyDropped())
        {

             if(charDir[charDir.length-1]!='D')
                {
                   System.out.println("I'm sorry. The Kiva Robot did not pick up the pod and then drop it off in the right place. " ); 
                }
                else
                {
                   System.out.println("Successfully picked up the pod and dropped it off. Thank you! " ); 
                }
        }
        else
        {
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod and then drop it off in the right place. " ); 
        } 
        System.out.println("MotorLifetime = " + kiva.getMotorLifetime() + "");
        //testMoveOutOfBounds(floorMap);
         //KivaMoveTest test3 =new KivaMoveTest();
          //test3.testForwardFromUp();

        // System.out.println("Facing: "  +  face + face.getDelta());        
        // System.out.println("sumpoint: "  +  Sum2Point(intitialKinaLocation ,face.getDelta()));

        
        // KivaTest test2 =new KivaTest();
        // test2.testSingleArgumentConstructor();
        
                
        // var maxrow = floorMap.getMaxRowNum();
        // var maxcol = floorMap.getMaxColNum();
        // var minrow = floorMap.getMinRowNum();
        // var mincol = floorMap.getMinColNum();
        
        // System.out.println("maxrow : "  + maxrow);
        // System.out.println("maxcol : "  + maxcol);
        // System.out.println("minrow : "  + minrow);
        // System.out.println("mincol : "  + mincol);        
        
                
 
        // var intitialKinaLocation2 = floorMap.getInitialKivaLocation();
        // var PodLocation = floorMap.getPodLocation();
        // var DropZoneLocation = floorMap.getDropZoneLocation();
        // var allOpjectLocation = floorMap.getObjectAtLocation(new Point(1,1));

        // System.out.println("intitialKinaLocation : "  + intitialKinaLocation2);
        // System.out.println("allOpjectLocation : "  + allOpjectLocation);
        // System.out.println("PodLocation : "  + PodLocation);
        // System.out.println("DropZoneLocation : "  + DropZoneLocation);
        
        
        
        //KivaCommandTester
        // KivaCommandTester test =new KivaCommandTester();
        // test.testForward();
        // test.testTurnLeft();
        // test.testTurnRight();
        // test.testTake();
        // test.testDrop();
        
       
    }
    // public Point Sum2Point(Point a,Point b)
    // {
      // var x =  a.getX() + b.getX() ;
      // var y =  a.getY() + b.getY() ;
      // return new Point(x,y);
    // }
    public void testMoveOutOfBounds(FloorMap defaultMap) {

        Kiva kiva = new Kiva(defaultMap);
        kiva.move(KivaCommand.FORWARD);
        // kiva.move(KivaCommand.TURN_LEFT);
        // kiva.move(KivaCommand.FORWARD);
        // kiva.move(KivaCommand.FORWARD);
        // System.out.println("testMoveOutOfBounds: (expect an IllegalMoveException)");
        // kiva.move(KivaCommand.FORWARD);

        // This only runs if no exception was thrown
        System.out.println("testMoveOutOfBounds FAIL!");
        System.out.println("Moved outside the FloorMap!");
    }  
     public KivaCommand convertToKivaCommands(char _ch) {
        switch (_ch) {
            case 'F':
                return KivaCommand.FORWARD;
            case 'R':
                return KivaCommand.TURN_RIGHT;
            case 'L':
                return KivaCommand.TURN_LEFT;
            case 'T':
                return KivaCommand.TAKE;
            case 'D':
                return KivaCommand.DROP;
            default:
                System.out.println("Must is char from (F,R,L,T,D) and this char is not correct :" + _ch);                
            }
            return null;
        }
    } 

