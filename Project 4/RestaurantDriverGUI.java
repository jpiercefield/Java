import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Iterator;

/**
 * Written By: Drew Michel and James Piercefield
 * 
 * RestaurantDriverGUI - Creates interactive GUI with the RestuarantDriver
 * - Holds all necessary components
 */
public class RestaurantDriverGUI extends JFrame{

    private static Restaurant restaurant = null;
    private static final DecimalFormat FMT = new DecimalFormat("$#,##0.00");
    private static String name;
    private JTextArea infoArea;
    private JScrollPane taScroller;
    private JPanel restButtons, restLabels, top;
    
    private JButton btnStatus, btnItemNames, btnSort, 
    				btnHelp, btnAdd, btnSubtract, 
    				btnActivate, btnDiscontinue, btnOrder,
    				btnRateItem, btnUpdatePrice, btnProfit, 
    				btnAverageRating, btnWrite;
    
    private JLabel lblReviewerName, lblReviewerRating, lblReviewerDate, 
    			   lblItemName, lblOrders, lblCategory, lblServingSize,
    			   lblCalories, lblRetail, lblWholesale, 
    			   lblPriceChange, lblSortField, lblSortAlg;
    
    private JTextField txtReviewerName, txtReviewerRating, txtReviewerDate, 
    				   txtItemName, txtOrders, txtServingSize,
    				   txtCalories, txtRetail, txtWholesale, 
    				   txtPriceChange, txtSortField, txtSortAlg;
    
    private JCheckBox checkAll, checkObject, checkWholesale;
    private JComboBox<MenuCategory> comboCategories;
    /**
     * Main Method- Takes in arguments of various lengths. <br>
     * -Proceeds to make a Restaurant object through the various constructors, based on argument length. <br>
     * -Creates new GUI object
     * @param restrauntname
     */
    public static void main(String[] args){
        try{
            if(args.length == 1){
                restaurant = new Restaurant(args[0]);
                name = args[0];
            }
            else if(args.length == 2){
                restaurant = new Restaurant(args[0], args[1]);
                name = args[0];
            }
            else if(args.length >= 3){
                restaurant = new Restaurant(args[0], args[1], Boolean.parseBoolean(args[2]));
                name = args[0];
            }
            else{
                System.out.println("Usage: java RestaurantDriver restName fileName isObject");
                return;
            }

        }catch(RestaurantException re){
            System.out.println(re.getMessage());
        }

        RestaurantDriverGUI driver = new RestaurantDriverGUI(name);

    }

