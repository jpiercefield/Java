//This class extends BowlProtein also known as a child
public class BlackBeans extends BowlProtein{
    
    public BlackBeans(BowlBase next){
        super(next);
    }
    
    public BlackBeans(BowlProtein next){
        super(next);
    }
    //To String method to send Black Beans
    public String toString(){
        
        return super.toString()+"Black Beans\r\n";
    }
    
}
