/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       2.5
 @since         1.0
 */
package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class HamperCreator {
    private Order order;

    /**
     * Constructor for HamperCreator, sets the order, and sums all the total
     * nutrients seperatly to compare the values with the inventory to check for
     * shortage exception.
     * 
     * @param order is an object of type Order
     * @throws RuntimeException if there is not enough stock
     */
    public HamperCreator(Order order) {
        List<FamilyProfile> familyProfiles = order.getRequestedFamilies();
        for (FamilyProfile familyProfile : familyProfiles) {
            ClientDailyNeedData.calculateWeeklyFamilyNeeds(familyProfile); // calculte weekly family need and sets it to
                                                                           // family profile
        }

        long totalGrains = familyProfiles.stream().map(f -> f.getWeeklyNutrientProfile().getWholeGrain()).reduce(0L,
                Long::sum); // Sum of total grain of all families.
        long totalFVContent = familyProfiles.stream().map(f -> f.getWeeklyNutrientProfile().getFruitVeggies())
                .reduce(0L, Long::sum); // Sum of total FVcontent of all families.
        long totalProtein = familyProfiles.stream().map(f -> f.getWeeklyNutrientProfile().getProtein()).reduce(0L,
                Long::sum); // Sum of total protein of all families.
        long totalOther = familyProfiles.stream().map(f -> f.getWeeklyNutrientProfile().getOther()).reduce(0L,
                Long::sum); // Sum of total other of all families.
        long totalCalories = familyProfiles.stream().map(f -> f.getWeeklyNutrientProfile().getCalories()).reduce(0L,
                Long::sum); // Sum of total calories of all families.

        // checking all the families nutrition with inventory nutrition.
        if (totalGrains > InventoryData.getTotalGrain() || totalFVContent > InventoryData.getTotalFVContent() ||
                totalProtein > InventoryData.getTotalProContent() || totalOther > InventoryData.getTotalOther()
                || totalCalories > InventoryData.getTotalCalories()) {

            throw new RuntimeException("Not enough stock!"); // throwing inventory shortage error.
        }

        this.order = order; // taking order
    }

    /**
     * making the hamper
     */
    public void buildHamper() {
        List<Hamper> hampers = new ArrayList<>();
        List<FamilyProfile> familyProfiles = order.getRequestedFamilies(); // all the families in an array

        // Creates hamper with minimum item for all the families
        for (FamilyProfile familyProfile : familyProfiles) {
            List<FoodItem> items = pickMinimumItem(InventoryData.getFoodItems(), // picking the minimum items against
                                                                                 // families nutrition need
                    familyProfile.getWeeklyNutrientProfile());

            Hamper hamper = new Hamper(items); // creating the hamper
            hampers.add(hamper); // adding the hamper to the hamper list
        }

        order.setCreatedHamper(hampers); // setting all the hampers in order.
    }

    /**
     * picking minimum items from total inventorty items based on family needs then
     * removing the selected items from the inventory
     * 
     * @param items           list of items from the inventory
     * @param nutrientProfile the family nutrition needed for the family
     * @return the pickedItems to create the hamper
     */
    private List<FoodItem> pickMinimumItem(List<FoodItem> items, WeeklyNutrientProfile nutrientProfile) {
        long neededGrains = nutrientProfile.getWholeGrain();
        long neededFV = nutrientProfile.getFruitVeggies();
        long neededProtein = nutrientProfile.getProtein();
        long neededOther = nutrientProfile.getOther();
        long neededCalories = nutrientProfile.getCalories();

        List<FoodItem> minGrainItems = new ArrayList<>();
        items.sort(Comparator.comparing(FoodItem::getGrainContent).reversed()); // sorting inventroy food list based on
                                                                                // highest grains.
        for (FoodItem item : items) {
            if (neededGrains <= 0) // stop picking items if grain need is fulfilled.
                break;
            if (item.getGrainContent() == 0 || item.getGrainContent() > neededGrains) // Skip picking item if there is
                                                                                      // no grain for that item or the
                                                                                      // grain of the item is more than
                                                                                      // requirement.
                continue;

            minGrainItems.add(item); // add grain item to minimum grain items needed list.
            // decrease the family nutrition need by this item.
            neededGrains -= item.getGrainContent();
            neededFV -= item.getFVContent();
            neededProtein -= item.getProContent();
            neededOther -= item.getOther();
            neededCalories -= item.getCalories();
        }

        List<FoodItem> minFVItems = new ArrayList<>();
        items.sort(Comparator.comparing(FoodItem::getFVContent).reversed()); // sorting inventroy food list based on
                                                                             // highest FVContent.
        for (FoodItem item : items) {
            if (neededFV <= 0)// stop picking items if FVContent need is fulfilled.
                break;
            if (item.getFVContent() == 0 || item.getFVContent() > neededFV)
                continue;
            minFVItems.add(item); // add FVContent item to minimum FVContent items needed list.
            // decrease the family nutrition need by this item.
            neededGrains -= item.getGrainContent();
            neededFV -= item.getFVContent();
            neededProtein -= item.getProContent();
            neededOther -= item.getOther();
            neededCalories -= item.getCalories();

        }

        List<FoodItem> minProteinItems = new ArrayList<>();
        items.sort(Comparator.comparing(FoodItem::getProContent).reversed()); // sorting inventroy food list based on
                                                                              // highest protein.
        for (FoodItem item : items) {
            if (neededProtein <= 0)// stop picking items if protein need is fulfilled.
                break;
            if (item.getProContent() == 0 || item.getProContent() > neededProtein)
                continue;
            minProteinItems.add(item);// add protein item to minimum protein items needed list.
            // decrease the family nutrition need by this item.
            neededGrains -= item.getGrainContent();
            neededFV -= item.getFVContent();
            neededProtein -= item.getProContent();
            neededOther -= item.getOther();
            neededCalories -= item.getCalories();

        }

        List<FoodItem> minOtherItems = new ArrayList<>();
        items.sort(Comparator.comparing(FoodItem::getOther).reversed()); // sorting inventroy food list based on highest
                                                                         // other.
        for (FoodItem item : items) {
            if (neededOther <= 0)// stop picking items if other need is fulfilled.
                break;
            if (item.getOther() == 0 || item.getOther() > neededOther)
                continue;
            minOtherItems.add(item);// add other item to minimum other items needed list.
            // decrease the family nutrition need by this item.
            neededGrains -= item.getGrainContent();
            neededFV -= item.getFVContent();
            neededProtein -= item.getProContent();
            neededOther -= item.getOther();
            neededCalories -= item.getCalories();

        }

        List<FoodItem> minCaloriesItems = new ArrayList<>();
        items.sort(Comparator.comparing(FoodItem::getCalories).reversed()); // sorting inventroy food list based on
                                                                            // highest calories.
        for (FoodItem item : items) {
            if (neededCalories <= 0)// stop picking items if calories need is fulfilled.
                break;
            if (item.getCalories() == 0 || item.getCalories() > neededCalories)
                continue;

            minCaloriesItems.add(item);// add calories item to minimum calories items needed list.
            // decrease the family nutrition need by this item.
            neededGrains -= item.getGrainContent();
            neededFV -= item.getFVContent();
            neededProtein -= item.getProContent();
            neededOther -= item.getOther();
            neededCalories -= item.getCalories();

        }

        // adding all the items list into one list.
        List<FoodItem> pickedItems = new ArrayList<>();
        pickedItems.addAll(minGrainItems);
        pickedItems.addAll(minFVItems);
        pickedItems.addAll(minProteinItems);
        pickedItems.addAll(minOtherItems);
        pickedItems.addAll(minCaloriesItems);

        pickedItems = new ArrayList<>(new HashSet<>(pickedItems)); // removing duplicate items if any
        pickedItems.sort(Comparator.comparing(FoodItem::getId)); // sorting items as lowest itemID
        // removing picked items from inventory and list.
        pickedItems.stream().map(i -> i.getId()).forEach(InventoryData::deleteFoodItem);
        items.removeAll(pickedItems);

        return pickedItems;
    }

}
