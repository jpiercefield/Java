import java.io.Serializable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PokeButton extends JButton implements Serializable {
    private PokeGUI container;
    private Poke myPoke;
    private PokeButtonListener myPokeListener;
    
    public PokeButton(String name, PokeGUI container) {
        this(new Poke(name), container); 
    }
    
    public PokeButton(Poke poke, PokeGUI container) {
        super(poke.getName());
        this.container = container;
        myPoke = poke;
        myPokeListener = new PokeButtonListener();
        addActionListener(myPokeListener);       
    }
    
    private class PokeButtonListener implements ActionListener, Serializable {
        public void actionPerformed(ActionEvent ae) {
            container.displayText(myPoke.getName() + " poked.\r\n");
            myPoke.poke();
            container.displayText(myPoke.getName() + " now has " + myPoke.getNumPokes() +
                                  " poke(s).\r\n");
            int numPokes = myPoke.getNumPokes();
            Color newColor = null;
            if (numPokes == 100) {
                newColor = JColorChooser.showDialog(null, "Pick Color for >= 100 Pokes",
                                         Color.RED);
            }
            else if (numPokes == 50) {
                newColor = JColorChooser.showDialog(null, "Pick Color for >= 50 Pokes",
                                         Color.MAGENTA);   
            }
            else if (numPokes == 10) {
                newColor = JColorChooser.showDialog(null, "Pick Color for >= 10 Pokes",
                                         Color.PINK);   
            }
            if (newColor != null) {
                setBackground(newColor);
            }
        }
    }
    
    public void reregister(PokeGUI container) {
        removeActionListener(myPokeListener);
        this.container = container;
        addActionListener(myPokeListener);
    }
    
    public Poke getPoke() {
        return myPoke;
    }
    
    public void setPoke(Poke poke) {
        myPoke = poke;
        setText(myPoke.getName());
    }
}