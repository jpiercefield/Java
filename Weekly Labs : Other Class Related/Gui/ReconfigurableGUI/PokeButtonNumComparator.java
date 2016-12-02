import java.util.Comparator;
import java.io.Serializable;
public class PokeButtonNumComparator implements Comparator<PokeButton>, Serializable {
    public int compare(PokeButton first, PokeButton second) {
        int result = first.getPoke().getNumPokes() - second.getPoke().getNumPokes();
        if (result == 0) {
            result = first.getPoke().getName().compareTo(second.getPoke().getName());
        }
        return result;
    }
}