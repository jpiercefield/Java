import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class PokeGUI extends FixFrame implements Serializable {
    
    private ArrayList<PokeButton> buttons;
    private JTextArea txtStatus;
    private JScrollPane txtScroller;
    private JPanel pnlBtnPoke;
    private JScrollPane btnPokeScroller;
    private JButton btnAdd;
    private JButton btnAll;
    private JButton btnSort;
    private JButton btnSave;
    private JPanel pnlBtnOther;
    private String fileName;
    private PokeButtonNumComparator comparator;
    
    
    public PokeGUI(String fileName) {
        super(500, 500, "Logan's Program");
        this.fileName = fileName;
        txtStatus = new JTextArea();
        txtScroller = new JScrollPane(txtStatus);
        add(txtScroller, BorderLayout.CENTER);
        setupPokeButtons(fileName);
        setupOtherButtons();
        comparator = new PokeButtonNumComparator();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setupPokeButtons(String fileName) {
        pnlBtnPoke = new JPanel();
        
        buttons = readButtons(fileName);
        for (JButton b : buttons) {
            pnlBtnPoke.add(b);
        }
        btnPokeScroller = new JScrollPane(pnlBtnPoke);
        btnPokeScroller.setPreferredSize(new Dimension(500, 50));
        add(btnPokeScroller, BorderLayout.SOUTH);    
    }
    
    private void setupOtherButtons() {
        pnlBtnOther = new JPanel();
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new AddListener());
        pnlBtnOther.add(btnAdd);
        btnAll = new JButton("All");
        btnAll.addActionListener(new AllListener());
        pnlBtnOther.add(btnAll);
        btnSort = new JButton("Sort");
        btnSort.addActionListener(new SortListener());
        pnlBtnOther.add(btnSort);
        btnSave = new JButton("Save");
        btnSave.addActionListener(new SaveListener());
        pnlBtnOther.add(btnSave);
        add(pnlBtnOther, BorderLayout.EAST);
    }

    private class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            txtStatus.append("Add button clicked.\r\n");
            String pokeName = SimpleDialogs.stringInput("Enter name of pokee", "Poke Dialog");
            PokeButton btnNew = new PokeButton(pokeName, PokeGUI.this);
            buttons.add(btnNew);
            pnlBtnPoke.add(btnNew);
            // PokeGUI.this.setVisible(false);
            // PokeGUI.this.setVisible(true);
            PokeGUI.this.invalidate();
            PokeGUI.this.validate();
            PokeGUI.this.repaint();
        }
    }
    
    private class AllListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            txtStatus.append("All button clicked.\r\n");
            txtStatus.append("Poke Object Information.\r\n");
            for (PokeButton pb : buttons) {
                txtStatus.append(pb.getPoke() + "\r\n");
            }
        }
    }
    
    private class SortListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            txtStatus.append("Sort button clicked.\r\n");
            PokeButton[] allPokes = new PokeButton[buttons.size()];
            int i = 0;
            for (PokeButton pb : buttons) {
                allPokes[i] = pb;
                i++;
            }
            Sort0Generic.<PokeButton> mergeSort(allPokes, comparator);
            // i = 0;
            /* for (PokeButton pb : buttons) {
                pb.setPoke(allPokes[i]);
                i++;
            } */
            buttons = new ArrayList<PokeButton>();
            pnlBtnPoke.removeAll();
            for (PokeButton pb: allPokes) {
                buttons.add(pb);
                pnlBtnPoke.add(pb);
            }

//            PokeGUI.this.setVisible(false);
//            PokeGUI.this.setVisible(true);
            PokeGUI.this.invalidate();
            PokeGUI.this.validate();
            PokeGUI.this.repaint();
        }
    }
    
    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
               txtStatus.append("Save button clicked.\r\n");
               FileOutputStream fos = new FileOutputStream(fileName);
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               oos.writeObject(buttons);
               oos.close();
            }
            catch (IOException ioe) {
                txtStatus.append("Problem writing to output file.\r\n");
                txtStatus.append(ioe + "\r\n");
            }
        }
    }
    private ArrayList<PokeButton> readButtons(String fileName) {
      ArrayList<PokeButton> buttons = new ArrayList<PokeButton>();
      try {
         FileInputStream fis = new FileInputStream(fileName);
         ObjectInputStream ois = new ObjectInputStream(fis);
         buttons = (ArrayList<PokeButton>) ois.readObject();
         for (PokeButton pb : buttons) {
             pb.reregister(this);
         }
      }
      catch (ClassNotFoundException | IOException exc) {
          txtStatus.append("Problem with reading object file. No poke buttons created.\r\n");
          txtStatus.append(exc.toString());
      }
      return buttons;
    }
    
    public void displayText(String text) {
        txtStatus.append(text);
    }
    
    public static void main(String[] args) {
        PokeGUI gui = new PokeGUI("pokeInfo.ser");
    }
}