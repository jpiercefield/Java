import java.util.Comparator;
public class MenuItemRatingComparator implements Comparator<MenuItem>
{
    public int compare(MenuItem item1, MenuItem item2)
    {
        Double r1 = item1.getAverageRating();
        Double r2 = item2.getAverageRating();
        return r2.compareTo(r1); // from highest to lowest
    }
}
