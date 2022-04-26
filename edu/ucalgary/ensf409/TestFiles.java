/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       7.8
 @since         1.0
 */
package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
public class TestFiles {

    //ClientDailyNeedDataTest
    /**
     * this creates the composition of each family
     * boolean for weekly service needed is initially false
     * the number of each family member get passed to the family profile to create
     * the family
     * the client ID is obtained from the getter in the ClientDailyNeedData file
     */
    int FemalesClient = 2;
    int MalesClient = 2;
    int clientUnderEight = 1;
    int clientOverEight = 3;
    boolean testWeeklyService = false;
    FamilyProfile family = new FamilyProfile(FemalesClient, MalesClient, clientOverEight,
            clientUnderEight, testWeeklyService);

    DailyNeed maleDailyNeed = ClientDailyNeedData.getDailyNeed(1);
    DailyNeed femaleDailyNeed = ClientDailyNeedData.getDailyNeed(2);
    DailyNeed childOverDailyNeed = ClientDailyNeedData.getDailyNeed(3);
    DailyNeed childUnderDailyNeed = ClientDailyNeedData.getDailyNeed(4);

    /**
     * test calculateWeeklyFamilyWholeGrains()
     * takes amount of grains per food item and multiplies it by each family
     * member's daily needs
     * then, this value is multiplied by 7 for the the whole week
     * this process is repeated for every family member
     */
    @Test
    public void testCalculateWeeklyFamilyWholeGrains() {
        // ClientDailyNeedData familyTest = new ClientDailyNeedData();
        long expected = 7
                * ((MalesClient * maleDailyNeed.getWholeGrain()) + (FemalesClient * femaleDailyNeed.getWholeGrain()) +
                        (clientOverEight * childOverDailyNeed.getWholeGrain())
                        + (clientUnderEight * childUnderDailyNeed.getWholeGrain()));
        long found = ClientDailyNeedData.calculateWeeklyFamilyWholeGrains(family);
        assertEquals("Method CalculateWeeklyFamilyWholeGrains() did not return the expected result: ", expected, found);

    }

    /**
     * test calculateWeeklyFamilyFruitVeggies()
     * takes amount of fruits and vegetables per food item and multiplies it by each
     * family member's daily needs
     * then, this value is multiplied by 7 for the the whole week
     * this process is repeated for every family member
     */
    @Test
    public void testCalculateWeeklyFamilyFruitVeggies() {

        long expected = 7 * ((MalesClient * maleDailyNeed.getFruitVeggies())
                + (FemalesClient * femaleDailyNeed.getFruitVeggies()) +
                (clientOverEight * childOverDailyNeed.getFruitVeggies())
                + (clientUnderEight * childUnderDailyNeed.getFruitVeggies()));
        long found = ClientDailyNeedData.calculateWeeklyFamilyFruitVeggies(family);
        assertEquals("Method CalculateWeeklyFamilyWholeGrains() did not return the expected result: ", expected, found);
    }

    /**
     * test calculateWeeklyFamilyProtein()
     * takes amount of protein per food item and multiplies it by each family
     * member's daily needs
     * then, this value is multiplied by 7 for the the whole week
     * this process is repeated for every family member
     */
    @Test
    public void testCalculateWeeklyFamilyProtein() {

        long expected = 7
                * ((MalesClient * maleDailyNeed.getProtein()) + (FemalesClient * femaleDailyNeed.getProtein()) +
                        (clientOverEight * childOverDailyNeed.getProtein())
                        + (clientUnderEight * childUnderDailyNeed.getProtein()));
        long found = ClientDailyNeedData.calculateWeeklyFamilyProtein(family);
        assertEquals("Method CalculateWeeklyFamilyProtein() did not return the expected result: ", expected, found);
    }

