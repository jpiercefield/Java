/**This is the "improved" version of BowlBase, Which is actually extending that. The methods included
   are a Contructor accepting a cost and a string variable, a method to return the total cost, and a
   toString method.*/
public class BowlBaseImproved extends BowlBase{
    
    private double totalCost;
    private String var1;
    public BowlBaseImproved(double totalCost, String var1)
    {
        super();
        this.totalCost = totalCost;
        this.var1 = var1;
    }

    public double cost(){
        return totalCost;
    }
    
    public String toString(){
        return super.toString()+var1+"\r\n";
    }
   

}