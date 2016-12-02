public class Edamame extends BowlTopping{
    
    public Edamame(BowlProtein next){
        
        super(next);
    }
    
    public Edamame(BowlTopping next){
        super(next);
    }
    
    public String toString()
    {
        return super.toString()+"Edamame\r\n";
    }
}