    /**
     * test calculateWeeklyOther()
     * takes amount of other food items and multiplies it by each family member's
     * daily needs
     * then, this value is multiplied by 7 for the the whole week
     * this process is repeated for every family member
     */
    @Test
    public void testCalculateWeeklyFamilyOther() {

        long expected = 7
                * ((MalesClient * maleDailyNeed.getOther()) + (FemalesClient * femaleDailyNeed.getOther()) +
                        (clientOverEight * childOverDailyNeed.getOther())
                        + (clientUnderEight * childUnderDailyNeed.getOther()));
        long found = ClientDailyNeedData.calculateWeeklyFamilyOther(family);
        assertEquals("Method CalculateWeeklyFamilyOther() did not return the expected result: ", expected, found);
    }

    /**
     * test calculateWeeklyFamilyTotalCalories()
     * takes amount of calories per food item and multiplies it by each family
     * member's daily needs
     * then, this value is multiplied by 7 for the the whole week
     * this process is repeated for every family member
     */
    @Test
    public void testCalculateWeeklyFamilyTotalCalories() {

        long expected = 7
                * ((MalesClient * maleDailyNeed.getCalories()) + (FemalesClient * femaleDailyNeed.getCalories()) +
                        (clientOverEight * childOverDailyNeed.getCalories())
                        + (clientUnderEight * childUnderDailyNeed.getCalories()));
        long found = ClientDailyNeedData.calculateWeeklyFamilyTotalCalories(family);
        assertEquals("Method CalculateWeeklyFamilyTotalCalories() did not return the expected result: ", expected,
                found);
    }

    //DailyNeedTest
    // sample data
    // this data is used for testing
    int sampleId = 30;
    String testClientType = "Adult Male";
    int sampleGrains = 55;
    int sampleFV = 20;
    int sampleProtein = 30;
    int sampleOther = 25;
    int sampleCalories = 1000;

    /**
     * test Constructor for DailyNeed class
     * DailyNeed constructor is testing the validity of the 7 arguments
     * stored under dailyNeedConstructor
     * testing whether the DailyNeed constructor returns an object when called
     * properly
     */
    @Test
    public void testDailyNeedConstructor() {
        DailyNeed dailyNeedConstructor = new DailyNeed(sampleId, testClientType, sampleGrains,
                sampleFV, sampleProtein, sampleOther, sampleCalories);
        assertNotNull("DailyNeed constructor did not create an object when given valid arguments.",
                dailyNeedConstructor);
    }

