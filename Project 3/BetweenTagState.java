/**
 * - XML State <br>
 * - Precondition: Open Tag <br>
 * - It skips the whitespace until a character is received. <br>
 * - Read's in "<" character.<br>
 * - Next State is EndBetween 
 */
public class BetweenTagState implements stateInterface 
{
    private Context passVarXML;
    private String thisChar;

    public boolean greaterThan() 
    {
        thisChar += ">";
        return true;
    }
    
    public BetweenTagState(Context fileVar) 
    {
        this.passVarXML = fileVar; 
        this.thisChar = new String(""); //this =
    }
    
    public boolean forwardSlash() 
    {
        thisChar += "/";
        return true;
    }
    
    public boolean space()
    {
        return true;
    }

    public boolean unlisted(char unknownChar) 
    {
        if(thisChar.length() > 0)
        {
            thisChar += unknownChar;
        }
        else if(!Character.isSpaceChar(unknownChar))
        {
            thisChar += unknownChar;
        }
        return true;
    }
     
    public boolean lessThan() 
    {
        if(thisChar.length() <= 0)
        {
            passVarXML.stateArrange(passVarXML.getState());
        }
        else
        {
            String spaceTabs = "";
            for (int i = 0; i < passVarXML.getTags().size(); i++) 
            {
                spaceTabs += "    ";
            }
            passVarXML.getWritingMaterial().add(spaceTabs + thisChar);
            passVarXML.stateArrange(passVarXML.getEndBetweenTagState()); //--------------
            thisChar = new String(""); 
        }
        return true;
    }
}