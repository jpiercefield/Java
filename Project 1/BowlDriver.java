 /**James Logan Piercefield
This program is implemented using the Decorator design pattern. 
What the program does - Basically this program is to act like a restraunt. You ask the user
for the input of which type of food he'd like. Starting with asking if they'd like
a build your own bowl (which allows the user to create a bowl from a slection of toppings,
proteins, and bowl type), or the user can select pre-made bowl such as a Mexican Salad
or a Potato Steak. After you have selected your meal, the program will repeat your order with
the total price of that individual seletion. It'll then ask the user if they'd like to create
another bowl. If so the process will repeat, else it will tell the user how many bowls they've 
created and the total price in dollars for everything.*/

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class BowlDriver{
    public static double totalPrice = 0;
    public static void main(String[] args)throws IOException{
        //static FileIO bla = new F
        int totalBowls = 0;
        boolean loop = false;
        String input = "";
        input = Keyboard.getKeyboard().readString("Would you like to order a bowl? (y/n)");
        char inputChar;
        inputChar = input.charAt(0);
        inputChar = Character.toUpperCase(inputChar);
        if(inputChar == 'N')
        {
            System.out.println("You ordered 0 bowl(s) for a grand total of $0.00");
            System.exit(0);
        }

        while(!loop)
        {
            totalBowls++;
            int newNum = 0;
            BowlBuilder newBB = null;
            while(true){
                newNum = menu();

                if(newNum == 1){
                    newBB = new BowlBuilder();
                    requestBase(newBB);
                    requestProteins(newBB);
                    requestToppings(newBB);
                    break;
                }else if(newNum == 2){
                    newBB = new MexicanSalad();
                    break;
                }else if(newNum == 3){
                    newBB = new PotatoSteak();
                    break;
                }
            }
            showOrder(newBB.orderBowl());
            String input1 = null;
            input1 = Keyboard.getKeyboard().readString("Would you like to order another bowl (y/n)? ");
            char test;
            test = input1.charAt(0);
            test = Character.toUpperCase(test);
            while(test!='N' && test!='Y'){
                input1 = Keyboard.getKeyboard().readString("Would you like to order another bowl (y/n)? ");
                test = input1.charAt(0);
                test = Character.toUpperCase(test);
            }

            if(test == 'N')
            {
                System.out.println("You ordered " + totalBowls + " bowl(s) for a grand total of ");
                System.out.println(Currency.formatCurrency(totalPrice));
                loop = true;
            }
        }

    }
    //displays pre-built bowl options or "build your own", returns user menu selection (bowl is not built here)
    public static int menu(){
        System.out.println("\r\n1. Build Your Own");
        System.out.println("\r\n2. Mexican Salad");
        System.out.println("\r\n3. Potato Steak\n");
        int hello = 0;
        String line = Keyboard.getKeyboard().readString("Select from the above: ");
        if(!line.equals("")){
            return Integer.parseInt(line.charAt(0)+"");
        }else{
            return -4;
        }

    }

    //calls the appropriate method on the builder until input is accepted by the builder
    private static void requestBase(BowlBuilder bb)
    {
        boolean checking = false;
        System.out.println("");
        while(!checking)
        {
            String baseOption = null;
            baseOption = Keyboard.getKeyboard().readString("\r\n(Q)unipa. Mixed (G)reens, Baked (P)otato:");
            if(!baseOption.equals(""))
            {
                checking = bb.buildBase(baseOption.charAt(0));
            }else{
                checking = false;
            }

        }

    }
    //adds proteins until user specifies done (at least one protein MUST be selected)
    
    private static void requestProteins(BowlBuilder bb){
        boolean checking = false; 
        System.out.println("\nSelect the proteins for your bowl.");
        while(!checking)
        {
            boolean option = false;
            String proteinOption = null;
            proteinOption = Keyboard.getKeyboard().readString("\r\n(G)rilled Chicken, (S)teak, (B)lack Bean, (D)one:");
            if(!proteinOption.equals(""))
            {
                checking = bb.buildProtein(proteinOption.charAt(0));
            }else{
                checking = false;
            }

        }
    }
    //adds toppings until user specifies done (note how options disappear after selection)
    private static void requestToppings(BowlBuilder bb){
        boolean checking = false;
        System.out.println("Select the toppings for your bowl.");
        while(!checking)
        {
            String toppingOption = "";
            toppingOption = Keyboard.getKeyboard().readString("\r\n(T)omato, (A)vocado, (P)eppers, (E)damame, (D)one:");
            char charChoice;
            if(!toppingOption.equals(""))
            {
                checking = bb.buildTopping(toppingOption.charAt(0));
            }else{
                checking = false;
            }

        }

    }

    //Displays the finished bowl order + this will add that bowl order to the total price for later use.
    private static void showOrder(BowlItem bowlItem){
        System.out.println(bowlItem);
        totalPrice += bowlItem.cost();
        System.out.println(Currency.formatCurrency(bowlItem.cost()));
    }

}