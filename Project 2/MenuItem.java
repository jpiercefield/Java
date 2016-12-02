import java.util.ArrayList;
import java.text.DecimalFormat;

public class MenuItem implements Statable, java.io.Serializable
{
    private String name;
    private MenuCategory type;
    private int servingSize; // in ounces
    private int calories;
    private double price;
    private double wholesaleCost;
    private int numOrders;
    private double totalWholesaleCost;
    private double totalSales;
    private boolean active;
    private boolean recommended;
    private ArrayList<Rating> ratings;
    
    public static final int MIN_SERVING_SIZE = 3;
    public static final int MAX_PERCENTAGE = 20;
    private static final DecimalFormat FMT = new DecimalFormat("$#,##0.00");
    
    public MenuItem(String name, MenuCategory type, int servingSize, int calories, double price,
                    double wholesaleCost) throws MenuItemException
    {
        if (name == null || name.equals("") || type == null || type.equals("") || servingSize < MIN_SERVING_SIZE || calories < 0 || price <= 0 || wholesaleCost <= 0 ||
            wholesaleCost > price)
        {
            throw new MenuItemException("Invalid information for MenuItem");
        }
            
        this.name = name;
        this.type = type;
        this.servingSize = servingSize;
        this.calories = calories;
        this.price = price;
        this.wholesaleCost = wholesaleCost;
        ratings = new ArrayList<Rating>();
    }
    
    public MenuItem(String name, MenuCategory type, int servingSize, int calories, double price,
                    double wholesaleCost, int numOrders, double totalCost,
                    double totalSales, boolean active, boolean recommended,
                    ArrayList<Rating> ratings) throws MenuItemException
    {
        this(name, type, servingSize, calories, price, wholesaleCost);
        this.numOrders = numOrders;
        totalWholesaleCost = totalCost;
        this.totalSales = totalSales;
        this.active = active;
        this.recommended = recommended;
        this.ratings = ratings;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getType()
    {
        return type.toString();
    }
    
    public int getServingSize()
    {
        return servingSize;
    }
    
    public int getCalories()
    {
        return calories;
    }
    
    public double getWholesaleCost()
    {
        return wholesaleCost;
    }
    
    public int getNumOrders()
    {
        return numOrders;
    }
    
    public int getNumRatings()
    {
        return ratings.size();
    }
    
    public double getTotalWholesaleCost()
    {
        return totalWholesaleCost;
    }
    
    public double getTotalSales()
    {
        return totalSales;
    }
    
    public boolean isActive()
    {
        return active;
    }
        
    public boolean isRecommended()
    {
        return recommended;
    }
    
    public String getReviews()
    {
        StringBuilder builder = new StringBuilder();
        for (Rating r : ratings)
        {
            builder.append(r + "\r\n");
        }
        return builder.toString();
    }
    
    public boolean setName(String name)
    {
        boolean result = false;
        if (name != null && !name.equals(""))
        {
            this.name = name;
            result = true;
        }
        return result;
    }
    
    public boolean setServingSize(int size)
    {
        boolean result = false;
        if (size >= MIN_SERVING_SIZE)
        {
            servingSize = size;
            result = true;
        }
        return result;
    }

    public boolean setCalories(int calories)
    {
        boolean result = false;
        if (calories > 0)
        {
            this.calories = calories;
            result = true;
        }
        return result;
    }
    
    public boolean updatePrice(boolean isWholesale, int percentage)
    {
        boolean result = false;
        if (Math.abs(percentage) <= MAX_PERCENTAGE)
        {
            if (isWholesale)
            {
                wholesaleCost = wholesaleCost * (1 + percentage/100.0);
            }
            else
            {
                price = price * (1 + percentage/100.0);
            }
            result = true;
        }
        return result;
    }
    
    public String getState()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(name + "," + type + "," + servingSize + "," + calories + "," + price + "," + wholesaleCost + "," + numOrders + ",");
        builder.append(totalWholesaleCost + "," + totalSales + "," + active + "," + recommended + "," + ratings.size());
        for (Rating r : ratings)
        {
            builder.append("," + r.getState());
        }
        return builder.toString();
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: " + name + " Category: " + type + " Serving Size (oz): " + servingSize + " Calories: " + calories +
                       "\r\nPrice: " + FMT.format(price) + " Wholesale Cost: " + FMT.format(wholesaleCost) + " # Orders: " + numOrders);
        builder.append("\r\nTotal Wholesale Cost: " + FMT.format(totalWholesaleCost) + " Total Sales: " + FMT.format(totalSales) + " Active: " + active);
        builder.append(" Recommended: " + recommended + " Ratings:\r\n");
        for (Rating r : ratings)
        {
            builder.append(r + "\r\n");
        }
        return builder.toString();        
    }
    
    public void discontinue()
    {
        active = false;
    }
    
    public void activate()
    {
        active = true;
    }
    
    public void recommend()
    {
        recommended = true;
    }
    
    public void unrecommend()
    {
        recommended = false;
    }
    
    public boolean addRating(String rater, String date, int rating) throws MenuItemException
    {
        boolean result = false;
        try
        {
            if (active)
            {
               Rating theRating = new Rating(rater, date, rating);
               ratings.add(theRating);
               result = true;
            }
        }
        catch (RatingException re)
        {
            throw new MenuItemException(re.getMessage());
        }
        return result;
    }
    
    public double getAverageRating()
    {
        double result = 0;
        if (ratings.size() > 0)
        {
            for (Rating r: ratings)
            {
                result += r.getRating();
            }
            result /= ratings.size();
        }
        return result;
    }
    
    public boolean order(int numOrders)
    {
        boolean result = false;
        if (active && numOrders > 0)
        {
            this.numOrders += numOrders;
            totalWholesaleCost += wholesaleCost * numOrders;
            totalSales += price * numOrders;
            result = true;
        }
        return result;
    }
}
    