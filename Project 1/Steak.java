//This class extends BowlProtein also known as a child
public class Steak extends BowlProtein{
    public Steak(BowlBase next){
        super(next);
    }
    
    public Steak(BowlProtein next){
        super(next);
    }
    //To String method to send "Steak"
    public String toString(){
        return super.toString()+"Steak\r\n";
    }
}
