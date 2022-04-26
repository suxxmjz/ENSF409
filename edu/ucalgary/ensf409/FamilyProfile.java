/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       1.3
 @since         1.0
 */
package edu.ucalgary.ensf409;

//Represent number of members and their weekly needs of a single family.
public class FamilyProfile {
    private int adultFemales;
    private int adultMales;
    private int childrenUnder8;
    private int childrenOver8;
    private boolean weeklyServiceHampers;
    private WeeklyNutrientProfile weeklyNutrientProfile;

    /**
     * Constructor for FamilyProfile to initialize variables used to create a profile for the family 
     * @param adultFemales number of adult females in a family
     * @param adultMales number of adult males in a family
     * @param childrenOver8 number of childer over eight in a family
     * @param childrenUnder8 umber of childer under eight in a family
     * @param weeklyServiceHampers boolean to ask whether the user requires weekly service due to mobility issues
     */

    public FamilyProfile(int adultFemales, int adultMales, int childrenOver8, int childrenUnder8,
            boolean weeklyServiceHampers) {
        this.adultFemales = adultFemales;
        this.adultMales = adultMales;
        this.childrenOver8 = childrenOver8;
        this.childrenUnder8 = childrenUnder8;
    }

    /**
     * getter method for number of adult females
     * @return an integer value for number of adult females
     */

    public int getAdultFemales() {
        return this.adultFemales;
    }

    /**
     * getter method for number of adult males
     * @return an integer value for number of adult males
     */

    public int getAdultMales() {
        return this.adultMales;
    }

     /**
     * getter method for number of children over eight
     * @return an integer value for number of children over 8
     */

    public int getChildrenOver8() {
        return this.childrenOver8;
    }

     /**
     * getter method for number of children under eight
     * @return an integer value for number of children under 8
     */

    public int getChildrenUnder8() {
        return this.childrenUnder8;
    }

     /**
     * getter method for weeklyServiceHampers 
     * @return an integer value for weeklyServiceHampers
     */

    public boolean getWeeklyServiceHampers() {
        return this.weeklyServiceHampers;
    }
    
    /**
     * getter method for weeklyNutrientProfile (i.e. how much of each food item does each family need in a week)
     * this is called from class WeeklyNutrientProfile
     * @return weeklyNutrientProfile
     */

    public WeeklyNutrientProfile getWeeklyNutrientProfile() {
        return this.weeklyNutrientProfile;
    }

    /**
     * setter method weeklyNutrientProfile
     * @param weeklyNutrientProfile will be called from WeeklyNutrientProfile class 
     * and is responsible for formalizing the amount of food and calories every family
     * needs in a week
     * @return weeklyNutrientProfile
     */

    public void setWeeklyNutrientProfile(WeeklyNutrientProfile weeklyNutrientProfile) {
        this.weeklyNutrientProfile = weeklyNutrientProfile;
    }

    /**
     * toString method to create a new string builder to add the following strings in it
     * @return sb variable formalized using REGEX
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (adultMales > 0)
            sb.append(" ").append(adultMales).append(" Adult Male,");
        if (adultFemales > 0)
            sb.append(" ").append(adultFemales).append(" Adult Female,");
        if (childrenUnder8 > 0)
            sb.append(" ").append(childrenUnder8).append(" Child under 8,");
        if (childrenOver8 > 0)
            sb.append(" ").append(childrenOver8).append(" Child over 8,");

        return sb.toString().replaceAll(",$", "");
    }
}
