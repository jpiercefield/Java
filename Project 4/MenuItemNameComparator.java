import java.util.Comparator;
public class MenuItemNameComparator implements Comparator<MenuItem>
{
    public int compare(MenuItem item1, MenuItem item2)
    {
        return item1.getName().compareTo(item2.getName());
    }
}
