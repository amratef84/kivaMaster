import java.util.*;
import javax.swing.Timer;
import edu.duke.Point;

/**
 * Write a description of class Kiva here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Kiva
{
    // instance variables - replace the example below with your own
    Point currentLocation;
    FacingDirection directionFacing;
    FloorMap map;
    boolean carryingPod;
    boolean successfullyDropped;
    long motorLifetime ;
    
    /**
     * Constructor for objects of class Kiva
     */
    public Kiva(FloorMap map)
    {
        // initialise instance variables
       this.map=map;
       this.currentLocation = map.getInitialKivaLocation();
    }

    public Kiva(FloorMap map,Point currentLocation)
    {
        // initialise instance variables
       this.map=map;
       this.currentLocation = currentLocation;
    }
    
    public void move(KivaCommand command)
    {
        // put your code here  
       var ch = command.getDirectionKey();
       switch(ch) {
          case 'F':
           testFaceing(currentLocation,ch);
            break;
          case 'L':
             testFaceing(currentLocation,ch);
            break;
          case 'R':
            testFaceing(currentLocation,ch);
            break;
          case 'T':
           {
              if(sameLocation(currentLocation , map.getPodLocation()))
                 {
                    carryingPod=true;
                 }
              else 
                {
                    carryingPod=false;
                    throw new NoPodException("Can't take nonexistent pod from location " +
                    currentLocation + "!" );
                }
              break;
           }
          case 'D':
           { 
             if(carryingPod && sameLocation(currentLocation , map.getDropZoneLocation()))
                 {
                    successfullyDropped=true;
                 } 
              else 
                {
                    successfullyDropped=false;
                    throw new IllegalDropZoneException("Can't just drop pods willy-nilly at" +
                    currentLocation + "!");
                }
            break;
           }
          default:
            System.out.println("Error");
        }
    } 
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }
    public boolean isCarryingPod()
    {
        // put your code here               
        return carryingPod;
    }
    
    public boolean isSuccessfullyDropped()
    {
        // put your code here           
        return successfullyDropped;
    }
   public Point getCurrentLocation ()
   {
       return currentLocation;
   }

   public FacingDirection getDirectionFacing(char ch)
   {  
      switch (ch)
        {
          case 'F':
                directionFacing = FacingDirection.UP;
                break;
          case 'R':
                directionFacing = FacingDirection.RIGHT;
                break;
          case 'L':
                directionFacing = FacingDirection.LEFT;
                break;
          default:
            System.out.println("Error : FacingDirection"  + ch );
        }
       return directionFacing;
   }
    public FacingDirection getDirectionFacing()
   {  
       return directionFacing;
   }
    private FacingDirection deferentLocation(Point a, Point b) 
    {
         if(a.getY() > b.getY())
         {
            //FacingDirection(FacingDirection.UP);         
            directionFacing = FacingDirection.UP;
         }
        else if(a.getY() == b.getY())
        {
                if(a.getX() > b.getX())
                 directionFacing = FacingDirection.LEFT;
                 else
                 directionFacing = FacingDirection.RIGHT;
        }
        else
        {
            directionFacing = FacingDirection.DOWN;
        }
        return directionFacing;
    }
    //This id method 
    public Point Sum2Point(Point a,Point b)
    {
      var x =  a.getX() + b.getX();
      var y =  a.getY() + b.getY();
      var point =new Point(x,y);
      if(CheckOutRange(point))
      {  
         if(CheckCellOBATACLE(point))
         {
            currentLocation = point;
            return point;
         }
         else
         {
            throw new IllegalMoveException(" Can't move onto an obstacle at!"+point);
         }
      }
      else
      { 
          throw new IllegalMoveException(" Can't move onto an obstacle at!"+point);
         // return pi;
      }
    }
    public boolean CheckOutRange(Point a)
    {
        if(a.getX() > map.getMinColNum() && a.getX() < map.getMaxColNum() && a.getY()> map.getMinRowNum() && a.getY() < map.getMaxRowNum())
            return true;
            else  return false;
    }
    public boolean CheckCellOBATACLE(Point a)
    {
       if(map.getObjectAtLocation(a)!= FloorMapObject.OBSTACLE)
            return true;
       else return false;
    }
    public void testFaceing(Point _currentLocation,char ch)
    {
       FacingDirection face;
       Point _po = null;
       incrementMotorLifetime();
       if(directionFacing==null)
       {
        if(ch=='F')
            {
               _po=new Point(0,-1);
               directionFacing = FacingDirection.UP;
            }
            else if(ch=='R')
            {
                 // _po=new Point(1,0);
                 directionFacing=FacingDirection.RIGHT;
            }
            else
            {
                // _po=new Point(-1,0);
                 directionFacing=FacingDirection.LEFT;                
            }
       }
       else
       {
         face = getDirectionFacing();
        switch (face)
        {
          case UP:
            if(ch=='F')
            {
               _po=new Point(0,-1);
               directionFacing = FacingDirection.UP;
            }
            else if(ch=='R')
            {
                 // _po=new Point(1,0);
                 directionFacing=FacingDirection.RIGHT;
            }
            else
            {
                // _po=new Point(-1,0);
                 directionFacing=FacingDirection.LEFT;                
            }
            break;
          case DOWN:
            if(ch=='F')
            {
               _po=new Point(0,1);
               directionFacing = FacingDirection.DOWN;
            }
            else if(ch=='R')
            {
                // _po=new Point(-1,0);
                 directionFacing=FacingDirection.LEFT;
            }
            else
            {
                // _po=new Point(1,0);
                 directionFacing=FacingDirection.RIGHT;                
            }
            break;
          case RIGHT:
            if(ch=='F')
            {
               _po=new Point(1,0);
               directionFacing = FacingDirection.RIGHT; 
            }
            else if(ch=='R')
            {
                // _po=new Point(0,1);
                 directionFacing=FacingDirection.DOWN;
            }
            else
            {
                // _po=new Point(0,-1);
                 directionFacing=FacingDirection.UP;                
            }
            break;
          case LEFT:
           if(ch=='F')
            {
               _po=new Point(-1,0);
               directionFacing = FacingDirection.LEFT;             
            }
             else if(ch=='R')
            {
                 // _po=new Point(0,-1);
                 directionFacing=FacingDirection.UP;
            }
            else
            {
                // _po=new Point(0,1);
                 directionFacing=FacingDirection.DOWN;                
            }
            break;      
          default:
            System.out.println("Error : testFaceing");
        }
       }
        if(_po!=null)
          System.out.println( directionFacing + "   "+Sum2Point(_currentLocation,_po));
       else
          System.out.println( directionFacing );

    }
    public long getMotorLifetime()
    {
        return motorLifetime;
    }
   public void incrementMotorLifetime()
   {
     motorLifetime +=1000;
   }
    
}
