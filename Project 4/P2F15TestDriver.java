public class P2F15TestDriver
{
    public static void main(String [] args) throws Exception
    {
        Restaurant myRest = new Restaurant("MyRestaurant","restaurants_1.txt");
        try
        {
            if (!myRest.removeFromMenu("Spaghetti")) 
                System.out.println("1 failed");
        }
        catch (Exception e)
        {
            System.out.println("1 failed with crash");
        }
        try
        {
            if (myRest.removeFromMenu("Spaghetti"))
                System.out.println("2 failed");
        }
        catch (Exception e)
        {
            System.out.println("2 failed with crash");
        }
        try
        {
            if (myRest.removeFromMenu("Spaghetti2"))
                System.out.println("3 failed");
        }
        catch (Exception e)
        {
            System.out.println("3 failed with crash");
        }
        try
        {
             if (myRest.addToMenu("Spaghetti",MenuCategory.valueOf("MAIN"),12,500,1.50,8.99))
                System.out.println("4 failed");
        }
        catch (Exception e)
        {
            if (!(e instanceof RestaurantException))
                System.out.println("4 failed with crash");
        }
        try
        {
            if (!myRest.addToMenu("Spaghetti",MenuCategory.valueOf("MAIN"),12,500,8.99,1.50))
                System.out.println("5 failed");
        }
        catch (Exception e)
        {
            if (!(e instanceof RestaurantException))
                System.out.println("5 failed with crash");
        }
        try
        {
            if (myRest.addToMenu("Spaghetti",MenuCategory.valueOf("MAIN"),12,500,8.99,1.50))
                System.out.println("6 failed");
        }
        catch (Exception e)
        {
            if (!(e instanceof RestaurantException))
                System.out.println("6 failed with crash");
        }
        try
        {
            if (!myRest.activate("Spaghetti"))
                System.out.println("7 failed");
        }
        catch (Exception e)
        {
            System.out.println("7 failed with crash");
        }
        try
        {
            if (myRest.activate("Spaghetti2"))
                System.out.println("8 failed");
        }
        catch (Exception e)
        {
            System.out.println("8 failed with crash");
        }
        try
        {
            if (!myRest.order("spaghetti",10))
            {
                System.out.println("9 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("9 failed with crash");
        }
        try
        {
            if (myRest.order("Spaghetti2",10))
                System.out.println("10 failed");
        }
        catch (Exception e)
        {
            System.out.println("10 failed with crash");
        }
        try
        {
            if (!myRest.addRating("Spaghetti","Mark","07/15/2015",4))
                System.out.println("11 failed");
        }
        catch (Exception e)
        {
            System.out.println("11 failed with crash");
        }
        try
        {
            if (myRest.addRating("Spaghetti2","Mark","07/15/2015",4))
                System.out.println("12 failed");
        }
        catch (Exception e)
        {
            System.out.println("12 failed with crash");
        }
        try
        {
            if (myRest.addRating("Spaghetti","Mark","15/07/2015",4))
                System.out.println("13 failed");
        }
        catch (Exception e)
        {
            if (!(e instanceof RestaurantException))
                System.out.println("13 failed with crash");
        }
        try
        {
            if (myRest.addRating("Spaghetti","Mark","ab/cd/ef",4))
                System.out.println("14 failed");
        }
        catch (Exception e)
        {
            if (!(e instanceof RestaurantException))
                System.out.println("14 failed with crash");
        }
        if (Math.abs(myRest.getAverageItemRating() - 3.83) > 0.01)
        {
            System.out.println("15 failed");
        }
        try
        {
            String sortResult = myRest.sort(2, 2);
            int tacos = sortResult.indexOf("Tacos");
            int applePie = sortResult.indexOf("Apple Pie");
            int spaghetti = sortResult.indexOf("Spaghetti");
            boolean correctOrder = tacos != -1 && applePie != -1 && spaghetti != -1 &&
                                    tacos < applePie && applePie < spaghetti;
            if (!correctOrder)
            {
                System.out.println("16 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("16 failed with crash");
        }
        try
        {
            myRest.writeToFile("MyRestaurant0.out.txt", false);
            Restaurant myRest2 = new Restaurant("MyRestaurant", "MyRestaurant0.out.txt");
            String nameString = myRest2.getAllItemNames().toString().toLowerCase();
            if (!myRest2.getName().equals("MyRestaurant") || nameString.indexOf("spaghetti") == -1)
            {
                System.out.println("17 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("17 failed with crash");
        }

        try
        {
            myRest.writeToFile("MyRestaurant0.out.ser", true);
            Restaurant myRest3 = new Restaurant("MyRestaurant", "MyRestaurant0.out.ser", true);
            String nameString = myRest3.getAllItemNames().toString().toLowerCase();
            if (!myRest3.getName().equals("MyRestaurant") || nameString.indexOf("spaghetti") == -1)
            {
                System.out.println("18 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("18 failed with crash");
        }
        Restaurant myRest4 =  myRest4 = new Restaurant("MyRestaurant", "restaurants_1.txt");;
                   
        try
        {
           if (!myRest4.addToMenu("Sweet Potato",MenuCategory.valueOf("SIDE"),12,250,1.79,0.54))
                System.out.println("19 failed");
        }
        catch (Exception e)
        {
            System.out.println("19 failed with crash");
        }
        try
        {
           if (!myRest4.activate("Sweet Potato"))
                System.out.println("20 failed");
        }
        catch (Exception e)
        {
            System.out.println("20 failed with crash");
        }
        try
        {
           if (!myRest4.discontinue("Lava Cake"))
                System.out.println("21 failed");
        }
        catch (Exception e)
        {
            System.out.println("21 failed with crash");
        }
        try
        {
            if (!myRest4.updatePrice(true,"Steak",5))
                System.out.println("22 failed");
        }
        catch (Exception e)
        {
            System.out.println("22 failed with crash");
        }
        String myRest4String = myRest4.toString();
        if (!myRest4String.contains("2.10"))
            System.out.println("23 failed");
        if (!myRest4String.contains("$2.10"))
            System.out.println("24 failed");
        try
        {
           if (myRest4.updatePrice(true,25))
                System.out.println("25 failed");
        }
        catch (Exception e)
        {
            System.out.println("25 failed with crash");
        }
        try
        {
            if (!myRest4.order("Sweet Potato",47))
                System.out.println("26 failed");
            }
        catch (Exception e)
        {
            System.out.println("26 failed with crash");
        }
        try
        {
           if (myRest4.order("Lava Cake",59))
                System.out.println("27 failed");
        }
        catch (Exception e)
        {
            System.out.println("27 failed with crash");
        }
        try
        {
            if (Math.abs(myRest4.getTotalProfit() - 1633.75) > 0.01)
                 System.out.println("28 failed");
        }
        catch (Exception e)
        {
               System.out.println("28 failed with crash");
        }
        try
        {
            if (!myRest4.addRating("Sweet Potato","Slim Jim","11/17/2015",5))
                System.out.println("29 failed");
        }
        catch (Exception e)
        {
            System.out.println("29 failed with crash");
        }
        try
        {
           if (Math.abs(myRest4.getAverageItemRating()) - 4 > 0.01)
                System.out.println("30 failed");
        }
        catch (Exception e)
        {
            System.out.println("30 failed with crash");
        }
        myRest4String = myRest4.toString();
        if (myRest4String.indexOf("Slim Jim") == -1 || myRest4String.indexOf("11/17/2015") == -1)
             System.out.println("31 failed");            
    }
}