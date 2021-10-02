
/**
 * Enumeration class KivaCommand - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum KivaCommand
{
    FORWARD('F'),
    TURN_LEFT('L'),
    TURN_RIGHT('R'),
    TAKE('T'),
    DROP('D');
    
    private char directionKey;
    
    private KivaCommand(char directionKey){
        this.directionKey = directionKey ;
    }
    public char getDirectionKey(){
        return directionKey;
    }
}
