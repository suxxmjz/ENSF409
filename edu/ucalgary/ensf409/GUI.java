/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       1.8
 @since         1.0
 */
package edu.ucalgary.ensf409;

// ENSF 409 W22 Group 50 final project 

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

// GUI contains the following
// * a place for the name of the employee to be entered
// * an input spinners so that the following information can be put in per family:
//      - number of adult females 
//      - number of adults males
//      - number of children over the age of 8
//      - number of children under the age of 8
// * a checkbox to mark if a family requires weekly service
// * a button to submit a family on the order form
// * a button to submit the whole order form with all the families entered included

// * a txt file will be created with the hamper orders for each of the families entered 

public class GUI {

    // all the vaiables needed to store information collected from the GUI and
    // to send it to other classes for the order form to bew created
    private static List<FamilyProfile> requestedFamilies = new ArrayList<>();
    static String employeeName = " ";
    static int families;
    static int adultFemales;
    static int adultMales;
    static int childrenOver8Yrs;
    static int childrenUnder8Yrs;
    static boolean weeklyServiceHampers;

    // text fields for employee name and to display the number of families submitted
    static JTextField employeeIn = new JTextField("               ");
    static JTextField numOfFams = new JTextField("  ");

    // spinners for user to enter the number of each family members
    static JSpinner AdultFemales = new JSpinner();
    static JSpinner AdultMales = new JSpinner();
    static JSpinner childrenOver8 = new JSpinner();
    static JSpinner childrenUnder8 = new JSpinner();

    // checkbox for if the family needs weekly service
    static JCheckBox checkbox = new JCheckBox("Weekly service required?");

    public static void main(String args[]) {

        // creates base frame for other items to go on top
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Foodbank hamper system");
            frame.setSize(1000, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // create multiple panels for all the elements that must go on the GUI
            JPanel buttonPanel = new JPanel();
            JPanel clientPanel = new JPanel();
            JPanel rightPanel = new JPanel();
            JPanel titlePanel = new JPanel();

            // all the buttons and labels needed for the GUI
            JButton submit = new JButton("Add family");
            JButton doneSubmit = new JButton("Submit final order");
            JLabel instructions = new JLabel("Please enter the required information to produce a hamper order form.");
            JLabel employeeLabel = new JLabel("Please enter employee name:");
            JLabel family1Females = new JLabel("Please enter the number of adult females in family:");
            JLabel familyMales = new JLabel("Please enter the number of adult males in family:");
            JLabel familyOver8 = new JLabel("Please enter the number of children over 8 in family");
            JLabel familyUnder8 = new JLabel("Please enter the number of children under 8 in family");
            JLabel numberOfFams = new JLabel("The number of families added: ");

            // Adding all the elements to their respective panels
            buttonPanel.add(submit);
            titlePanel.add(instructions);
            clientPanel.add(employeeLabel);
            clientPanel.add(employeeIn);
            rightPanel.add(family1Females);
            rightPanel.add(AdultFemales);
            rightPanel.add(familyMales);
            rightPanel.add(AdultMales);
            rightPanel.add(familyOver8);
            rightPanel.add(childrenOver8);
            rightPanel.add(familyUnder8);
            rightPanel.add(childrenUnder8);
            rightPanel.add(checkbox);
            buttonPanel.add(doneSubmit);
            buttonPanel.add(doneSubmit);
            buttonPanel.add(numberOfFams);
            buttonPanel.add(numOfFams);

            // create an action listener for the submit family button
            // so that when it is pressed, a family object will be created
            // based on the data entered for the family
            submit.addActionListener((e) -> {
                submitAction();
            });

            // create an action listener for the submit order button
            // when this is pressed all the families that were submmited will
            // be entered and an order form will be created to fufill the needs
            // of all the families on the order form
            doneSubmit.addActionListener((e) -> {
                submitFinalOrder();
            });

            // add borderLayouts to all the previously made panels
            frame.getContentPane().add(BorderLayout.WEST, clientPanel);
            frame.getContentPane().add(BorderLayout.PAGE_END, buttonPanel);
            frame.getContentPane().add(BorderLayout.NORTH, titlePanel);
            frame.getContentPane().add(BorderLayout.CENTER, rightPanel);

            // set frane visibility to true so that all elements are visible
            frame.setVisible(true);
        });

    }

    /**
     * Submits the final order with all the included families that were entered in
     * the GUI
     * Will create an order form with the employee name, and the list of requested
     * families
     * The order will be used in the HamperCreator and then the order form will be
     * printed to a text file
     */
    private static void submitFinalOrder() {
        if ("".equals(employeeName) || requestedFamilies.size() == 0) {
            JOptionPane.showMessageDialog(null, "Input data!");
            return;
        }

        Order order = new Order(employeeName, requestedFamilies);
        System.out.println(order);
        try {
            HamperCreator hamperCreator = new HamperCreator(order);
            hamperCreator.buildHamper();
            JOptionPane.showMessageDialog(null, order);
            OrderForm orderForm = new OrderForm(order);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // resetting families
        requestedFamilies = new ArrayList<>();
        numOfFams.setText("0");
    }

    // Gets all the values from the entered family values on the GUI
    // The values are used to create a new family profile, that
    // family profile is then added to the requestedFamilies list
    // once the family profiles is created the inpu values on the GUI are reset for
    // the
    // family composition values
    // throws an exception if any of the entered values are negative
    private static void submitAction() throws IllegalArgumentException {
        families += 1;
        employeeName = employeeIn.getText();
        adultFemales = (int) AdultFemales.getValue();
        adultMales = (int) AdultMales.getValue();
        childrenOver8Yrs = (int) childrenOver8.getValue();
        childrenUnder8Yrs = (int) childrenUnder8.getValue();
        if (checkbox.isSelected()) {
            weeklyServiceHampers = true;
        } else {
            weeklyServiceHampers = false;
        }
        if (adultFemales < 0 || adultMales < 0 || childrenOver8Yrs < 0 || childrenUnder8Yrs < 0) {
            throw new IllegalArgumentException();
        }
        if(families > 10){
            throw new IllegalArgumentException("Maximum families per order is 10");
        }


        // creates a family object and adds it to list with the collected values from
        // the GUI
        FamilyProfile family = new FamilyProfile(adultFemales, adultMales, childrenOver8Yrs, childrenUnder8Yrs,
                weeklyServiceHampers);
        requestedFamilies.add(family);

        // set the spinners to 0 so another family can be entered
        AdultFemales.setValue(0);
        AdultMales.setValue(0);
        childrenOver8.setValue(0);
        childrenUnder8.setValue(0);

        // increase the number of families entered by 1 to know how many are on the
        // order form
        numOfFams.setText(Integer.toString(families));
    }
}
