import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<SpeciesEnum, FoodTypesEnum> whatASpeciesEats = new HashMap<>();
    private static Map<String, SpeciesEnum> animals = new HashMap<>();
    private static Map<FoodTypesEnum, Integer> feedOnHand = new HashMap<>();

    public static void main(String[] args) {
        whatASpeciesEats.put(SpeciesEnum.ELEPHANT, FoodTypesEnum.VEGGIES);
        whatASpeciesEats.put(SpeciesEnum.LION, FoodTypesEnum.MEAT);

        animals.put("Bob", SpeciesEnum.ELEPHANT);
        animals.put("Sally", SpeciesEnum.ELEPHANT);
        animals.put("Zebra", SpeciesEnum.LION);
        animals.put("Simba", SpeciesEnum.LION);

        feedOnHand.put(FoodTypesEnum.VEGGIES, 100);

        newFeed(10, FoodTypesEnum.MEAT);
        newFeed(100, FoodTypesEnum.VEGGIES);
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
}
