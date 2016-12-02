/**BowlTopping extends the BowlItem class (child of Bowlitem)
Sends cost, next protein, next topping, toString, & check duplicates*/
public abstract class BowlTopping extends BowlItem{
    // equals OBJECT 
    public boolean equals(Object WW)
    {
        //check all toppings.. need more if statements
        return WW.getClass().isInstance(this);
    }
        
    public BowlTopping(BowlProtein next){
        super(next);
    }
    
    public BowlTopping(BowlTopping next){
        super(next);
    }
    
    public double cost(){
        return (super.cost() + .44);
    }
    
    public String toString(){
        return super.toString() + "Topping: ";
    }
    //This method is a bit harder to understand, basically it is checking for a
    //duplicate within the BowlItem class, 'this' being the currennt topping
    public boolean isDuplicate()
    {
        return super.isDuplicate(this);
    }
    
    
}