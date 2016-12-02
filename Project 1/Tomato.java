public class Tomato extends BowlTopping{
    
    public Tomato(BowlProtein next){
        
        super(next);
    }
    
    public Tomato(BowlTopping next){
        super(next);
    }
    
    public String toString()
    {
        return super.toString()+"Tomato\r\n";
    }
}
