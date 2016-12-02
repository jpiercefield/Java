/**This is an option which extends the BowlBuilder class - Containing a constructor for the MexicanSalad
Within Mexican Salad - Mixed Greens, Black Beans, Tomato, Peppers, Tomato, and Avocado*/

public class MexicanSalad extends BowlBuilder
{
    //Contructor
    public MexicanSalad()
    {
        buildBase('G');
        buildProtein('B');
        buildTopping('P');
        buildTopping('T');
        buildTopping('A');
    }
    
    
}