    /**
     * Creates all necessary components for the GUI. 
     * Sets the layout (GridLayout), adds a TextArea with a ScrollPane
     * and an area for new user input. 
     * Calls methods to initalize buttons and labels, proceeds to add to GUI. 
     * @param name A name for the restuarant, provided upon top of GUI
     */
    public RestaurantDriverGUI(String name){
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        center();
        infoArea = new JTextArea();
        taScroller = new JScrollPane(infoArea);
        top = new JPanel();
        top.setLayout(new GridLayout(1,2));
        initLabels();
        top.add(restLabels);
        top.add(taScroller);
        this.getContentPane().add(top, BorderLayout.CENTER);
        initButtons();
        this.getContentPane().add(restButtons, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    /**
     * Creates a new JPanel to contain the labels and their text fields.
     * Sets layout to (GridLayout) 15 columns and 2 rows to hold above.
     */
    public void initLabels(){
        restLabels = new JPanel();
        restLabels.setLayout(new GridLayout(15,2));
        lblReviewerName = new JLabel("Reviewer Name");
        restLabels.add(lblReviewerName);
        txtReviewerName = new JTextField();
        restLabels.add(txtReviewerName);
        lblReviewerRating = new JLabel("Reviewer Rating");
        restLabels.add(lblReviewerRating);
        txtReviewerRating = new JTextField();
        restLabels.add(txtReviewerRating);
        lblReviewerDate = new JLabel("ReviewerDate");
        restLabels.add(lblReviewerDate);
        txtReviewerDate = new JTextField();
        restLabels.add(txtReviewerDate);
        lblItemName = new JLabel("Item Name");
        restLabels.add(lblItemName);
        txtItemName = new JTextField();
        restLabels.add(txtItemName);
        lblOrders = new JLabel("# Orders");
        restLabels.add(lblOrders);
        txtOrders = new JTextField();
        restLabels.add(txtOrders);
        lblCategory = new JLabel("Category");
        restLabels.add(lblCategory);
        comboCategories = new JComboBox<MenuCategory>(MenuCategory.values());
        restLabels.add(comboCategories);
        lblServingSize = new JLabel("ServingSize");
        restLabels.add(lblServingSize);
        txtServingSize = new JTextField();
        restLabels.add(txtServingSize);
        lblCalories = new JLabel("# Calories");
        restLabels.add(lblCalories);
        txtCalories = new JTextField();
        restLabels.add(txtCalories);
        lblRetail = new JLabel("Retail Price");
        restLabels.add(lblRetail);
        txtRetail = new JTextField();
        restLabels.add(txtRetail);
        lblWholesale =  new JLabel("Wholesale Price");
        restLabels.add(lblWholesale);
        txtWholesale = new JTextField();
        restLabels.add(txtWholesale);
        lblPriceChange = new JLabel("% Price Change");
        restLabels.add(lblPriceChange);
        txtPriceChange = new JTextField();
        restLabels.add(txtPriceChange);
        lblSortField = new JLabel("Sort Field");
        restLabels.add(lblSortField);
        txtSortField = new JTextField();
        restLabels.add(txtSortField);
        lblSortAlg = new JLabel("Sort Algorithm");
        restLabels.add(lblSortAlg);
        txtSortAlg = new JTextField();
        restLabels.add(txtSortAlg);
        checkAll = new JCheckBox("All Items?");
        restLabels.add(checkAll);
        checkObject = new JCheckBox("Use Object Files?");
        restLabels.add(checkObject);
        checkWholesale = new JCheckBox("Wholesale Price?");
        restLabels.add(checkWholesale);

    }
    

    /**
     * Initializes buttons, creates a new JPanel to contain the buttons.
     * Sets a FlowLayout inorder to organize the buttons cleanly.
     * Adds listeners to the buttons, so we know when they are clicked.
     */
    public void initButtons(){
        restButtons = new JPanel();
        restButtons.setLayout(new FlowLayout());
        btnStatus = new JButton("Status");
        btnStatus.addActionListener(new StatusListener());
        restButtons.add(btnStatus);
        btnItemNames = new JButton("All Item Names");
        btnItemNames.addActionListener(new ItemNamesListener());
        restButtons.add(btnItemNames);
        btnSort = new JButton("Sort");
        btnSort.addActionListener(new SortListener());
        restButtons.add(btnSort);
        btnHelp = new JButton("Help");
        btnHelp.addActionListener(new HelpListener());
        restButtons.add(btnHelp);
        btnAdd = new JButton("+");
        btnAdd.addActionListener(new AddListener());
        restButtons.add(btnAdd);
        btnSubtract = new JButton("-");
        btnSubtract.addActionListener(new SubtractListener());
        restButtons.add(btnSubtract);
        btnActivate = new JButton("Activate");
        btnActivate.addActionListener(new ActivateListener());
        restButtons.add(btnActivate);
        btnDiscontinue = new JButton("Discontinue");
        btnDiscontinue.addActionListener(new DiscontinueListener());
        restButtons.add(btnDiscontinue);
        btnOrder = new JButton("Order");
        btnOrder.addActionListener(new OrderListener());
        restButtons.add(btnOrder);
        btnRateItem = new JButton("Rate Item");
        btnRateItem.addActionListener(new RateItemListener());
        restButtons.add(btnRateItem);
        btnUpdatePrice = new JButton("Update Price");
        btnUpdatePrice.addActionListener(new UpdatePriceListener());
        restButtons.add(btnUpdatePrice);
        btnProfit = new JButton("Profit");
        btnProfit.addActionListener(new ProfitListener());
        restButtons.add(btnProfit);
        btnAverageRating = new JButton("Average Rating");
        btnAverageRating.addActionListener(new AverageRatingListener());
        restButtons.add(btnAverageRating);
        btnWrite = new JButton("Write File");
        btnWrite.addActionListener(new WriteListener());
        restButtons.add(btnWrite);
    }
    
    /**
     * Listener for the "Status" button. <br>
     * Appends status from restaurant to the infoArea
     */
    private class StatusListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            infoArea.append(restaurant.toString());
        }
    }

