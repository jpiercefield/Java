import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class CDCollectionGUI extends JFrame
{

    private CDCollection theCollection; // holds all CD information MODEL
    private JPanel pnlButtons; // holds all the buttons
    private JPanel pnlCDs;
    private JPanel pnlBottom;
    private JTextArea infoArea;
    private JScrollPane taScroller;
    private JButton btnSort, btnAll, btnAdd, btnFirst, btnLast,
                    btnNext, btnPrevious, btnClear, btnSave, btnObliterate;
    private JLabel lblTitle, lblArtist, lblPrice, lblTracks;
    private JTextField txtTitle, txtArtist, txtPrice, txtTracks;
    private LocationListener locListener;
    private ImageIcon icon;
    
    public CDCollectionGUI()
    {
        this("cds.txt");
    }
    
    public CDCollectionGUI(String fileName)
    {
        super("CDCollectionGUI");
        icon = new ImageIcon("Scruffy.jpg"); // just for fun, picture on upper left corner of frame
        this.setIconImage(icon.getImage()); 
        this.setLayout(new GridLayout(2, 1)); ///////
        infoArea = new JTextArea();
        taScroller = new JScrollPane(infoArea); // for scrollable text area
        this.add(taScroller);
        pnlBottom = new JPanel();
        pnlBottom.setLayout(new GridLayout(2, 1)); ///////
        /* this.*/initCDPanel();
        pnlBottom.add(pnlCDs);
        initButtons();
        pnlBottom.add(pnlButtons);
        this.add(pnlBottom);
        try
        {
           theCollection = new CDCollection(fileName);
        }
        catch (IOException ioe)
        {
            theCollection = new CDCollection();
            infoArea.append("CD Collection is empty\r\n");
        }
        catch (ClassNotFoundException cnfe)
        {
            theCollection = new CDCollection();
            infoArea.append("CD Collection is empty\r\n");
        }
        this.setSize(400, 480);
        //this.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent we) { System.exit(0);}});
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  setResizable(false);
      //   setEnabled(false); // experiment, DON'T DO IF YOU WANT A USABLE GUI
    }
  
    private void initButtons()
    {
        pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout());
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new AddListener());
        pnlButtons.add(btnAdd);
        btnAll = new JButton("All");
        btnAll.addActionListener(new AllListener());
        pnlButtons.add(btnAll);
        btnSort = new JButton("Sort");
        btnSort.addActionListener(new SortListener());
        pnlButtons.add(btnSort);
        initMovementButtons();
        btnClear = new JButton("Clear");
        btnClear.addActionListener(new ClearListener());
        pnlButtons.add(btnClear);
        btnSave = new JButton("Save");
        btnSave.addActionListener(new SaveListener());
        pnlButtons.add(btnSave);
        btnObliterate = new JButton("Obliterate");
        btnObliterate.addActionListener(new ObliterateListener());
        pnlButtons.add(btnObliterate);
    }
    
    private void initMovementButtons()
    {
        locListener = new LocationListener();
        btnFirst = new JButton("First");
        btnFirst.addActionListener(locListener);
        pnlButtons.add(btnFirst);
        btnLast = new JButton("Last");
        btnLast.addActionListener(locListener);
        pnlButtons.add(btnLast);
        btnPrevious = new JButton("Previous");
        btnPrevious.addActionListener(locListener);
        pnlButtons.add(btnPrevious);
        btnNext = new JButton("Next");
        btnNext.addActionListener(locListener); // the same listener can be attached to different buttons
        pnlButtons.add(btnNext);
    }
 
    private class LocationListener implements ActionListener
    {
    
        private int location = 0;
        
        public void resetLocation()
        {
            location = 0;
            if (theCollection.getSize() > 0)
            {
                updateTextFields();
                infoArea.append("Displaying first CD\r\n");
            }
        }

        private void updateTextFields()
        {
            CD cd = theCollection.getCD(location);
            txtTitle.setText(cd.getTitle());
            txtArtist.setText(cd.getArtist());
            txtPrice.setText(cd.getPrice()  + "");
            txtTracks.setText(cd.getTracks() + "");                        
        }
        
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e); // for debugging purposes
            Object source = e.getSource();
            System.out.println("Event source: " + source);
            if (source == btnFirst)
            {
              System.out.println("btnFirst clicked");
              location = 0;
            }
            else if (source == btnLast)
            {
              System.out.println("btnLast clicked");  
              location = theCollection.getSize() - 1;
            }
            else if (source == btnPrevious)
            {
              System.out.println("btnPrevious clicked");
              location--;
              if (location < 0) // go to end
              {
                 location = theCollection.getSize() - 1;
              }
            }
            else // source == btnNext
            {
                System.out.println("btnNext clicked");
                location++;
                if (location == theCollection.getSize())
                // go to beginning
                {
                    location = 0;
                }
            }
            if (theCollection.getSize() > 0)
            {
                updateTextFields();
                infoArea.append("Displayed CD information\r\n");
            }
            else
            {
                infoArea.append("No CDs to display\r\n");
            }
        }
    }
    
    private class AddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e);
            String title, artist;
            double price = -1;
            int tracks = -1;
            try
            {
                title = txtTitle.getText();
                artist = txtArtist.getText();
                price = Double.parseDouble(txtPrice.getText());
                tracks = Integer.parseInt(txtTracks.getText());
            }
            catch (NumberFormatException nfe)
            {
                infoArea.append("Bad numeric data -- please reenter.\r\n");
                txtPrice.setText(""); // clear text
                txtTracks.setText("");
                return;
            }                
            if (!title.equals("") && !artist.equals("")) // good data for all fields
            {
               theCollection.add(new CD(title, artist, price, tracks));
               clearAllTextFields();
               infoArea.append("The CD has been added.\r\n");
            }
            else
            {
                infoArea.append("Enter data for title and/or artist and try to add again.\r\n");
            }
        }
    }
    
    private void clearAllTextFields()
    {
        txtTitle.setText("");
        txtArtist.setText("");
        txtPrice.setText("");
        txtTracks.setText("");
    }
    
    private class AllListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e);
            infoArea.append("The contents of the CD collection:\r\n");
            infoArea.append(theCollection.toString() + "\r\n");
        }
    }
    
    private class SortListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           System.out.println(e);
           theCollection.sort();
           infoArea.append("The CD collection has been sorted\r\n");
           infoArea.append(theCollection + "\r\n");
           locListener.resetLocation();
        }
    }
    
    private class ClearListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e);
            clearAllTextFields();
        }
    }
    
    private class SaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e);
            JFileChooser chooser = new JFileChooser(".");  // current directory
            int returnVal = chooser.showSaveDialog(CDCollectionGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = chooser.getSelectedFile();
                String filename = file.getName();
                try
                {
                   theCollection.writeCDs(filename);
                   infoArea.append("Saved collection to " + filename + "\r\n");
                }
                catch (IOException ioe)
                {
                   infoArea.append("Could not save collection to " + filename + "\r\n");
                }
            }
            else
            {
                infoArea.append("Save command cancelled by user.\r\n");
            }
        }
    }
    
    private class ObliterateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println(e);
            theCollection.obliterate();
        }
    }
    
    private void initCDPanel()
    {
        pnlCDs = new JPanel();
        pnlCDs.setLayout(new GridLayout(4,2)); ////////
        lblTitle = new JLabel("Title");
        txtTitle = new JTextField();
        lblArtist = new JLabel("Artist");
        txtArtist = new JTextField();
        lblPrice = new JLabel("Price");
        txtPrice = new JTextField();
        lblTracks = new JLabel("Tracks");
        txtTracks = new JTextField();
        pnlCDs.add(lblTitle);
        pnlCDs.add(txtTitle);
        pnlCDs.add(lblArtist);
        pnlCDs.add(txtArtist);
        pnlCDs.add(lblPrice);
        pnlCDs.add(txtPrice);
        pnlCDs.add(lblTracks);
        pnlCDs.add(txtTracks);
    }
        
    public static void main(String[] args)
    {
        CDCollectionGUI gui;
        if (args.length > 0)
           gui = new CDCollectionGUI(args[0]);
        else
           gui = new CDCollectionGUI();
        gui.setVisible(true);
    }           
}