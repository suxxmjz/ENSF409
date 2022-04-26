/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
           Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
           Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
           Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       1.2
 @since         1.0
 */

package edu.ucalgary.ensf409;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class OrderForm {

    private Order order;

    /**
     * constructor for OrderForm class 
     * creates order that is being printed into the output text file
     * calls outputText() to create the order
     * @param order object order is retrieved from Order class
     */
    public OrderForm(Order order) {
        this.order = order;
        outputText();
    }

    /** 
     * method outputText to print the hamper created into a .txt file
     * @catches FileNotFoundException when file can't be opened
     * @catches IOException when output file can't be written - error in the IO
     */
    public void outputText() {
        try {
            File output = new File("orderform.txt");  //create a file with the name orderform.txt
            FileWriter write = new FileWriter(output);         //write a new file 
            BufferedWriter buffer = new BufferedWriter(write); //create a new BufferWriter
            buffer.write(order.toString()); 
            buffer.flush();                                   //to restart a new .txt file everytime the algorithm is run
            buffer.close();                                   //to close the file
        } catch (FileNotFoundException ex) {
            System.out.print("Error: file couldn't be opened.");

        } catch (IOException ex) {
            System.out.print("Error: file couldn't be written.");
        }
    }
}
