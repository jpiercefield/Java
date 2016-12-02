/**BowlBase extending BowlItem - This class contains the Constructor and the Constructor for BowlBase, 
   an abstract double cost method, and a toString method to return the "Base:"*/

public abstract class BowlBase extends BowlItem{
    
    public BowlBase(){
        super(null); //null? -- break point --------
    }
    
    public abstract double cost();
    
    public String toString()
    {
        return "Base: ";
    }
}