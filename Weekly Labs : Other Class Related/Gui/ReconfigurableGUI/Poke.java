import java.io.Serializable;

public class Poke implements Serializable {
    private String name;
    private int numPokes;
    private static int numCreated = 0;
    
    public Poke(String name) {
        numCreated++;
        if (name == null || name.equals("")) {
            this.name = "Someone" + numCreated;
        }
        else {
            this.name = name;
        }
    }
    
    public void poke() {
        numPokes++;
    }
    
    public void reset() {
        numPokes = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumPokes() {
        return numPokes;
    }
    
    public String toString() {
        return "Name: " + name + " # Pokes: " + numPokes;
    }
}