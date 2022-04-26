/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       1.8
 @since         1.0
 */
package edu.ucalgary.ensf409;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private String employeeName;
    private LocalDate date;

    private List<FamilyProfile> requestedFamilies;
    private List<Hamper> createdHampers;

    /**
     * Constructor for Order class, creates the necessary variables to be used
     * @param employeeName create a string with employee/volunteer names
     * @param requestedFamilies creates a list interface of requested family, obtained from Family Profile class
     */
    public Order(String employeeName, List<FamilyProfile> requestedFamilies) {
        this.employeeName = employeeName;
        this.date = LocalDate.now();       //to add the locat date of the machine into the created hamper
        this.requestedFamilies = requestedFamilies;
        this.createdHampers = new ArrayList<>();    //create new hampers and add them to the array list
    }

    /**
     * getter method for RequestedFamilies
     * @return string with list of RequestedFamilies
     */
    public List<FamilyProfile> getRequestedFamilies() {
        return requestedFamilies;
    }

    /**
     * getter method for createdHampers
     * @return createdHampers
     */
    public List<Hamper> getCreatedHampers() {
        return createdHampers;
    }

    /**
     * setter method for createdHampers
     * @param createdHampers - createdHampers variable is using the ordered list
     * obtained from Hamper class
     * @return createdHampers
     */
    public void setCreatedHamper(List<Hamper> createdHampers) {
        this.createdHampers = createdHampers;
    }

    /**
     * toString method to create the order to be sent to OrderForm class for printing
     * @return sb as a string object
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();     //create a new string builder to add the order in it
        sb.append("Name: ").append(employeeName);
        sb.append("\nDate: ").append(date);

        sb.append("\n\nOriginal Request\n");
        for (int i = 0; i < requestedFamilies.size(); i++) {      //for loop to check if number of requested families is less than i, add a new hamper 
            sb.append("Hamper ").append(i + 1).append(":");
            sb.append(requestedFamilies.get(i)).append("\n");
        }

        sb.append("\n");
        for (int i = 0; i < createdHampers.size(); i++) {     //for loop to check if number of requested families is less than i, add a items in hamper 
            sb.append("\nHamper ").append(i + 1).append(" Items\n");
            sb.append(createdHampers.get(i)).append("\n");
        }

        return sb.toString();
    }

}
