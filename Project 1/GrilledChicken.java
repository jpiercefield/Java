//This class extends BowlProtein also known as a child
public class GrilledChicken extends BowlProtein{
    
    public GrilledChicken(BowlBase next){
        super(next);
    }
    
    public GrilledChicken(BowlProtein next){
        super(next);
    }
    //To string method to send Grilled Chicken
    public String toString(){
        return super.toString()+"Grilled Chicken\r\n";
    }
}
