package budget;

public class ManagerFactory {
    private static FoodManager foodManager = new FoodManager();
    private static ClothesManager clothesManager = new ClothesManager();
    private static EntertainmentManager entertainmentManager = new EntertainmentManager();
    private static OtherPurchaseManager otherPurchaseManager = new OtherPurchaseManager();

    public static Manager getFoodManager() {
        return foodManager;
    }

    public static Manager getClothesManager() {
        return clothesManager;
    }

    public static Manager getEntertainmentManager() {
        return entertainmentManager;
    }

    public static Manager getOtherPurchaseManager() {
        return otherPurchaseManager;
    }
}
