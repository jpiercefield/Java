public class Avocado extends BowlTopping{
    
    public Avocado(BowlProtein next){
        
        super(next);
    }
    
    public Avocado(BowlTopping next){
        super(next);
    }
    
    public String toString()
    {
        return super.toString()+"Avocado\r\n";
    }
}
