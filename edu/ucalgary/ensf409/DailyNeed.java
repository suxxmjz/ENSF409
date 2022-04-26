/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       1.2
 @since         1.0
 */

package edu.ucalgary.ensf409;
// Need of a family member
public class DailyNeed {
    private int id;
    private String clientType;
    private int wholeGrains;
    private int fruitVeggies;
    private int protein;
    private int other;
    private int calories;

    /**
     * Constructor for DailyNeed, this will initialize the required variables for this method
     * @param id id number of the client
     * @param clientType type of client (i.e. adult male, adult female, child over eight, or child under eight)
     * @param wholeGrains amount of whole grains needed per day
     * @param fruitVeggies amount of fruits and vegetables needed per day
     * @param protein amount of protein needed per day
     * @param other amount of other food items needed per day
     * @param calories number of calories needed for the family
     */
    public DailyNeed(int id,
            String clientType,
            int wholeGrains,
            int fruitVeggies,
            int protein,
            int other,
            int calories) {

        this.id = id;
        this.clientType = clientType;
        this.wholeGrains = wholeGrains;
        this.fruitVeggies = fruitVeggies;
        this.protein = protein;
        this.other = other;
        this.calories = calories;
    }
    
    /**
     * getter method for family id
     * @return id of client
     */
    public int getId() {
        return id;
    }
    /**
     * getter method for clientType
     * @return clinetType 
     */
    public String getClientType() {
        return clientType;
    }
    /**
     * getter method for whole grain
     * @return wholeGrains
     */
    public int getWholeGrain() {
        return wholeGrains;
    }

    /**
     * getter method for fruits and vegetables
     * @return fruitVeggies
     */

    public int getFruitVeggies() {
        return fruitVeggies;
    }

    /**
     * getter method for protein
     * @return protein
     */

    public int getProtein() {
        return protein;
    }

     /**
     * getter method for other food items
     * @return other
     */
    public int getOther() {
        return other;
    }

     /**
     * getter method for calorie amount for different food items
     * @return calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * toString method creates a new string builder to add all the follwing strings in it
     * @return sb value in string format using .toString()
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();   
        sb.append(id).append(" ");
        sb.append(clientType).append(" ");
        sb.append(wholeGrains).append(" ");
        sb.append(fruitVeggies).append(" ");
        sb.append(protein).append(" ");
        sb.append(other).append(" ");
        sb.append(calories).append(" ");
        return sb.toString();
    }
}
