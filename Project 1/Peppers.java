public class Peppers extends BowlTopping{
    
    public Peppers(BowlProtein next){
        
        super(next);
    }
    
    public Peppers(BowlTopping next){
        super(next);
    }
    
    public String toString()
    {
        return super.toString()+"Peppers\r\n";
    }
}
