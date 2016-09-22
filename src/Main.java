import java.util.*;

public class Main {
    private static Map<SpeciesEnum, FoodTypesEnum> whatASpeciesEats = new HashMap<>();
    private static Map<String, SpeciesEnum> animals = new HashMap<>();
    private static Map<FoodTypesEnum, Integer> feedOnHand = new HashMap<>();
    private static List<FeedAction> feedingRecord = new ArrayList<>();

    private static int minimumFoodQuantity = 10;

    public static void main(String[] args) {
        whatASpeciesEats.put(SpeciesEnum.ELEPHANT, FoodTypesEnum.VEGGIES);
        whatASpeciesEats.put(SpeciesEnum.LION, FoodTypesEnum.MEAT);

        animals.put("Bob", SpeciesEnum.ELEPHANT);
        animals.put("Sally", SpeciesEnum.ELEPHANT);
        animals.put("Zebra", SpeciesEnum.LION);
        animals.put("Simba", SpeciesEnum.LION);

        feedOnHand.put(FoodTypesEnum.VEGGIES, 100);

        newFeed(100, FoodTypesEnum.MEAT);
        newFeed(100, FoodTypesEnum.VEGGIES);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, - 1);

        feedAnimal(50, new Date(), "Bob");
        feedAnimal(10, new Date(), "Simba");
        feedAnimal(20, new Date(), "Simba");
        feedAnimal(10, cal.getTime(), "Simba");
        feedAnimal(10, cal.getTime(), "Bob");

        printAvgFeedPerDay();

        printAvgFeedingTimesPerDay();
    }


    public static void newFeed(int quantity, FoodTypesEnum type){
        if(feedOnHand.get(type) != null){
            feedOnHand.put(type, feedOnHand.get(type) + quantity);
        }
        else{
            feedOnHand.put(type, quantity);
        }
        System.out.println("Type = " + type + " Quantity = " + feedOnHand.get(type));
    }

    public static void feedAnimal(int quantity, Date date, String name){
        if(quantity < 0){
            System.out.println("Sorry you can't unfeed an animal");
        }
        else if(animals.get(name) != null){
            FeedAction feedAction = new FeedAction();
            feedAction.setDate(date);
            feedAction.setName(name);

            FoodTypesEnum foodType = whatASpeciesEats.get(animals.get(name));

            if(feedOnHand.get(foodType) <= minimumFoodQuantity){
                //order more
            }

            if(feedOnHand.get(foodType) < quantity){
                System.out.println("Sorry you couldn't have feed them that much!!");
                feedAction.setQuantity(feedOnHand.get(foodType));
                feedOnHand.put(foodType, 0);
            }
            else{
                feedAction.setQuantity(quantity);
                feedOnHand.put(foodType, feedOnHand.get(foodType) - quantity);
            }

            feedingRecord.add(feedAction);
            System.out.println("The amount of " + foodType + " you have left " + feedOnHand.get(foodType));
        }
        else{
            System.out.println("Sorry that animal is an imposter catch it!!");
        }
    }

    public static void printAvgFeedPerDay(){
        Map<SpeciesEnum, HashMap> report = new HashMap<>();
        animals.forEach((name, species) -> {
            Map<String, Integer> fedPerDay = new HashMap<>();
            int totalAmountOfFood = 0;
            int totalTimesFed = 0;
            Map<Integer, Integer> days = new HashMap<Integer, Integer>();
            for (FeedAction feedAction : feedingRecord) {
                Calendar cal = Calendar.getInstance();

                if(feedAction.getName() == name){
                    totalAmountOfFood += feedAction.getQuantity();
                    cal.setTime(feedAction.getDate());
                    days.put(cal.get(Calendar.DAY_OF_YEAR), 0);
                    totalTimesFed++;
                }
            }
            if(days.size() > 0){
                System.out.println(name + " was feed " + totalAmountOfFood / days.size() + " kg per day on average");
                fedPerDay.put(name, totalTimesFed / days.size());
            }
        });
    }

    public static void printAvgFeedingTimesPerDay(){
        animals.forEach((name, species) -> {
            Map<String, Integer>feeding = new HashMap<>();
            for (FeedAction feedAction : feedingRecord) {

            }
        });



    }
}
