import static utilities.Validation.*;
public class Rating implements Statable, java.io.Serializable
{
    private String date;
    private String reviewer;
    private int rating;
    public static final int MAX_RATING = 5;
    
    public Rating(int rating) throws RatingException
    {
        this("anonymous", "99/01/01", rating);
    }
    
    public Rating(String reviewer, String date, int rating) throws RatingException
    {
        if (reviewer == null || reviewer.equals("") || isDateInvalid(date) || rating < 1 || rating > MAX_RATING)
        {
            throw new RatingException("Invalid review information");
        }
        this.reviewer = reviewer;
        this.date = date;
        this.rating = rating;
    }
    
    public String getReviewer()
    {
        return reviewer;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public int getRating()
    {
        return rating;
    }
    
    public String toString()
    {
        return "Reviewer: " + reviewer + " Date: " + date + " Rating: " + rating;
    }
    
    public String getState()
    {
        return reviewer + ":" + date + ":" + rating;
    }
}
