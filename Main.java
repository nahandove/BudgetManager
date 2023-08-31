package budget;

public class Main {
    static PurchaseManager manager = Manager.getInstance();

    public static void main(String[] args) {
        manager.mainMenu();
    }
}
