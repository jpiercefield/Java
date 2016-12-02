/**The BowlBuilder class is where the actual creation of the order is being dealt with.
These methods error check to see if correct input has been added or not. 
The specific order: base, protein, then topping.
Instance variables include: BowlItem bowlItem, boolean baseTrue, and boolean proteinTrue
The methods: buildBase(), buildProtein(), buildTopping(), and an orderBowl() method*/

public class BowlBuilder{

    
    private BowlItem bowlItem;
    private boolean baseTrue = false;
    private boolean proteinTrue = false;

    //creates the BowlBase if the input is okay, else does nothing and returns false
    public boolean buildBase(char baseChar){
        baseChar = Character.toUpperCase(baseChar);
        switch(baseChar){
            case 'Q':
                // option for not being improved - bowlItem = new Quinoa();
                bowlItem = new BowlBaseImproved(2.79, "Quinoa");
                break;
            case 'G':
                //option for not being improved bowlItem - new MixedGreen();
                bowlItem = new BowlBaseImproved(2.49, "Mixed Greens");
                break;
            case 'P':
                //option for not being improved - bowlItem = new BakedPotato();
                bowlItem = new BowlBaseImproved(1.99, "Baked Potato");
                break;
            default:
                return false;

        }
        baseTrue = true;
        return true;
    }

    //creates a BowlProtein and adds it to the order if the input is okay
    public boolean buildProtein(char proteinChar){
        if(baseTrue){
            proteinChar = Character.toUpperCase(proteinChar);
            if(bowlItem instanceof BowlBase){
                switch(proteinChar){
                    case 'G': //grilledChick
                        bowlItem = new GrilledChicken(((BowlBase)bowlItem));
                        break;
                    case 'S': //steak
                        bowlItem = new Steak(((BowlBase)bowlItem));
                        break;
                    case 'B': //black beans
                        bowlItem = new BlackBeans(((BowlBase)bowlItem));
                        break;
                    case 'D': //done op
                        if(proteinTrue)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    default: //default no
                        return false;
                }
            }else if(bowlItem instanceof BowlProtein){
                switch(proteinChar){
                    case 'G':
                        bowlItem = new GrilledChicken(((BowlProtein)bowlItem));
                        break;
                    case 'S':
                        bowlItem = new Steak(((BowlProtein)bowlItem));
                        break;
                    case 'B':
                        bowlItem = new BlackBeans(((BowlProtein)bowlItem));
                        break;
                    case 'D':
                        if(proteinTrue)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    default:
                        return false;
                }
            }
            proteinTrue = true; // = true... escape
            return false;
        }else{
            System.err.println("No Base --"); //peronal use, error checking
            return false;
        }
    }

    //creates a BowlTopping and adds it to the order if the input is okay
    public boolean buildTopping(char topChar){
        if(proteinTrue){
            BowlItem tempItem = bowlItem;
            BowlItem oldBowlItem = bowlItem;
            topChar = Character.toUpperCase(topChar);
            if(bowlItem instanceof BowlTopping){
               switch(topChar){
                case 'T': //tomato
                    tempItem = new Tomato(((BowlTopping)bowlItem));
                    break;
                case 'A': //avocado
                    tempItem = new Avocado(((BowlTopping)bowlItem));
                    break;
                case 'P': //peppers
                    tempItem = new Peppers(((BowlTopping)bowlItem));
                    break;
                case 'E': //edamame
                    tempItem = new Edamame(((BowlTopping)bowlItem));
                    break;
                case 'D': //done option
                    return true;
                default: //default no
                    return false;
               }
            }else if(bowlItem instanceof BowlProtein){
               switch(topChar){
                case 'T': //tomato
                    tempItem = new Tomato(((BowlProtein)bowlItem));
                    break;
                case 'A': //avocado
                    tempItem = new Avocado(((BowlProtein)bowlItem)); //break-point? -STEP-INTO
                    break;
                case 'P': //peppers
                    tempItem = new Peppers(((BowlProtein)bowlItem));
                    break;
                case 'E': //edamame
                    tempItem = new Edamame(((BowlProtein)bowlItem));
                    break;
                case 'D': //done op
                    return true;
                default: //default
                    return false;
               }
            }
            //---Test to see if this isDuplicae.. bi = current? - temp.. 
            if(((BowlTopping)tempItem).isDuplicate()){
                bowlItem = oldBowlItem;
            }else{
                bowlItem = tempItem;
            }
            return false;
        }else{
            System.err.println("No proteins were added yet.."); //place hold ---
            return false;
        }
    }

     //returns the finished bowl or null if the order is incomplete
    public BowlItem orderBowl(){
        if(proteinTrue) //add bool var - place hold
        {
            return bowlItem; //return bowl
        }else{
            return null; //else null 
        }
    }

}