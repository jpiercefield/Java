/**BowlItem is basically at the top of the pyramid. This class has BowlBase, BowlPrtein, and BowlTopping
being extended from it. Each of these 3 classes also have 3 other child classes of their own.*/
public abstract class BowlItem{

    //constructor to set the (next bowl item) in the decorator design pattern
    private BowlItem nextBowlItem;
    private double cost;
    
    public BowlItem(BowlItem next)
    {
        if(next instanceof BowlTopping)
        {
            
            if(!((BowlTopping)next).isDuplicate()){
                nextBowlItem = next;
            }else{
                    
            }
        }else{
            nextBowlItem = next;
        }
    }
    
    //returns the cost of the next bowl item
    public double cost(){
        if(nextBowlItem!=null)
        {
            return nextBowlItem.cost();
        }
        return 0;

    }
    
    //returns the String description of the next bowl item
    public String toString(){
        if(nextBowlItem!=null)
        {
            return nextBowlItem.toString();
        }
        
        return ""; //checked
    }
    
    public boolean isDuplicate(BowlTopping topping){
        if(nextBowlItem!=null){
            if(topping.equals(nextBowlItem)){
                return true;
            }else{
                return nextBowlItem.isDuplicate(topping);
            }
        }else{
            return false;
        }
    }

}