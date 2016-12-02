import java.util.Comparator;
public class MenuItemProfitComparator implements Comparator<MenuItem>
{
    public int compare(MenuItem item1, MenuItem item2)
    {
        Double r1 = item1.getTotalSales() - item1.getTotalWholesaleCost();
        Double r2 = item2.getTotalSales() - item2.getTotalWholesaleCost();
        return r2.compareTo(r1);
    }
}
