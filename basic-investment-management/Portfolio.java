package basicinvestmentmanagement;

/** 
 * @author Name: James Bunker
 * Course: CIS 2430 
 * Semester: F21
 * 
 * Title: basic-investment-management
 * Files: Investment.java, MutualFund.java, Stock.java, Portfolio.java
 * @version Final
**/

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Portfolio extends JFrame {

    // making the panels and frame accessible from anywhere in class
    static JFrame frame;
    static JPanel startPanel;
    static JPanel buyPanel;
    static JPanel sellPanel;
    static JPanel updatePanel;
    static JPanel gainPanel;
    static JPanel searchPanel;

    // making the arraylist and hashmap able to be accesed from anywhere in class
    static ArrayList<Investment> investmentList = new ArrayList<Investment>();
    static HashMap<String, List<Integer>> keywordHashmap = new HashMap<String, List<Integer>>();
    static int index = 0;// sorry about this one :(

    /**
     * Method prepares the GUI frames location, size, and visibility
     */
    protected static void prepareGui() {
        // initializing the frame and calling the start menu method
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocation(430, 50);
        startMenu();
    }

    /**
     * Method is the start menu GUI that displays the applications information and
     * how to use it
     */
    protected static void startMenu() {
        // fancy new fonts for text display
        Font f = new Font("Serif", Font.BOLD, 30);
        Font g = new Font("Serif", Font.BOLD, 15);
        // creating startpanel and adding to frame
        startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        frame.add(startPanel);

        // command label
        JLabel lbl = new JLabel("Select one of the commands");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setFont(g);
        startPanel.add(lbl);

        // command choices
        String[] choices = { "Buy", "Sell", "Update", "Get Gain", "Search", "Quit" };

        // command combobox, initialzing the size, location, and options
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.setFont(g);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selection = (String) combo.getSelectedItem();
                // switch statement to determing what action should be carried out depending on
                // the selection
                switch (selection) {
                    case "Buy":
                        clearFrame(1);
                        break;
                    case "Sell":
                        clearFrame(2);
                        break;
                    case "Update":
                        clearFrame(3);
                        break;
                    case "Get Gain":
                        clearFrame(4);
                        break;
                    case "Search":
                        clearFrame(5);
                        break;
                    case "Quit":
                        clearFrame(6);
                        break;
                    default:
                        break;
                }

            }

        });
        startPanel.add(cb);

        // welcome banner and formating
        JTextArea welcome = new JTextArea("Welcome to ePortfolio");
        welcome.setLineWrap(true);
        welcome.setWrapStyleWord(true);
        welcome.setFont(f);
        startPanel.add(welcome);
        welcome.setEditable(false);

        // welcome message and formating
        JTextArea welcomeMessage = new JTextArea(
                "Choose a command from the “Commands” menu to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program. ");
        welcomeMessage.setLineWrap(true);
        welcomeMessage.setWrapStyleWord(true);
        welcomeMessage.setFont(f);
        startPanel.add(welcomeMessage);
        welcomeMessage.setEditable(false);
        frame.add(startPanel);
        frame.setVisible(true);
    }

    /**
     * Method is the buy menu GUI that allows the user to purchase investments
     */
    protected static void buyMenu() {
        // keeping the fonts going
        Font f = new Font("Serif", Font.BOLD, 30);
        Font e = new Font("Serif", Font.BOLD, 50);
        Font g = new Font("Serif", Font.BOLD, 15);
        // initializing panel and adding it to frame (no layout)
        buyPanel = new JPanel();
        frame.add(buyPanel);
        buyPanel.setLayout(null);

        // messages display area formmating and creation
        JTextArea display = new JTextArea(100, 100);
        display.setEditable(false);
        display.setBounds(20, 600, 950, 350);
        // adding in the scroll bar
        JScrollPane displayScrollSide = new JScrollPane(display);
        displayScrollSide.setBounds(20, 600, 950, 350);
        displayScrollSide.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        buyPanel.add(displayScrollSide);

        // messages label
        JLabel displayLabel = new JLabel("Messages");
        displayLabel.setFont(g);
        displayLabel.setBounds(20, 560, 300, 50);
        buyPanel.add(displayLabel);

        // panel name
        JLabel optionLabel = new JLabel("Buy Menu");
        optionLabel.setBounds(10, 40, 400, 60);
        optionLabel.setFont(e);
        buyPanel.add(optionLabel);

        // command label
        JLabel lbl = new JLabel("Select one of the commands");
        lbl.setFont(g);
        lbl.setBounds(400, 0, 200, 50);
        buyPanel.add(lbl);

        // command choices
        String[] choices = { "Buy", "Sell", "Update", "Get Gain", "Search", "Quit" };

        // command combobox, initialzing the size, location, and options
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setFont(g);
        cb.setBounds(460, 40, 70, 25);
        cb.addActionListener(new ActionListener() {
            // clearing frame and going to new panel depending on user input
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selection = (String) combo.getSelectedItem();
                switch (selection) {
                    case "Buy":
                        frame.remove(buyPanel);
                        clearFrame(1);
                        break;
                    case "Sell":
                        frame.remove(buyPanel);
                        clearFrame(2);
                        break;
                    case "Update":
                        frame.remove(buyPanel);
                        clearFrame(3);
                        break;
                    case "Get Gain":
                        frame.remove(buyPanel);
                        clearFrame(4);
                        break;
                    case "Search":
                        frame.remove(buyPanel);
                        clearFrame(5);
                        break;
                    case "Quit":
                        clearFrame(6);
                        break;
                    default:
                        break;
                }

            }

        });
        buyPanel.add(cb);

        String[] investmentContents = new String[5];// storing the user inputs in array

        // type box label
        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(f);
        typeLabel.setBounds(50, 100, 100, 50);
        buyPanel.add(typeLabel);

        // type combobox, adding to user input array depending on input
        String[] type = { "stock", "mutualfund" };
        final JComboBox<String> typecb = new JComboBox<String>(type);
        typecb.setMaximumSize(typecb.getPreferredSize());
        typecb.setFont(f);
        typecb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selection = (String) combo.getSelectedItem();
                switch (selection) {
                    case "stock":
                        investmentContents[0] = selection;
                        break;
                    case "mutualfund":
                        investmentContents[0] = selection;
                        break;
                    default:
                        break;
                }

            }

        });
        typecb.setBounds(300, 100, 200, 50);
        buyPanel.add(typecb);

        // symbol textbox label
        JLabel symbolLabel = new JLabel("Symbol");
        symbolLabel.setFont(f);
        symbolLabel.setBounds(50, 100, 100, 250);
        buyPanel.add(symbolLabel);

        // symbol text box
        JTextField symbolText = new JTextField();
        symbolText.setBounds(300, 200, 300, 50);
        symbolText.setFont(f);
        buyPanel.add(symbolText);

        // adding user input to input array, checks that input is not empty
        symbolText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = symbolText.getText();
                investmentContents[1] = command;
                if (investmentContents[1].isBlank() || investmentContents[1].isEmpty()) {
                    investmentContents[1] = null;
                    display.append("ERROR: Enter a non-empty string \n");
                }

            }
        });

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(f);
        nameLabel.setBounds(50, 100, 100, 450);
        buyPanel.add(nameLabel);

        JTextField nameText = new JTextField();
        nameText.setBounds(300, 300, 300, 50);
        nameText.setFont(f);
        buyPanel.add(nameText);

        // adding user input to input array, checks that input is not empty
        nameText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = nameText.getText();
                investmentContents[2] = command;
                if (investmentContents[2].isBlank() || investmentContents[2].isEmpty()) {
                    investmentContents[2] = null;
                    display.append("ERROR: Enter a non-empty string \n");
                }

            }
        });

        // quantity text box label
        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(f);
        quantityLabel.setBounds(50, 100, 150, 650);
        buyPanel.add(quantityLabel);

        // quantity text box
        JTextField quantityText = new JTextField();
        quantityText.setBounds(300, 400, 300, 50);
        quantityText.setFont(f);
        buyPanel.add(quantityText);

        // adding user input to input array, checks that input is greater than 0
        quantityText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = quantityText.getText();
                try {
                    investmentContents[3] = verifyQuantityInput(command);
                } catch (Exception y) {
                }
                if (investmentContents[3] == null) {
                    display.append("ERROR: must be number greater than 0\n");
                }

            }
        });

        // price text box label
        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(f);
        priceLabel.setBounds(50, 100, 100, 850);
        buyPanel.add(priceLabel);

        // price text box
        JTextField priceText = new JTextField();
        priceText.setBounds(300, 500, 300, 50);
        priceText.setFont(f);
        buyPanel.add(priceText);

        // adding user input to input array, checks that input greater than 0
        priceText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = priceText.getText();
                try {
                    investmentContents[4] = verifyPriceInput(command);
                } catch (Exception y) {
                }
                if (investmentContents[4] == null) {
                    display.append("ERROR: must be number greater than 0\n");
                }
            }
        });

        // reset button
        JButton reset = new JButton("Reset");
        reset.setBounds(700, 200, 150, 60);
        reset.setFont(f);
        buyPanel.add(reset);

        // when button pressed refreshes the user input array and all of the textboxes
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Reset")) {
                    priceText.setText("");
                    quantityText.setText("");
                    nameText.setText("");
                    symbolText.setText("");
                    typecb.setSelectedIndex(0);

                    for (int i = 0; i < investmentContents.length; i++) {
                        investmentContents[i] = null;
                    }

                }

            }
        });

        // buy button
        JButton buy = new JButton("Buy");
        buy.setBounds(700, 400, 150, 60);
        buy.setFont(f);
        buyPanel.add(buy);

        // verifies all fields are inputted correctly and purchases investment (if
        // existing then adds to that investment)
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                int validInput = 1;
                for (int i = 0; i < investmentContents.length; i++) {
                    if (investmentContents[i] == null) {
                        validInput = 0;
                    }
                }
                if (validInput == 1) {
                    if (command.equals("Buy")) {
                        try {
                            int existingSymbolIndex = symbolExistence(symbolText.getText());

                            if (existingSymbolIndex == -1 && investmentContents[0].equals("stock")) {// no matches and
                                                                                                     // new
                                                                                                     // stock
                                Stock newStock = new Stock(investmentContents[1], investmentContents[2],
                                        Integer.parseInt(investmentContents[3]),
                                        Double.parseDouble(investmentContents[4]));
                                investmentList.add(newStock);
                                display.append("==================\n" + "Investment:\n");
                                for (int i = 0; i < 5; i++) {
                                    display.append(investmentContents[i] + "\n");
                                }
                                display.append(
                                        "Book value: $" + investmentList.get(investmentList.size() - 1).getBookValue()
                                                + "\n==================\n");

                                // updating hashmap
                                fillHash(investmentList.size() - 1, investmentContents[2]);
                            } else if (existingSymbolIndex == -1 && investmentContents[0].equals("mutualfund")) {// no
                                                                                                                 // matches
                                                                                                                 // and
                                                                                                                 // new
                                // mutualfund

                                MutualFund newMutualFund = new MutualFund(investmentContents[1], investmentContents[2],
                                        Integer.parseInt(investmentContents[3]),
                                        Double.parseDouble(investmentContents[4]));
                                investmentList.add(newMutualFund);
                                display.append("==================\n" + "Investment:\n");
                                for (int i = 0; i < 5; i++) {
                                    display.append(investmentContents[i] + "\n");
                                }
                                display.append(
                                        "Book value: $" + investmentList.get(investmentList.size() - 1).getBookValue()
                                                + "\n==================\n");

                                // updating hashmap
                                fillHash(investmentList.size() - 1, investmentContents[2]);
                            } else {// investment exists, and prompting user to buy more
                                investmentList.get(existingSymbolIndex)
                                        .updateQuantity(Integer.parseInt(investmentContents[3]));
                                investmentList.get(existingSymbolIndex)
                                        .updatePrice(Double.parseDouble(investmentContents[4]));
                                investmentList.get(existingSymbolIndex)
                                        .updateBookValue(Double.parseDouble(investmentContents[4])
                                                * Integer.parseInt(investmentContents[3]));
                                display.append("==================\n" + "Investment:\n");
                                for (int i = 0; i < 5; i++) {
                                    display.append(investmentContents[i] + "\n");
                                }
                                display.append("Book value: $" + investmentList.get(existingSymbolIndex).getBookValue()
                                        + "\n==================\n");
                            }
                        } catch (Exception b) {
                            display.append("ERROR: Ensure all fields are correctly filled\n");
                        }

                    }
                } else {
                    display.append("ERROR: Ensure all fields are correctly filled\n");
                }

            }
        });

        frame.setVisible(true);
    }

    /**
     * Method is the sell menu GUI that allows the user to specify the price and
     * quantity of an investment and sell it
     */
    protected static void sellMenu() {
        // fonts?
        Font f = new Font("Serif", Font.BOLD, 30);
        Font e = new Font("Serif", Font.BOLD, 50);
        Font g = new Font("Serif", Font.BOLD, 15);
        // initializing the panel and adding to frame
        sellPanel = new JPanel();
        frame.add(sellPanel);
        sellPanel.setLayout(null);

        // display for messages
        JTextArea display = new JTextArea(100, 100);
        display.setEditable(false);
        display.setBounds(20, 600, 950, 350);
        JScrollPane displayScrollSide = new JScrollPane(display);
        displayScrollSide.setBounds(20, 600, 950, 350);
        displayScrollSide.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sellPanel.add(displayScrollSide);

        // display label
        JLabel displayLabel = new JLabel("Messages");
        displayLabel.setFont(g);
        displayLabel.setBounds(20, 560, 300, 50);
        sellPanel.add(displayLabel);

        // panel name
        JLabel optionLabel = new JLabel("Sell Menu");
        optionLabel.setBounds(10, 40, 400, 50);
        optionLabel.setFont(e);
        sellPanel.add(optionLabel);

        // command label
        JLabel lbl = new JLabel("Select one of the commands");
        lbl.setFont(g);
        lbl.setBounds(400, 0, 200, 50);
        sellPanel.add(lbl);

        // command choices
        String[] choices = { "Buy", "Sell", "Update", "Get Gain", "Search", "Quit" };

        // command combobox and what happens when each thing is selected
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setFont(g);
        cb.setBounds(460, 40, 70, 25);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selection = (String) combo.getSelectedItem();
                switch (selection) {
                    case "Buy":
                        frame.remove(sellPanel);
                        clearFrame(1);
                        break;
                    case "Sell":
                        frame.remove(sellPanel);
                        clearFrame(2);
                        break;
                    case "Update":
                        frame.remove(sellPanel);
                        clearFrame(3);
                        break;
                    case "Get Gain":
                        frame.remove(sellPanel);
                        clearFrame(4);
                        break;
                    case "Search":
                        frame.remove(sellPanel);
                        clearFrame(5);
                        break;
                    case "Quit":
                        clearFrame(6);
                        break;
                    default:
                        break;
                }

            }

        });
        sellPanel.add(cb);

        // symbol label
        JLabel symbolLabel = new JLabel("Symbol");
        symbolLabel.setFont(f);
        symbolLabel.setBounds(50, 100, 100, 250);
        sellPanel.add(symbolLabel);

        // symbol textbox
        JTextField symbolText = new JTextField();
        symbolText.setBounds(300, 200, 300, 50);
        symbolText.setFont(f);
        sellPanel.add(symbolText);

        // user input for sell menu
        String[] sellContents = new String[3];

        // checking if symbol exists and adding result to input array
        symbolText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = symbolText.getText();
                sellContents[0] = Integer.toString(symbolExistence(command));
            }
        });

        // quantity label
        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(f);
        quantityLabel.setBounds(50, 100, 150, 450);
        sellPanel.add(quantityLabel);

        // quantity text box
        JTextField quantityText = new JTextField();
        quantityText.setBounds(300, 300, 300, 50);
        quantityText.setFont(f);
        sellPanel.add(quantityText);

        // getting user input and verifying it is greater than 0
        quantityText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = quantityText.getText();
                try {
                    sellContents[1] = verifyQuantityInput(command);
                } catch (Exception y) {
                }
                if (sellContents[1] == null) {
                    display.append("ERROR: must be number greater than 0 and must own more than the quantity\n");
                }

            }
        });

        // price label
        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(f);
        priceLabel.setBounds(50, 100, 150, 650);
        sellPanel.add(priceLabel);

        // price text field
        JTextField priceText = new JTextField();
        priceText.setBounds(300, 400, 300, 50);
        priceText.setFont(f);
        sellPanel.add(priceText);

        // getting user input and verifying it is greater than 0
        priceText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = priceText.getText();
                try {
                    sellContents[2] = verifyPriceInput(command);
                } catch (Exception y) {
                }
                if (sellContents[2] == null) {
                    display.append("ERROR: must be number greater than 0\n");
                }

            }
        });

        // reset button
        JButton reset = new JButton("Reset");
        reset.setBounds(700, 200, 150, 60);
        reset.setFont(f);
        sellPanel.add(reset);

        // restting user input array and text boxes
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                // do intput check here
                if (command.equals("Reset")) {
                    priceText.setText("");
                    quantityText.setText("");
                    symbolText.setText("");

                    for (int i = 0; i < sellContents.length; i++) {
                        sellContents[i] = null;
                    }
                }

            }
        });

        // sell button
        JButton sell = new JButton("Sell");
        sell.setBounds(700, 400, 150, 60);
        sell.setFont(f);
        sellPanel.add(sell);

        // verify that user inputed valid input for each section and verifies the user
        // owns more than quantity of the investment
        // sells the investment and unpdates list and map
        sell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                // do intput check here
                int validInput = 1;
                for (int i = 0; i < sellContents.length; i++) {
                    if (sellContents[i] == null) {
                        validInput = 0;
                    }
                }
                if (validInput == 1) {
                    try {
                        if (command.equals("Sell")) {
                            if (Integer.parseInt(sellContents[1]) <= investmentList
                                    .get(Integer.parseInt(sellContents[0]))
                                    .getQuantity()) {

                                investmentList.get(Integer.parseInt(sellContents[0]))
                                        .sell((double) (investmentList.get(Integer.parseInt(sellContents[0]))
                                                .getQuantity()
                                                - Integer.parseInt(sellContents[1]))
                                                / (double) investmentList.get(Integer.parseInt(sellContents[0]))
                                                        .getQuantity());
                                investmentList.get(Integer.parseInt(sellContents[0]))
                                        .updateQuantity(-Integer.parseInt(sellContents[1]));

                                display.append("Sold " + sellContents[1] + " at $" + sellContents[2]
                                        + " book value is now $"
                                        + investmentList.get(Integer.parseInt(sellContents[0])).getBookValue() + "\n");

                                if (investmentList.get(Integer.parseInt(sellContents[0])).getBookValue() == 0) {
                                    display.append("Book valued at $0, will be deleted..." + "\n");

                                    // updating hashmap and removing from list
                                    removeHash(Integer.parseInt(sellContents[0]),
                                            investmentList.get(Integer.parseInt(sellContents[0])).getName());
                                    investmentList.remove(Integer.parseInt(sellContents[0]));

                                }

                            } else {
                                display.append("Not valid input (quantity exceeds investment owned)\n");
                            }
                        }
                    } catch (Exception y) {
                        display.append("ERROR: Ensure all fields are correctly filled\n");
                    }
                } else {
                    display.append("ERROR: Ensure all fields are correctly filled\n");
                }

            }
        });

        frame.setVisible(true);
    }

    /**
     * Method is the update menu GUI that displays any investments and allows the
     * user to go through them and update the price
     */
    protected static void updateMenu() {
        index = 0;
        // fonts.
        Font f = new Font("Serif", Font.BOLD, 30);
        Font g = new Font("Serif", Font.BOLD, 15);
        Font e = new Font("Serif", Font.BOLD, 50);
        updatePanel = new JPanel();
        frame.add(updatePanel);
        updatePanel.setLayout(null);

        // panel name
        JLabel optionLabel = new JLabel("Update Menu");
        optionLabel.setBounds(10, 40, 400, 50);
        optionLabel.setFont(e);
        updatePanel.add(optionLabel);

        // messahe area
        JTextArea display = new JTextArea(100, 100);
        display.setEditable(false);
        display.setBounds(20, 600, 950, 350);
        JScrollPane displayScrollSide = new JScrollPane(display);
        displayScrollSide.setBounds(20, 600, 950, 350);
        displayScrollSide.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        updatePanel.add(displayScrollSide);

        // message label
        JLabel displayLabel = new JLabel("Messages");
        displayLabel.setFont(g);
        displayLabel.setBounds(20, 560, 300, 50);
        updatePanel.add(displayLabel);

        // commands label
        JLabel lbl = new JLabel("Select one of the commands");
        lbl.setFont(g);
        lbl.setBounds(400, 0, 200, 50);
        updatePanel.add(lbl);

        String[] choices = { "Buy", "Sell", "Update", "Get Gain", "Search", "Quit" };

        // command combobox and action listener
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setFont(g);
        cb.setBounds(460, 40, 70, 25);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selection = (String) combo.getSelectedItem();
                switch (selection) {
                    case "Buy":
                        frame.remove(updatePanel);
                        clearFrame(1);
                        break;
                    case "Sell":
                        frame.remove(updatePanel);
                        clearFrame(2);
                        break;
                    case "Update":
                        frame.remove(updatePanel);
                        clearFrame(3);
                        break;
                    case "Get Gain":
                        frame.remove(updatePanel);
                        clearFrame(4);
                        break;
                    case "Search":
                        frame.remove(updatePanel);
                        clearFrame(5);
                        break;
                    case "Quit":
                        clearFrame(6);
                        break;
                    default:
                        break;
                }

            }

        });
        updatePanel.add(cb);

        // symbol label
        JLabel symbolLabel = new JLabel("Symbol");
        symbolLabel.setFont(f);
        symbolLabel.setBounds(50, 100, 100, 250);
        updatePanel.add(symbolLabel);

        // symbol text box
        JTextField symbolText = new JTextField();
        symbolText.setBounds(300, 200, 300, 50);
        symbolText.setFont(f);
        symbolText.setEditable(false);
        updatePanel.add(symbolText);

        // name label
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(f);
        nameLabel.setBounds(50, 100, 150, 450);
        updatePanel.add(nameLabel);

        // name text box
        JTextField nameText = new JTextField();
        nameText.setBounds(300, 300, 300, 50);
        nameText.setFont(f);
        nameText.setEditable(false);
        updatePanel.add(nameText);

        // price label
        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(f);
        priceLabel.setBounds(50, 100, 150, 650);
        updatePanel.add(priceLabel);

        // price text box
        JTextField priceText = new JTextField();
        priceText.setBounds(300, 400, 300, 50);
        priceText.setFont(f);
        updatePanel.add(priceText);

        // sets original text boxes to have investment information
        if (investmentList.size() > 0) {
            symbolText.setText(investmentList.get(index).getSymbol());
            priceText.setText(Double.toString(investmentList.get(index).getPrice()));
            nameText.setText(investmentList.get(index).getName());
        }

        String[] priceUpdateContents = new String[1];

        // updating price if valid input
        priceText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = priceText.getText();
                try {
                    priceUpdateContents[0] = verifyPriceInput(command);
                } catch (Exception y) {
                }
                if (priceUpdateContents[0] == null) {
                    display.append("ERROR: must be number greater than 0\n");
                }

            }
        });

        // prev button
        JButton prev = new JButton("Prev");
        prev.setBounds(700, 100, 150, 60);
        prev.setFont(f);
        updatePanel.add(prev);

        // loops through list on each click going 'left'
        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (investmentList.size() > 0) {
                    if (command.equals("Prev")) {

                        if (index == 0) {
                            index = investmentList.size() - 1;
                        } else {
                            index--;
                        }
                        symbolText.setText(investmentList.get(index).getSymbol());
                        priceText.setText(Double.toString(investmentList.get(index).getPrice()));
                        nameText.setText(investmentList.get(index).getName());
                    }
                }

            }
        });

        // next button
        JButton next = new JButton("Next");
        next.setBounds(700, 300, 150, 60);
        next.setFont(f);
        updatePanel.add(next);

        // loops through list going 'right'
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Next")) {
                    if (investmentList.size() > 0) {
                        if (index == investmentList.size() - 1) {
                            index = 0;
                        } else {
                            index++;
                        }
                        symbolText.setText(investmentList.get(index).getSymbol());
                        priceText.setText(Double.toString(investmentList.get(index).getPrice()));
                        nameText.setText(investmentList.get(index).getName());
                    }
                }

            }
        });

        // save button
        JButton save = new JButton("Save");
        save.setBounds(700, 500, 150, 60);
        save.setFont(f);
        updatePanel.add(save);

        // if investment owned updates it to the current user set price
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                // do intput check here
                if (investmentList.size() > 0) {
                    try {
                        if (command.equals("Save")) {
                            investmentList.get(index).updatePrice(Double.parseDouble(priceUpdateContents[0]));
                            display.append("Updated investment " + investmentList.get(index).getName() + " to $"
                                    + investmentList.get(index).getPrice() + "\n");
                        }
                    } catch (Exception y) {
                        display.append("ERROR: Ensure all fields are correctly filled\n");
                    }
                }

                else {
                    display.append("ERROR: Buy something!\n");
                }

            }
        });

        frame.setVisible(true);
    }

    /**
     * Method is the gain menu GUI that displays the total gain, and the gain on
     * each investment
     */
    protected static void gainMenu() {
        // fOnTs
        Font f = new Font("Serif", Font.BOLD, 30);
        Font g = new Font("Serif", Font.BOLD, 15);
        Font e = new Font("Serif", Font.BOLD, 50);
        gainPanel = new JPanel();
        frame.add(gainPanel);
        gainPanel.setLayout(null);

        // display area
        JTextArea display = new JTextArea(100, 100);
        display.setEditable(false);
        display.setBounds(20, 300, 950, 650);
        JScrollPane displayScrollSide = new JScrollPane(display);
        displayScrollSide.setBounds(20, 300, 950, 650);
        displayScrollSide.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gainPanel.add(displayScrollSide);

        // panel name
        JLabel optionLabel = new JLabel("Gain Menu");
        optionLabel.setBounds(10, 40, 400, 50);
        optionLabel.setFont(e);
        gainPanel.add(optionLabel);

        // command label
        JLabel lbl = new JLabel("Select one of the commands");
        lbl.setFont(g);
        lbl.setBounds(400, 0, 200, 50);
        gainPanel.add(lbl);

        String[] choices = { "Buy", "Sell", "Update", "Get Gain", "Search", "Quit" };

        // command combobox
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setFont(g);
        cb.setBounds(460, 40, 70, 25);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selection = (String) combo.getSelectedItem();
                switch (selection) {
                    case "Buy":
                        frame.remove(gainPanel);
                        clearFrame(1);
                        break;
                    case "Sell":
                        frame.remove(gainPanel);
                        clearFrame(2);
                        break;
                    case "Update":
                        frame.remove(gainPanel);
                        clearFrame(3);
                        break;
                    case "Get Gain":
                        frame.remove(gainPanel);
                        clearFrame(4);
                        break;
                    case "Search":
                        frame.remove(gainPanel);
                        clearFrame(5);
                        break;
                    case "Quit":
                        clearFrame(6);
                        break;
                    default:
                        break;
                }

            }

        });
        gainPanel.add(cb);

        // display label
        JLabel displayLabel = new JLabel("Individual Gains");
        displayLabel.setFont(g);
        displayLabel.setBounds(20, 260, 300, 50);
        gainPanel.add(displayLabel);

        // gain textbox label
        JLabel gainLabel = new JLabel("Total Gain");
        gainLabel.setFont(f);
        gainLabel.setBounds(50, 100, 200, 250);
        gainPanel.add(gainLabel);

        // total gain textbox
        JTextField gainText = new JTextField();
        gainText.setBounds(300, 200, 300, 50);
        gainText.setFont(f);
        gainText.setEditable(false);
        gainPanel.add(gainText);

        DecimalFormat df = new DecimalFormat("0.00"); // making it look nice
        // only does calculations if there is at least one investment owned
        if (investmentList.size() > 0) {
            try {
                double gain = 0;
                for (int i = 0; i < investmentList.size(); i++) {
                    gain += investmentList.get(i).getGain();

                    display.append("Gain on investment '" + investmentList.get(i).getSymbol() + "' is $"
                            + investmentList.get(i).getGain() + "\n");
                }
                gainText.setText("" + Double.parseDouble(df.format(gain)));

            } catch (Exception y) {
                display.append("ERROR: Failed to get gain\n");
            }
        }

        else {
            display.append("ERROR: Buy something!\n");
        }

        frame.setVisible(true);
    }

    /**
     * Method is the search menu GUI that displays any investments the user searched
     * for
     */
    protected static void searchMenu() {
        // yeah i believe these are fonts
        Font f = new Font("Serif", Font.BOLD, 30);
        Font g = new Font("Serif", Font.BOLD, 15);
        Font e = new Font("Serif", Font.BOLD, 50);
        // initializing panel and adding to frame
        searchPanel = new JPanel();
        frame.add(searchPanel);
        searchPanel.setLayout(null);

        // message display area
        JTextArea display = new JTextArea(100, 100);
        display.setEditable(false);
        display.setBounds(20, 600, 950, 350);
        JScrollPane displayScrollSide = new JScrollPane(display);
        displayScrollSide.setBounds(20, 600, 950, 350);
        displayScrollSide.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        searchPanel.add(displayScrollSide);

        // display label
        JLabel displayLabel = new JLabel("Search Results");
        displayLabel.setFont(g);
        displayLabel.setBounds(20, 560, 300, 50);
        searchPanel.add(displayLabel);

        // panel label
        JLabel optionLabel = new JLabel("Search Menu");
        optionLabel.setBounds(10, 40, 400, 50);
        optionLabel.setFont(e);
        searchPanel.add(optionLabel);

        // command label
        JLabel lbl = new JLabel("Select one of the commands");
        lbl.setFont(g);
        lbl.setBounds(400, 0, 200, 50);
        searchPanel.add(lbl);

        String[] choices = { "Buy", "Sell", "Update", "Get Gain", "Search", "Quit" };

        // command combobox
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setFont(g);
        cb.setBounds(460, 40, 70, 25);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selection = (String) combo.getSelectedItem();
                switch (selection) {
                    case "Buy":
                        frame.remove(searchPanel);
                        clearFrame(1);
                        break;
                    case "Sell":
                        frame.remove(searchPanel);
                        clearFrame(2);
                        break;
                    case "Update":
                        frame.remove(searchPanel);
                        clearFrame(3);
                        break;
                    case "Get Gain":
                        frame.remove(searchPanel);
                        clearFrame(4);
                        break;
                    case "Search":
                        frame.remove(searchPanel);
                        clearFrame(5);
                        break;
                    case "Quit":
                        clearFrame(6);
                        break;
                    default:
                        break;
                }

            }

        });
        searchPanel.add(cb);

        // user input array, initialized to empty strings
        String[] searchContents = new String[4];
        for (int i = 0; i < searchContents.length; i++) {
            searchContents[i] = "";
        }

        // symbol label
        JLabel symbolLabel = new JLabel("Symbol");
        symbolLabel.setFont(f);
        symbolLabel.setBounds(50, 100, 100, 250);
        searchPanel.add(symbolLabel);

        // symbol text
        JTextField symbolText = new JTextField();
        symbolText.setBounds(300, 200, 300, 50);
        symbolText.setFont(f);
        searchPanel.add(symbolText);

        // getting user input to array
        symbolText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = symbolText.getText();

                searchContents[0] = command;

            }
        });

        // keyword textbox label
        JLabel keywordLabel = new JLabel("Name Keywords");
        keywordLabel.setFont(f);
        keywordLabel.setBounds(50, 100, 250, 450);
        searchPanel.add(keywordLabel);

        // keyword textbox
        JTextField keywordText = new JTextField();
        keywordText.setBounds(300, 300, 300, 50);
        keywordText.setFont(f);
        searchPanel.add(keywordText);

        // getting user input and adding to array
        keywordText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = keywordText.getText();

                searchContents[1] = command;

            }
        });

        // low price range text box label
        JLabel lowLabel = new JLabel("Low Price");
        lowLabel.setFont(f);
        lowLabel.setBounds(50, 100, 200, 650);
        searchPanel.add(lowLabel);

        // low price range text box
        JTextField lowText = new JTextField();
        lowText.setBounds(300, 400, 300, 50);
        lowText.setFont(f);
        searchPanel.add(lowText);

        // accepting blank input or number, otherwise error displayed
        lowText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = lowText.getText();
                if (command.isBlank() || command.isEmpty()) {
                    searchContents[2] = command;
                } else {
                    try {
                        Double.parseDouble(command);
                        searchContents[2] = command;
                    } catch (Exception f) {
                        display.append("ERROR: Enter number for price or leave blank (and press enter)\n");
                    }
                }

            }
        });

        // high price range text box label
        JLabel highLabel = new JLabel("High Price");
        highLabel.setFont(f);
        highLabel.setBounds(50, 100, 200, 850);
        searchPanel.add(highLabel);

        // high price range text box label
        JTextField highText = new JTextField();
        highText.setBounds(300, 500, 300, 50);
        highText.setFont(f);
        searchPanel.add(highText);

        // accepting blank input or number, otherwise error displayed
        highText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = highText.getText();
                // do intput check here
                if (command.isBlank() || command.isEmpty()) {
                    searchContents[3] = command;
                } else {
                    try {
                        Double.parseDouble(command);
                        searchContents[3] = command;
                    } catch (Exception f) {
                        display.append("ERROR: Enter number for price or leave blank (and press enter)\n");
                    }
                }

            }
        });

        // reset button
        JButton reset = new JButton("Reset");
        reset.setBounds(700, 200, 150, 60);
        reset.setFont(f);
        searchPanel.add(reset);

        // clears input array and text boxes
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Reset")) {
                    symbolText.setText("");
                    highText.setText("");
                    lowText.setText("");
                    keywordText.setText("");

                    for (int i = 0; i < searchContents.length; i++) {
                        searchContents[i] = "";
                    }
                }

            }
        });

        // search button
        JButton search = new JButton("Search");
        search.setBounds(700, 400, 150, 60);
        search.setFont(f);
        searchPanel.add(search);

        // setting array to lowercase and calling search function if user has at least
        // one investment
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                for (int i = 0; i < searchContents.length; i++) {
                    searchContents[i] = searchContents[i].toLowerCase();
                }
                if (investmentList.size() > 0) {
                    if (command.equals("Search")) {
                        display.append(searchInvestment(searchContents));
                    }
                } else {
                    display.append("ERROR: No investments owned\n");
                }

            }
        });

        frame.setVisible(true);
    }

    /**
     * Method clears the frame and repaints it with the new user specified panel
     * option
     * 
     * @param panelNum The panel that is to be displayed
     */
    protected static void clearFrame(int panelNum) {
        // clearing old panels and going to user specified panel
        frame.remove(startPanel);
        frame.validate();
        switch (panelNum) {
            case 1:
                buyMenu();
                break;
            case 2:
                sellMenu();
                break;
            case 3:
                updateMenu();
                break;
            case 4:
                gainMenu();
                break;
            case 5:
                searchMenu();
                break;
            case 6:
                // saving file to portfolio.txt and exiting
                fileSave("portfolio.txt");
                System.exit(1);
                break;
            default:
                break;

        }

    }

    /**
     * This is the main method
     * 
     * @param fileIn command line argument for file to load
     */
    public static void main(String[] fileIn) {

        // Checks if file is availiable and loads into list and hashmap
        if (fileIn.length > 0) {
            fileRead(fileIn[0]);

            for (int i = 0; i < investmentList.size(); i++) {
                fillHash(i, investmentList.get(i).getName());
            }

        }

        // creating GUI
        frame = new JFrame("ePortfolio");
        prepareGui();

    }

    /**
     * Method reads information from file into new investment (info seperated by
     * ""), then adds to arraylist investment
     * 
     * @param fileIn This is the user inputted file from command line
     */
    protected static void fileRead(String fileIn) {

        String type;
        String symbol;
        String name;
        String quantity;
        String price;
        String bookValue;

        try {
            File file = new File(fileIn);
            Scanner reader = new Scanner(file);

            // getting all nessesary file information and parsing it bewtween quotation
            // marks using sub strings
            while (reader.hasNextLine()) {
                type = reader.nextLine();
                type = type.substring(type.indexOf("\"") + 1);
                type = type.substring(0, type.indexOf("\""));

                symbol = reader.nextLine();
                symbol = symbol.substring(symbol.indexOf("\"") + 1);
                symbol = symbol.substring(0, symbol.indexOf("\""));

                name = reader.nextLine();
                name = name.substring(name.indexOf("\"") + 1);
                name = name.substring(0, name.indexOf("\""));

                quantity = reader.nextLine();
                quantity = quantity.substring(quantity.indexOf("\"") + 1);
                quantity = quantity.substring(0, quantity.indexOf("\""));

                price = reader.nextLine();
                price = price.substring(price.indexOf("\"") + 1);
                price = price.substring(0, price.indexOf("\""));

                bookValue = reader.nextLine();
                bookValue = bookValue.substring(bookValue.indexOf("\"") + 1);
                bookValue = bookValue.substring(0, bookValue.indexOf("\""));

                // taking away space between investments
                if (reader.hasNextLine()) {
                    reader.nextLine();
                }

                if (type.equals("stock")) {
                    Stock newStock = new Stock(symbol, name, Integer.parseInt(quantity), Double.parseDouble(price));
                    newStock.setBookValue(Double.parseDouble(bookValue));
                    investmentList.add(newStock);

                } else {
                    MutualFund newMutualFund = new MutualFund(symbol, name, Integer.parseInt(quantity),
                            Double.parseDouble(price));
                    newMutualFund.setBookValue(Double.parseDouble(bookValue));
                    investmentList.add(newMutualFund);
                }

            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file..");
        }
    }

    /**
     * Method adds elements to hashmap and creates new entries if no existing keys
     * 
     * @param index    This is the index in investmentList
     * 
     * @param keywords This is words from name of investment to add to list
     */
    protected static void fillHash(int index, String keywords) {
        List<String> keywordsList = Arrays.asList(keywords.toLowerCase().split(" "));

        for (int i = 0; i < keywordsList.size(); i++) {
            // makes new int list if keyword dosnt exist
            keywordHashmap.putIfAbsent(keywordsList.get(i), new ArrayList<Integer>());
            // adds index to hashmap for keyword
            keywordHashmap.get(keywordsList.get(i)).add(index);

        }
    }

    /**
     * Method saves portfolio to file (formatted)
     * 
     * @param fileIn This the is file that will be saved to
     * 
     */
    protected static void fileSave(String fileIn) {
        if (fileIn.isEmpty()) {
            fileIn = "portfolio.txt";
        }
        // trys to write to file
        try {
            PrintWriter fileWriter = new PrintWriter(fileIn);
            // going through list printing info
            for (int i = 0; i < investmentList.size(); i++) {
                fileWriter.write("type = \"" + investmentList.get(i).getType() + "\"\n");
                fileWriter.write("symbol = \"" + investmentList.get(i).getSymbol() + "\"\n");
                fileWriter.write("name = \"" + investmentList.get(i).getName() + "\"\n");
                fileWriter.write("quantity = \"" + investmentList.get(i).getQuantity() + "\"\n");
                fileWriter.write("price = \"" + investmentList.get(i).getPrice() + "\"\n");
                fileWriter.write("bookValue = \"" + investmentList.get(i).getBookValue() + "\"\n");

                // spacing between investments(except for last one)
                if (i != investmentList.size() - 1) {
                    fileWriter.write("\n");
                }

            }

            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to file...");
        }

    }

    /**
     * Method goes through list and returns index of stock, if not found -1
     * 
     * @param symbol
     * 
     * @return int This returns the index of existing symbol or -1 if not found
     */
    protected static int symbolExistence(String symbol) {

        for (int i = 0; i < investmentList.size(); i++) {
            if (symbol.equals(investmentList.get(i).getSymbol())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method gets and validates that user input is valid (qty must be >0)
     * 
     * @param investmentContentsQuantity This is the user inputed quantity
     * @return string This is the user input
     */
    protected static String verifyQuantityInput(String investmentContentsQuantity) {
        // validating input
        try {
            if (Integer.parseInt(investmentContentsQuantity) <= 0) {
                return null;
            }

            return investmentContentsQuantity;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Method gets and validates that user input is valid (price must be > 0)
     * 
     * @param investmentContentsPrice user entered input
     * @return null if not valid price, otherwise price returned
     */
    protected static String verifyPriceInput(String investmentContentsPrice) {
        try {
            if (Double.parseDouble(investmentContentsPrice) <= 0) {
                return null;
            }

            return investmentContentsPrice;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Method removes elements from hashmap and deletes entries if key with no
     * indexes and DOES NOT decrements higher indexes :(
     * 
     * @param index    This is the index of the element to remove
     * @param keywords This is the string of keywords to remove
     */
    protected static void removeHash(int index, String keywords) {
        List<String> keywordsList = Arrays.asList(keywords.toLowerCase().split(" "));

        // going through keywords removing the indexes from hashmap
        for (int i = 0; i < keywordsList.size(); i++) {
            // removing index from list
            keywordHashmap.get(keywordsList.get(i)).remove(Integer.valueOf(index));

            // removing entry if empty
            if (keywordHashmap.get(keywordsList.get(i)).isEmpty()) {
                keywordHashmap.remove(keywordsList.get(i));
            }
        }
    }

    /**
     * Method prompts user for input then searches through array lists looking for
     * matching symbols, matching keywords, and matching price ranges
     * 
     * @param searchContents This is the user inputed symbol, name keywords, low
     *                       price range, and high price range
     */
    protected static String searchInvestment(String[] searchContents) {
        boolean[] searchParam = { false, false, false };// what parameters the user is searching for
        boolean[] searchFoundConditions = { false, false, false };// conditions
        Object[] indexesSearch;// set of common keywords
        String returnForPrinting = new String(); // what will be displayed at the messages

        // checkign whether input was entered for search conditions and putting input to
        // lowercase
        for (int i = 0; i < 2; i++) {
            searchContents[i] = searchContents[i].toLowerCase(); // putting input to lowercase
            if (searchContents[i].isBlank() || searchContents[i].isEmpty()) {// parameter left blank
                searchParam[i] = false;
            } else {
                searchParam[i] = true;
            }
        }

        // checking whether there is any price range specified or if both low and high
        // prices are left blank
        if ((searchContents[2].isBlank() || searchContents[2].isEmpty())
                && (searchContents[3].isBlank() || searchContents[3].isEmpty())) {
            searchParam[2] = false;
        } else {
            searchParam[2] = true;
        }

        // if keywords are entered (hashmap search)
        if (searchParam[1]) {
            indexesSearch = hashSearchCheck(searchContents[1]).toArray();// getting indexes from hash search

            // no results from search
            if (indexesSearch.length == 0) {
                searchFoundConditions[1] = false;
            } else {
                searchFoundConditions[1] = true;
            }

            // checking other search parameters if keywords exist
            if (searchFoundConditions[1]) {
                for (int i = 0; i < indexesSearch.length; i++) {
                    if (searchParam[0]) {// checking if symbol matches
                        if (keywordCheck(investmentList.get((Integer) indexesSearch[i]).getSymbol().toLowerCase(),
                                searchContents[0])) {
                            searchFoundConditions[0] = true;
                        } else {
                            searchFoundConditions[0] = false;
                        }
                    } else {// if user entered blank for input
                        searchFoundConditions[0] = true;
                    }
                    if (searchParam[2]) {// checking if in price range
                        if (priceRange(investmentList.get((Integer) indexesSearch[i]).getPrice(), searchContents[2],
                                searchContents[3])) {
                            searchFoundConditions[2] = true;
                        } else {
                            searchFoundConditions[2] = false;
                        }

                    } else {// if user entered blank for input
                        searchFoundConditions[2] = true;
                    }

                    if (searchFoundConditions[0] && searchFoundConditions[1] && searchFoundConditions[2]) {// if all
                                                                                                           // conditions
                                                                                                           // are met
                                                                                                           // investment
                                                                                                           // displayed
                        returnForPrinting += (investmentList.get((Integer) indexesSearch[i]).getType() + ": "
                                + investmentList.get((Integer) indexesSearch[i]).getSymbol() + ", "
                                + investmentList.get((Integer) indexesSearch[i]).getName() + ", $"
                                + investmentList.get((Integer) indexesSearch[i]).getPrice() + ", "
                                + investmentList.get((Integer) indexesSearch[i]).getQuantity() + "\n");
                    }
                }
                if (returnForPrinting.isEmpty()) {
                    return ("Investment not found\n");
                }
                return returnForPrinting;
            }
            if (returnForPrinting.isEmpty()) {
                return ("Investment not found\n");
            }

        }

        else {// no keywords, sequential

            // searching stock list and all 3 conditions must yield true for each investment
            for (int i = 0; i < investmentList.size(); i++) {
                searchFoundConditions[1] = true;
                if (searchParam[0]) {// checking if symbol matches
                    if (investmentList.get(i).getSymbol().toLowerCase().equals(searchContents[0].toLowerCase())) {
                        searchFoundConditions[0] = true;
                    }
                } else {// if user entered blank for input
                    searchFoundConditions[0] = true;
                }
                if (searchParam[2]) {// checking if in price range
                    if (priceRange(investmentList.get(i).getPrice(), searchContents[2], searchContents[3])) {
                        searchFoundConditions[2] = true;
                    }

                } else {// if user entered blank for input
                    searchFoundConditions[2] = true;
                }

                if (searchFoundConditions[0] && searchFoundConditions[1] && searchFoundConditions[2]) {// if all
                                                                                                       // conditions
                                                                                                       // are met
                                                                                                       // investment
                                                                                                       // displayed
                    returnForPrinting += (investmentList.get(i).getType() + ": " + investmentList.get(i).getSymbol()
                            + ", "
                            + investmentList.get(i).getName()
                            + ", $" + investmentList.get(i).getPrice() + ", "
                            + investmentList.get(i).getQuantity() + "\n");
                    for (int b = 0; b < 3; b++) {
                        searchFoundConditions[b] = false;
                    }
                }
            }
            if (returnForPrinting.isEmpty()) {
                return ("Investment not found\n");
            }
            return returnForPrinting;
        }
        return ("ERROR: Could not perform search\n");
    }

    /**
     * Method searches hashmap for keywords and returns common indexes
     * 
     * @param keyword This is the keyword string used to search
     * @return HashSet<Integer> This is the set of any common indexes matched by
     *         hashset
     */
    protected static HashSet<Integer> hashSearchCheck(String keyword) {
        List<String> searchStringList = Arrays.asList(keyword.toLowerCase().split(" "));
        List<Integer[]> indexesToIntersect = new ArrayList<Integer[]>();
        HashSet<Integer> intersectionSet = new HashSet<>();

        // adding all values of key matches to list
        for (int j = 0; j < searchStringList.size(); j++) {
            if (keywordHashmap.containsKey(searchStringList.get(j))) {
                indexesToIntersect.add(keywordHashmap.get(searchStringList.get(j)).toArray(new Integer[0]));
            }
        }

        if (indexesToIntersect.size() > 0) {
            // making hashset to find intersections of arrays
            intersectionSet = new HashSet<>(Arrays.asList(indexesToIntersect.get(0)));
            // adding all common indexes to set
            for (int i = 0; i < indexesToIntersect.get(0).length; i++) {
                HashSet<Integer> set = new HashSet<>(Arrays.asList(indexesToIntersect.get(0)));
                intersectionSet.retainAll(set);
            }
        }
        return intersectionSet;
    }

    /**
     * Method creates lists for symbol and keyword words, returns true if symbol
     * list has all elements in keywords list
     * 
     * @param name    This is the name of investment by user
     * @param keyword This is the keyword string searching for
     * @return boolean This returns true if name list contains keyword list
     */
    protected static boolean keywordCheck(String name, String keyword) {
        List<String> inputStringList = Arrays.asList(name.toLowerCase().split(" "));
        List<String> wordsList = Arrays.asList(keyword.toLowerCase().split(" "));
        return inputStringList.containsAll(wordsList);
    }

    /**
     * Method checks if price is within price range
     * 
     * @param price     This is the price of investment
     * @param rangeLow  This the the low end price range of search
     * @param rangeHigh This the the high end price range of search
     * @return boolean This returns true if the price is within the price range
     */
    protected static boolean priceRange(double price, String rangeLow, String rangeHigh) {

        // - at start of range (less than number)
        if (rangeLow.isEmpty() || rangeLow.isBlank()) {
            if (price <= Double.parseDouble(rangeHigh)) {
                return true;
            } else {
                return false;
            }
        }

        // - at end of range (more than number)
        else if (rangeHigh.isEmpty() || rangeHigh.isBlank()) {
            if (price >= Double.parseDouble(rangeLow)) {
                return true;
            } else {
                return false;
            }
        }

        // checking inbetween price range
        else {
            if (price >= Double.parseDouble(rangeLow)) {
                if (price <= Double.parseDouble(rangeHigh)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

    }

}