    /**
     * Listener for the "All Item Names" button. <br>
     * Appends all the item names from the restaurant to the infoArea
     */
    private class ItemNamesListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            infoArea.append("All Item Names\r\n");
            Iterator itr = restaurant.getAllItemNames().iterator();
            while(itr.hasNext()){
                infoArea.append((String) itr.next() + "\r\n");
            }
        }

    }
    
    /**
     * Listener for the "Sort" button. <br>
     * Calls necessary method to sort the field. <br>
     * Appends the information from the sort method within the restuarant object, to the infoArea.
     */
    private class SortListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int field = 0;
            int alg = 0;
            if(!txtSortField.getText().equals("") && !txtSortAlg.getText().equals("")){
                try
                {
                    field = Integer.parseInt(txtSortField.getText());
                    alg = Integer.parseInt(txtSortAlg.getText());
                }
                catch(NumberFormatException nfe)
                {
                    field = 1;
                    alg = 1;
                }
            }

            if(field < 1 || field > 3){
                field = 1;
            }
            if(alg < 1 || alg > 2){
                alg = 1;
            }

            infoArea.append("Attempting to sort\r\nSorting on field " + field);
            infoArea.append("\r\nUsing sort " + alg + "\r\nCompleted Sort\r\n\r\n");
            infoArea.append(restaurant.sort(field, alg) + "\r\n");
            clearTextFields();
        }
    }

    /**
     * Listener for the "Help" button.<br>
     * Appends sorting methods to the infoArea, allowing user to see the options.
     */
    private class HelpListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            infoArea.append(helpList());
        }
        public String helpList(){
            String list;
            list = "Sort Fields:\r\n" + "1. item name(asc)\r\n" + "2. item profit (desc)\r\n"
            + "3. item average rating(desc)\r\n" + "Sort Algorithms:\r\n" + 
            "1. Selection Sort\r\n" + "2. Insertion Sort\r\n\r\n";
            return list;
        }
    }
    
    /**
     * Listener for the "Add" button. <br>
     * Creates a new item to add to the restaurant.<br>
     * Appends if the addition fields were successfully added or if information was wrong to the infoArea.
     */
    private class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String itemName, tempCat;
            int servingSize, numCalories;
            double price, wholesale;
            MenuCategory cat;

            infoArea.append("Processing Add..\r\n");
            itemName = txtItemName.getText();
            if(!itemName.equals("")){
                try{
                    servingSize = Integer.parseInt(txtServingSize.getText());
                    numCalories = Integer.parseInt(txtCalories.getText());
                    price = Double.parseDouble(txtRetail.getText());
                    wholesale = Double.parseDouble(txtWholesale.getText());
                    cat = (MenuCategory) comboCategories.getSelectedItem();
                }catch(NumberFormatException nfe){
                    infoArea.append("Must enter whole numbers for serving size and # of calories, and must enter numeric data for retail and wholesale.\r\n");
                    return;
                }
            }else{
                infoArea.append("Need non-blank item name.\r\n");
                return;
            }

            try{
                restaurant.addToMenu(itemName, cat, servingSize, numCalories, price, wholesale);
            }catch(RestaurantException re){
                infoArea.append(re.getMessage() + "\r\n");
            }

            infoArea.append("Item " + itemName + " added successfully.\r\n");
            clearTextFields();
        }
    }
    
    /**
     * Listener for the "-" button. <br>
     * Removes the given item based on the name given. <br>
     * Appends the success to the infoArea.
     */
    private class SubtractListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            String itemName = txtItemName.getText();
            infoArea.append("Processing remove...\r\n");

            if(!itemName.equals("")){
                restaurant.removeFromMenu(itemName);
                infoArea.append(itemName + " successfully removed from the menu\r\n");
            }
            else{
                infoArea.append("Need non-blank item name.");
            }
            clearTextFields();
        }
    }

    /**
     * Listener for the "Activate" button. <br>
     * Activates given item from the input given by ItemName<br>
     * Appends Activation success to the infoArea.
     */
    private class ActivateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            String itemName = txtItemName.getText();

            infoArea.append("Processing activate...\r\n");
            if(!checkAll.isSelected()){
                if(!itemName.equals("")){

                    if(restaurant.activate(itemName)){
                        infoArea.append(itemName + " successfully activated\r\n");
                    }
                    else{
                        infoArea.append(itemName + " unsuccessfully activated\r\n");
                    }

                }
                else{
                    infoArea.append("Need non-blank item name.\r\n");
                }
            }
            else{
                restaurant.activate();
                infoArea.append("Activated all menu items\r\n");
            }

            clearTextFields();
        }
    }
    
    /**
     * Listener for the "Discontinue" button.
     * Discontinues given item from the input given by ItemName<br>
     * Appends Discontinue success to the infoArea.
     */
    private class DiscontinueListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String itemName = txtItemName.getText();

            infoArea.append("Processing discontinue...\r\n");
            if(!checkAll.isSelected()){
                if(!itemName.equals("")){

                    if(restaurant.discontinue(itemName)){
                        infoArea.append(itemName + " successfully discontinued\r\n");
                    }
                    else{
                        infoArea.append(itemName + " unsuccessfully discontinued\r\n");
                    }

                }
                else{
                    infoArea.append("Need non-blank item name.\r\n");
                }
            }
            else{
                restaurant.discontinue();
                infoArea.append("Discontinued all menu items\r\n");
            }

            clearTextFields();
        }
    }

    /**
     * Listener for the "Order" button.<br>
     * Orders items, based on itemName and amount of orders. <br>
     * Appends the success of the order to the infoArea <br>
     */
    private class OrderListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String itemName = txtItemName.getText();

            infoArea.append("Processing order...\r\n");
            if(!itemName.equals("")){
                try{
                    int numOrders = Integer.parseInt(txtOrders.getText());
                    if(numOrders > 0){
                        if(restaurant.order(itemName, numOrders)){
                            infoArea.append(numOrders + " of " + itemName + " successfully processed.\r\n");
                        }
                        else{
                            infoArea.append(numOrders + " of " + itemName + " unsuccessfully processed.\r\n");
                        }
                    }
                    else{
                        infoArea.append("Must enter whole number for # orders.\r\n");
                    }
                }catch(NumberFormatException nfe){
                    infoArea.append("Must enter whole number for # orders.\r\n");
                }
            }   
            else{
                infoArea.append("Need to enter non-blank item name.\r\n");
            }

            clearTextFields();
        }
    }
    
    /**
     * Listener for "Rate Item" button. <br>
     * Processes the itemName, reviewerName, date, and the rating in order to add the rating to the restaurant.<br>
     * Appends success to the infoArea.
     */
    private class RateItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String itemName = txtItemName.getText();
            String reviewerName = txtReviewerName.getText();
            String date = txtReviewerDate.getText();

            infoArea.append("Processing item rating...\r\n");
            if(!(itemName.equals("") || reviewerName.equals("") || date.equals(""))){
                try{
                    int rating = Integer.parseInt(txtReviewerRating.getText());
                    try{
                        if(restaurant.addRating(itemName, reviewerName, date, rating)){
                            infoArea.append("Rating successfully added for " + itemName + "\r\n");
                        }
                        else{
                            infoArea.append("Rating unsuccessfully added for " + itemName + "\r\n");
                        }
                        clearTextFields();
                    }catch(RestaurantException re){
                        infoArea.append(re.getMessage() + "\r\n");
                        infoArea.append("Rating unsuccessfully added for " + itemName + "\r\n");
                    }
                }catch(NumberFormatException nfe){
                    infoArea.append("Must enter whole number for rating.\r\n");
                }   
            }
            else{
                infoArea.append("Need non-blank item name, reviewer name, and date.\r\n");
            }
        }
    }

    /**
     * Listener for the "Update Price" button. <br>
     * Processes the itemName and the new price percentage change. <br>
     * User also has option to add a price change to all items.
     */
    private class UpdatePriceListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String itemName = txtItemName.getText();
            int percentage = -1;
            boolean isWholesale = false;
            boolean all = false;
            infoArea.append("Processing update price...\r\n");
            try{
                percentage = Integer.parseInt(txtPriceChange.getText());
            }catch(NumberFormatException nfe){
                infoArea.append("Must have whole number for % change.\r\n");
                return;
            }

            if(checkWholesale.isSelected()){
                isWholesale = true;
            }

            if(checkAll.isSelected()){
                all = true;
            }

            if(!all){
                if(!itemName.equals("")){
                    if(restaurant.updatePrice(isWholesale, itemName, percentage)){
                        infoArea.append("Price for " + itemName + " successfully changed.\r\n");
                    }
                    else{
                        infoArea.append("Price for " + itemName + " unsuccessfully changed.\r\n");
                    }
                    clearTextFields();
                }
                else{
                    infoArea.append("Need non-blank item name.\r\n");
                }
            }
            else{
                if(restaurant.updatePrice(isWholesale, percentage)){
                    infoArea.append("Successfully changed prices for all menu items\r\n");
                    clearTextFields();
                }
                else{
                    infoArea.append("Unsuccessfully changed prices for all menu items\r\n");
                }
            }
        }
    }
    
    /**
     * Listener for the "Profit" button. <br>
     * Appends the total profit of the restaurant to the infoArea.
     */
    private class ProfitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            infoArea.append("The total profit of restaurant " + name + " is " + FMT.format(restaurant.getTotalProfit()) + ".\r\n");
        }
    }

    /**
     * Listener for the "Average Rating" button. <br>
     * Appends the average rating for the menu items to the infoArea.
     */
    private class AverageRatingListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            infoArea.append("Processing average rating...\r\n");
            infoArea.append("The average rating for menu items at restaurant " + name + " is " + FMT.format(restaurant.getAverageItemRating()).substring(1) + ".\r\n");
        }
    }

    /**
     * Listener for the "Write" button.<br>
     * Writes all the information to an output file for the user, based on file name. <br>
     * Appends success of the write to the infoArea.
     */
    private class WriteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String file = SimpleDialogs.stringInput("Please enter a file name" , "Output File");
            boolean isObjectFile = false;

            infoArea.append("Writing information to a file.\r\n");

            if(checkObject.isSelected()){
                isObjectFile = true;
            }
            try{
                restaurant.writeToFile(file, isObjectFile);
                infoArea.append(file + " was successfully written.\r\n");
            }catch(RestaurantException re){
                SimpleDialogs.normalOutput(re.getMessage(), "Problem writing file");
            }

        }
    }

    /**
     * Clears all text fields, allowing for correct new user input.<br>
     * Sets the checkAll, checkObject, and checkWholesale boxes to false.
     */
    public void clearTextFields(){
        txtReviewerName.setText("");
        txtReviewerRating.setText("");
        txtReviewerDate.setText("");
        txtItemName.setText("");
        txtOrders.setText("");
        txtServingSize.setText("");
        txtCalories.setText("");
        txtRetail.setText("");
        txtWholesale.setText("");
        txtPriceChange.setText("");
        txtSortField.setText("");
        txtSortAlg.setText("");
        checkAll.setSelected(false);
        checkObject.setSelected(false);
        checkWholesale.setSelected(false);
    }

    /**
     * Sets the size (height,width) and places GUI in
     * the center of the screen.
     */
    public void center(){
        setSize(1200,500);
        Dimension screenSize = getToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth/2 - 1200/2, screenHeight/2 - 500/2);
    }
}