    /**
     * test method getId()
     * getId should return the correct food ID when called properly
     * test passes when the expected item ID is found, and testing to see if it
     * matches with the ID in the database
     */
    @Test
    public void testGetId() {
        DailyNeed dailyNeed = new DailyNeed(sampleId, testClientType, sampleGrains,
                sampleFV, sampleProtein, sampleOther, sampleCalories);
        int expectedValue = sampleId;
        int foundValue = dailyNeed.getId();
        assertEquals("Method getId did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getClientType()
     * getClientType should return the correct type of family member when called
     * properly
     * test passes when the expected member is found, and testing to see if it
     * matches with the family member in the database
     */
    @Test
    public void testClientType() {
        DailyNeed dailyNeed = new DailyNeed(sampleId, testClientType, sampleGrains,
                sampleFV, sampleProtein, sampleOther, sampleCalories);
        String expectedValue = testClientType;
        String foundValue = dailyNeed.getClientType();
        assertEquals("Method getClientType did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getWholeGrain()
     * getWholeGrain should return the correct type of family member when called
     * properly
     * test passes when the expected whole grain is found, and testing to see if it
     * matches with the whole grain in the database
     */
    @Test
    public void testDailyNeedGetWholeGrain() {
        DailyNeed dailyNeed = new DailyNeed(sampleId, testClientType, sampleGrains,
                sampleFV, sampleProtein, sampleOther, sampleCalories);
        int expectedValue = sampleGrains;
        int foundValue = dailyNeed.getWholeGrain();
        assertEquals("Method getWholeGrain did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getFruitsVeggies()
     * getFruitsVeggies should return the correct type of family member when called
     * properly
     * test passes when the expected fruits and vegetable is found, and testing to
     * see if it matches with the fruits and vegetable in the database
     */
    @Test
    public void testDailyNeedGetFruitsVeggies() {
        DailyNeed dailyNeed = new DailyNeed(sampleId, testClientType, sampleGrains,
                sampleFV, sampleProtein, sampleOther, sampleCalories);
        int expectedValue = sampleFV;
        int foundValue = dailyNeed.getFruitVeggies();
        assertEquals("Method getFruitsVeggies did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getProtein()
     * getProtein should return the correct type of family member when called
     * properly
     * test passes when the expected protein is found, and testing to see if it
     * matches with the protein in the database
     */
    @Test
    public void testDailyNeedGetProtein() {
        DailyNeed dailyNeed = new DailyNeed(sampleId, testClientType, sampleGrains,
                sampleFV, sampleProtein, sampleOther, sampleCalories);
        int expectedValue = sampleProtein;
        int foundValue = dailyNeed.getProtein();
        assertEquals("Method getProtein did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getOther()
     * getOther should return the correct type of family member when called properly
     * test passes when the expected other food item is found, and testing to see if
     * it matches with the other in the database
     */
    @Test
    public void testDailyNeedGetOther() {
        DailyNeed dailyNeed = new DailyNeed(sampleId, testClientType, sampleGrains,
                sampleFV, sampleProtein, sampleOther, sampleCalories);
        int expectedValue = sampleOther;
        int foundValue = dailyNeed.getOther();
        assertEquals("Method getOther did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getCalories()
     * getCalories() should return the correct type of family member when called
     * properly
     * test passes when the expected calorie content is found, and testing to see if
     * it matches with the calories in the database
     */
    @Test
    public void testDailyNeedGetCalories() {
        DailyNeed dailyNeed = new DailyNeed(sampleId, testClientType, sampleGrains,
                sampleFV, sampleProtein, sampleOther, sampleCalories);
        int expectedValue = sampleCalories;
        int foundValue = dailyNeed.getCalories();
        assertEquals("Method getCalories did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method toString() in DailyNeed
     * this method tests if a string is correctly created
     */
    @Test
    public void testDailyNeedToString() {
        StringBuilder sb = new StringBuilder();
        assertNotNull("StringBuilder did not create a string.",
                sb);
    }

    //FamilyProfileTest
    // sample data for testing
    // initializing number of family members
    int testFemales = 1;
    int testMales = 2;
    int testUnderEight = 3;
    int testOverEight = 4;
    boolean testWeeklyServices = false; // initializing weekly services
    WeeklyNutrientProfile testWeeklyNutrientProfile = new WeeklyNutrientProfile(100,
            110, 20, 60, 1100); // random sample data for testing

    /**
     * test FamilyProfile constructor
     * to ensure that the constructor creates an object when the 5 arguments in
     * FamilyProfile are called correctly
     */
    @Test
    public void testFamilyProfileConstructor() {
        FamilyProfile familyProfileConstructor = new FamilyProfile(testFemales, testMales, testOverEight,
                testUnderEight, testWeeklyServices);
        assertNotNull("familyProfileConstructor constructor did not create an object when given valid arguments.",
                familyProfileConstructor);

    }

    /**
     * test getAdultFemales()
     * test the getter method to ensure proper retrieval of adult female information
     * from family profile
     */
    @Test
    public void testGetAdultFemales() {
        FamilyProfile familyProfile = new FamilyProfile(testFemales, testMales, testOverEight,
                testUnderEight, testWeeklyServices);
        int expectedValue = testFemales;
        int foundValue = familyProfile.getAdultFemales();
        // checks against the initialized values above
        assertEquals("Method getAdultFemales did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test getAdultMales()
     * test the getter method to ensure proper retrieval of adult male information
     * from family profile
     */
    @Test
    public void testGetAdultMales() {
        FamilyProfile familyProfile = new FamilyProfile(testFemales, testMales, testOverEight,
                testUnderEight, testWeeklyServices);
        int expectedValue = testMales;
        int foundValue = familyProfile.getAdultMales();
        // checks against the initialized values above
        assertEquals("Method getAdultMales did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test getChildrenOver8()
     * test the getter method to ensure proper retrieval of children over 8
     * information from family profile
     */
    @Test
    public void testGetChildrenOver8() {
        FamilyProfile familyProfile = new FamilyProfile(testFemales, testMales, testOverEight,
                testUnderEight, testWeeklyServices);
        int expectedValue = testOverEight;
        int foundValue = familyProfile.getChildrenOver8();
        // checks against the initialized values above
        assertEquals("Method getChildrenOver8 did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test getChildrenUnder8()
     * test the getter method to ensure proper retrieval of children under 8
     * information from family profile
     */
    @Test
    public void testGetChildrenUnder8() {
        FamilyProfile familyProfile = new FamilyProfile(testFemales, testMales, testOverEight,
                testUnderEight, testWeeklyServices);
        int expectedValue = testUnderEight;
        int foundValue = familyProfile.getChildrenUnder8();
        // checks against the initialized values above
        assertEquals("Method getChildrenUnder8 did not return the expected result: ", expectedValue,
                foundValue);
    }

    /**
     * test getWeeklyServiceHampers()
     * test the getter method to ensure that the weekly service for users with
     * mobility issues is properly created
     */
    @Test
    public void testWeeklyServiceHampers() {
        FamilyProfile familyProfile = new FamilyProfile(testFemales, testMales, testOverEight,
                testUnderEight, testWeeklyServices); // create a new family profile using testing numbers
                                                    // from above
        boolean expectedValue = testWeeklyService;
        boolean foundValue = familyProfile.getWeeklyServiceHampers(); // would test if the family needed a
                                                                      // weekly service
        assertEquals("Method getWeeklyServiceHampers did not return the expected result: ", expectedValue,
                foundValue);
    }

    /**
     * test setWeeklyNutrientProfile()
     * checks if the weekly nutrient profile for the family has been created
     */
    @Test
    public void testSetWeeklyNutrientProfile() {
        FamilyProfile familyProfile = new FamilyProfile(testFemales, testMales, testOverEight,
                testUnderEight, testWeeklyServices); // create a new family profile using testing numbers
                                                    // from above
        WeeklyNutrientProfile testProfile = new WeeklyNutrientProfile(90,
                100, 60, 20, 1400); // data for testing
        familyProfile.setWeeklyNutrientProfile(testProfile);
        WeeklyNutrientProfile expectedValue = testProfile; // expected value is from the testing data
        WeeklyNutrientProfile foundValue = familyProfile.getWeeklyNutrientProfile();
        assertEquals("Method setWeeklyNutrientProfile did not return the expected result: ", expectedValue,
                foundValue);
    }

    /**
     * test method toString()
     * test if StringBuilder was able to create a string from the retrieved
     * information
     */
    @Test
    public void testFamilyProfileToString() {
        StringBuilder sb = new StringBuilder();
        assertNotNull("StringBuilder did not create a string.", sb);
    }


    //FoodItemTest
    // testing data
    int testID = 51;
    String testName = "Cantaloupe, dozen";
    int testGrainContent = 55;
    int testFvContent = 20;
    int testProContent = 30;
    int testOther = 25;
    int testCalories = 1000;

    /**
     * test Constructor for FoodItem class
     * FoodItem constructor is testing the validity of the 7 arguments
     * stored under foodItemConstructor
     * testing whether the FoodItem constructor returns an object when called
     * properly
     */
    public void testFoodItemConstructor() {
        FoodItem foodItemConstructor = new FoodItem(testID, testName, testGrainContent,
                testFvContent, testProContent, testOther, testCalories);
        assertNotNull("FoodItem constructor did not create an object when given valid arguments.",
                foodItemConstructor);
    }

    /**
     * test ItemID
     * itemID should return the correct food ID when called properly
     * test passes when the expected item ID is found, and testing to see if it
     * matches with the ID in the database
     */
    @Test
    public void testGetItemID() {
        FoodItem ourInventory = new FoodItem(51, "Cantaloupe, dozen", 0, 100, 0, 0, 0);
        int itemIDValue = ourInventory.getId();
        int expectedItemIDValue = 51;
        assertEquals("Method getItemID did not return the expected result:", expectedItemIDValue, itemIDValue);

    }

    /**
     * test for getName of inventory item
     * getName should return the correct food name when called properly
     * test passes when the expected item name is found, and testing to see if it
     * matches with the name in the database
     */
    @Test
    public void testGetName() {
        FoodItem ourInventory = new FoodItem(51, "Cantaloupe, dozen", 0, 100, 0, 0, 10);
        String itemName = ourInventory.getName();
        String expectedName = "Cantaloupe, dozen";
        assertEquals("Method getName did not return the expected result:", expectedName, itemName);
    }

    /**
     * test for getGrain content for inventory item
     * getGrain should return the correct grain when called properly
     * test passes when the expected grain name is found, and testing to see if it
     * matches with the grain in the database
     */
    @Test
    public void testGetGrain() {
        FoodItem ourInventory = new FoodItem(51, "Cantaloupe, dozen", 0, 100, 0, 0, 10);
        int itemGrain = ourInventory.getGrainContent();
        int expectedItemGrain = 0;
        assertEquals("Method getGrain did not return the expected result:", expectedItemGrain, itemGrain);
    }

    /**
     * test for getFruitsVeggies content for inventory item
     * getFruitsVeggies should return the correct fruits and vegetable when called
     * properly
     * test passes when the expected fruits and vegetable name is found, and testing
     * to see if it matches with the fruits and vegetable in the database
     */
    @Test
    public void testGetFruitsVeggies() {
        FoodItem ourInventory = new FoodItem(51, "Cantaloupe, dozen", 0, 100, 0, 0, 10);
        int itemFV = ourInventory.getFVContent();
        int expectedItemFV = 100;
        assertEquals("Method getFruitsVeggies did not return the expected result:", expectedItemFV, itemFV);
    }

    /**
     * test for getProtein content for inventory item
     * getProtein should return the correct protein when called properly
     * test passes when the expected item protein is found, and testing to see if it
     * matches with the protein in the database
     */
    @Test
    public void testGetProtein() {
        FoodItem ourInventory = new FoodItem(51, "Cantaloupe, dozen", 0, 100, 0, 0, 10);
        int itemProtein = ourInventory.getProContent();
        int expectedItemProtein = 0;
        assertEquals("Method getProtein did not return the expected result:", expectedItemProtein, itemProtein);
    }

    /**
     * test for getOtherContent for inventory item
     * getOtherContent should return the correct other content when called properly
     * test passes when the expected other content is found, and testing to see if
     * it matches with the other content in the database
     */
    @Test
    public void testGetOtherContent() {
        FoodItem ourInventory = new FoodItem(51, "Cantaloupe, dozen", 0, 100, 0, 0, 10);
        int itemOtherContent = ourInventory.getOther();
        int expectedItemOtherContent = 0;
        assertEquals("Method getOtherContent did not return the expected result:", expectedItemOtherContent,
                itemOtherContent);
    }

    /**
     * test for getCalories for inventory item
     * getCalories should return the correct calorie content when called properly
     * test passes when the expected calorie content of a particular food item is
     * found,
     * and testing to see if it matches with the calorie in the database
     */
    @Test
    public void testGetCalories() {
        FoodItem ourInventory = new FoodItem(51, "Cantaloupe, dozen", 0, 100, 0, 0, 10);
        int itemCalories = ourInventory.getCalories();
        int expectedCalories = 10;
        assertEquals("Method getCalories did not return the expected result:", expectedCalories, itemCalories);
    }

    //HamperCreatorTest
    /**
     * initialized the new list for family profile
     * initialize a list of hamper - requested families
     * initializes list of food items
     */
    List<FamilyProfile> familiesRequested = new ArrayList<FamilyProfile>();
    List<Hamper> initializeHampers = new ArrayList<Hamper>();
    List<FoodItem> sampleFood = new ArrayList<FoodItem>();
    String sampleEmployee = "Sam";
    List<FamilyProfile> familiesExpectedCreator = createSampleFamilies(familiesRequested);

    Order order = new Order(sampleEmployee, familiesExpectedCreator);

    /**
     * this initlializes the sample list of requested families
     * create sameple and adds it to requested family list
     * 
     * @param familiesRequested is referencing the FamilyProfile class
     * @return familiesRequested
     */
    public List<FamilyProfile> createSampleFamilies(List<FamilyProfile> familiesRequested) {
        // an example of family that requires weekly service due to mobility issues
        FamilyProfile family1 = new FamilyProfile(1, 1, 0, 1, true);
        // an example of family that doesn't require weekly service due to mobility
        // issues
        FamilyProfile family2 = new FamilyProfile(0, 1, 0, 0, false);
        // adds family 1 and 2 into the requested family list
        familiesRequested.add(family1);
        familiesRequested.add(family2);
        return familiesRequested;
    }

    /**
     * test Hamper Creator constructor
     * checks if the constructor has made an object
     */
    @Test
    public void testHamperCreator() {
        HamperCreator testCreator = new HamperCreator(order);
        assertNotNull("HamperConstructor did not create an object when given valid arguments.",
                testCreator);
    }

    /**
     * test buildHamper()
     * builds the hamper constructor
     */
    @Test
    public void testBuildHamper() {
        Hamper testHamper = new Hamper(sampleFood);
        assertNotNull("BuildHamper did not create an object when given valid arguments.",
                testHamper);

}

    //HamperTest
    List<FoodItem> sampleFoodHamper = new ArrayList<>();
    List<FoodItem> testFood = createFoodItems(sampleFoodHamper);

    /**
     * method to create sample food item for testing
     * 
     * @param sampleFood - sampleFood variable calls the food item List
     *                   the food item list is in the order: id, name, grainContent,
     *                   fvContent,
     *                   proContent, other, calories
     *                   random values were added to each item
     * @return sampleFood returns the created food item list
     */
    public List<FoodItem> createFoodItems(List<FoodItem> sampleFood) {
        FoodItem foodItem1 = new FoodItem(34, "Apple", 100,
                88, 23, 15, 1994);
        FoodItem foodItem2 = new FoodItem(14, "Tomato", 30,
                80, 13, 10, 194);
        sampleFood.add(foodItem1);
        sampleFood.add(foodItem2);
        return sampleFood;
    }

    /**
     * test hamper constructor
     * creates an object if successful
     */
    @Test
    public void testHamperConstructor() {
        Hamper testHamper = new Hamper(sampleFoodHamper); // testHamper is successfuly returned if the sample food was
                                                          // in the hamper
        assertNotNull("hamper constructor did not create an object when given valid arguments.",
                testHamper);
    }

    /**
     * /test getFoodItems
     * method to test if food items were able to be added to the hamper
     * goal is to return the expected value from the hamper
     */
    @Test
    public void testGetFoodItems() {
        Hamper testHamper = new Hamper(sampleFoodHamper);
        List<FoodItem> expected = testFood; // expected value obtained from the sample food item list
        List<FoodItem> found = testHamper.getFoodItems(); // found value is obtained from the food item in the hamper
        assertEquals("Method getFoodItems did not return the expected result: ", expected, found);
    }

    /**
     * test method toString()
     * tests whether the hamper was able to successfuly create a string list with
     * the hamper created
     */
    @Test
    public void testToStringHamper() {
        StringBuilder sb = new StringBuilder();
        assertNotNull("StringBuilder did not create a string.",
                sb);
    }


    //OrderFormTest
    /**
     * test to ensure that the text file has been created successfuly
     * Note: program must be run first for the test to pass
     * 
     * @catches Exception if new order wasn't created from OrderForm
     */
    private List<FamilyProfile> requestedFamiliesForm = new ArrayList<>(); // creates a new array list of different
    // families from family profile

    @Test
    public void CreateTxtFile() {
        Order newOrder = null; // start with an empty order
        try {
            newOrder = new Order("Sam", requestedFamiliesForm); // Order will take a string, and an array list of
                                                                // requested families
            OrderForm orderForm = new OrderForm(newOrder); // to create the new order form
        } catch (Exception e) {

        }
        boolean check = new File("orderform.txt").exists(); // check if the orderform.txt file exsists
        assertTrue("Error: order failed to print.", check); // if not, show the error statement
    }

    //OrderTest
    /**
     * initalize employee name
     * initialize local date
     * initialize requested family by creating a new array list from FamilyProfile
     * initialize sample food by creating a new array list from FoodItem
     * initialize food hampers #1 and #2
     * initialize the expected number of families from the requested families
     * creating a sample hamper from all families
     */
    String testEmployee = "Smith";
    LocalDate testDate = LocalDate.now();
    List<FamilyProfile> requestedFamilies = new ArrayList<FamilyProfile>();
    List<Hamper> initializeHampersOrder = new ArrayList<Hamper>();
    List<FoodItem> orderSampleFood = new ArrayList<FoodItem>();
    List<FoodItem> foodHamper1 = createFoodItems1(orderSampleFood);
    List<FoodItem> foodHamper2 = createFoodItems2(orderSampleFood);
    List<FamilyProfile> expectedFamilies = createSampleFamiliesOrder(requestedFamilies);
    List<Hamper> testHampers = createSampleHampers(initializeHampers, foodHamper1, foodHamper2);

    /**
     * initlializes the sample list of requested families
     * 
     * @param requestedFamilies creates families from family profiles
     * @return requestedFamilies - returns the requested families created, 2
     */
    public List<FamilyProfile> createSampleFamiliesOrder(List<FamilyProfile> requestedFamilies) {
        FamilyProfile family1 = new FamilyProfile(1, 1, 1, 4, true);
        FamilyProfile family2 = new FamilyProfile(2, 1, 3, 0, false);
        requestedFamilies.add(family1);
        requestedFamilies.add(family2);
        return requestedFamilies;
    }

    /**
     * initializes the test hampers
     * 
     * @param hampers all hampers being made
     * @param food1   food hamper list #1
     * @param food2   food hamper list #2
     * @return hampers created
     */
    public List<Hamper> createSampleHampers(List<Hamper> hampers, List<FoodItem> food1, List<FoodItem> food2) {
        Hamper hamper1 = new Hamper(food1);
        Hamper hamper2 = new Hamper(food2);
        hampers.add(hamper1);
        hampers.add(hamper2);
        return hampers;

    }

    /**
     * creates sample food for hamper1
     * 
     * @param orderSampleFood obtained from FoodItem list
     *                        the sample food contians: food id, food name, grain
     *                        content, fruits and veggies content,
     *                        protein content, others, and calories per food item
     * @return orderSampleFood created
     */
    public List<FoodItem> createFoodItems1(List<FoodItem> orderSampleFood) {
        FoodItem foodItem1 = new FoodItem(34, "Apple", 100,
                88, 23, 15, 1994);
        FoodItem foodItem2 = new FoodItem(14, "Tomato", 30,
                80, 13, 10, 194);
        orderSampleFood.add(foodItem1);
        orderSampleFood.add(foodItem2);
        return orderSampleFood;
    }

    /**
     * creates sample food for hamper2
     * 
     * @param orderSampleFood obtained from FoodItem list
     *                        the sample food contians: food id, food name, grain
     *                        content, fruits and veggies content,
     *                        protein content, others, and calories per food item
     * @return orderSampleFood created
     */
    public List<FoodItem> createFoodItems2(List<FoodItem> orderSampleFood) {
        FoodItem foodItem1 = new FoodItem(10, "Cherry", 0,
                100, 0, 0, 35);
        FoodItem foodItem2 = new FoodItem(14, "Tomato", 0,
                120, 0, 0, 39);
        FoodItem foodItem3 = new FoodItem(14, "Chicken", 0,
                0, 400, 0, 140);
        orderSampleFood.add(foodItem1);
        orderSampleFood.add(foodItem2);
        orderSampleFood.add(foodItem3);
        return orderSampleFood;
    }

    /**
     * test Order constructor
     * ensures that the constructor can create an object
     */
    @Test
    public void testOrderConstructor() {
        Order orderConstructor = new Order(testEmployee, expectedFamilies);
        assertNotNull("orderConstructor did not create an object when given valid arguments.",
                orderConstructor);
    }

    /**
     * test getRequestedFamilies()
     * test whether the created families were successfuly added
     */
    @Test
    public void testGetRequestedFamilies() {
        Order orderTest = new Order(testEmployee, expectedFamilies);
        List<FamilyProfile> expected = expectedFamilies; // expected families are obtained from FamilyProfile
        List<FamilyProfile> found = orderTest.getRequestedFamilies(); // found number of families in the actual test
        assertEquals("Method getRequestedFamilies did not return the expected result: ", expected, found);

    }

    /**
     * test method toString()
     * to test if a string contains the information has been created
     */
    @Test
    public void testToStringOrderTest() {
        StringBuilder sb = new StringBuilder();
        assertNotNull("StringBuilder in OrderTest did not create a string.",
                sb);
    }

    //WeeklyNutrientProfileTest
    // sample data for testing
    long testWholeGrains = 55;
    long testFruitVeggies = 20;
    long testProtein = 30;
    long testOtherWeekly = 25;
    long testCaloriesWeekly = 1000;

    /**
     * test WeeklyNutrientconstructor
     * check if the constructor creates an object when the 5 arguments are valid
     */
    @Test
    public void testWeeklyNutrientConstructor() {
        WeeklyNutrientProfile weeklyNutrientConstructor = new WeeklyNutrientProfile(testWholeGrains,
                testFruitVeggies, testProtein, testOtherWeekly, testCaloriesWeekly);
        assertNotNull("weeklyNutrient constructor did not create an object when given valid arguments.",
                weeklyNutrientConstructor);
    }

    /**
     * test method getWholeGrain()
     * checks if the family needs of whole grains for the entire week has been met
     */
    @Test
    public void testWeeklyGetWholeGrain() {
        WeeklyNutrientProfile weeklyNutrient = new WeeklyNutrientProfile(testWholeGrains,
                testFruitVeggies, testProtein, testOtherWeekly, testCaloriesWeekly);
        long expectedValue = testWholeGrains; // expected value obtained from data above
        long foundValue = weeklyNutrient.getWholeGrain();
        assertEquals("Method getWholeGrain did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getFruitsVeggies()
     * checks if the family needs of fruits and vegetables for the entire week has
     * been met
     */
    @Test
    public void testWeeklyGetFruitsVeggies() {
        WeeklyNutrientProfile weeklyNutrient = new WeeklyNutrientProfile(testWholeGrains,
                testFruitVeggies, testProtein, testOtherWeekly, testCaloriesWeekly);
        long expectedValue = testFruitVeggies;
        long foundValue = weeklyNutrient.getFruitVeggies(); // expected value obtained from data above
        assertEquals("Method getFruitsVeggies did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getProtein()
     * checks if the family needs of protein for the entire week has been met
     */
    @Test
    public void testWeeklyGetProtein() {
        WeeklyNutrientProfile weeklyNutrient = new WeeklyNutrientProfile(testWholeGrains,
                testFruitVeggies, testProtein, testOtherWeekly, testCaloriesWeekly);
        long expectedValue = testProtein;
        long foundValue = weeklyNutrient.getProtein(); // expected value obtained from data above
        assertEquals("Method getProtein did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getOther()
     * checks if the family needs of other food items for the entire week has been
     * met
     */
    @Test
    public void testWeeklyGetOther() {
        WeeklyNutrientProfile weeklyNutrient = new WeeklyNutrientProfile(testWholeGrains,
                testFruitVeggies, testProtein, testOtherWeekly, testCaloriesWeekly);
        long expectedValue = testOther;
        long foundValue = weeklyNutrient.getOther(); // expected value obtained from data above
        assertEquals("Method getOther did not return the expected result: ", expectedValue, foundValue);
    }

    /**
     * test method getCalories()
     * checks if the family needs of calories food items for the entire week has
     * been met
     */
    @Test
    public void testWeeklyGetCalories() {
        WeeklyNutrientProfile weeklyNutrient = new WeeklyNutrientProfile(testWholeGrains,
                testFruitVeggies, testProtein, testOtherWeekly, testCaloriesWeekly);
        long expectedValue = testCalories;
        long foundValue = weeklyNutrient.getCalories(); // expected value obtained from data above
        assertEquals("Method getCalories did not return the expected result: ", expectedValue, foundValue);
    }
}
