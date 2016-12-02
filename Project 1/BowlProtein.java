/**This class extends BowlItem, also known as being it's child. BowlProtein also has 3 classes that
extend's itself. (Also Known as being the parent class) (BlackBeans, Steak, and Grilled Chicken).*/
public abstract class BowlProtein extends BowlItem{
 
    public BowlProtein(BowlBase next){
        super(next);
    }
    
    public BowlProtein(BowlProtein next){
        super(next);
    }
    
    public double cost(){
        return(super.cost() + 1.47);
    }
    
    public String toString(){
        return super.toString() + "Protein: ";
    }
    
    
}