package edu.ucalgary.ensf409;

//Weekly need of all the family members
public class WeeklyNutrientProfile {
    private long wholeGrains;
    private long fruitVeggies;
    private long protein;
    private long other;
    private long calories;

    /**
     * constructor for WeeklyNutrientProfile to initalize the variables used in this class
     * @param wholeGrains amount of whole grains needed for a week
     * @param fruitVeggies amount of fruits and vegetables needed for a week
     * @param protein amount of protien needed for a week
     * @param other amount of other food items needed for a week
     * @param calories amount of calories needed for a week
     */
    public WeeklyNutrientProfile(long wholeGrains,
            long fruitVeggies,
            long protein,
            long other,
            long calories) {

        this.wholeGrains = wholeGrains;
        this.fruitVeggies = fruitVeggies;
        this.protein = protein;
        this.other = other;
        this.calories = calories;
    }

    /**
     * getter for whole grains per week
     * @return long data type for amount of whole grains per week
     */
    public long getWholeGrain() {
        return wholeGrains;
    }

     /**
     * getter for fruits and vegetables per week
     * @return long data type  for amount of fruits and vegetables per week
     */
    public long getFruitVeggies() {
        return fruitVeggies;
    }

     /**
     * getter for protein amount per week
     * @return long data type for amount of proteins per week
     */
    public long getProtein() {
        return protein;
    }

     /**
     * getter for other amount per week
     * @return long data type for amount of other food items per week
     */
    public long getOther() {
        return other;
    }

     /**
     * getter for calories amount per week
     * @return long data type for amount of calories per week
     */
    public long getCalories() {
        return calories;
    }
}
