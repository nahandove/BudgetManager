package budget;

import java.io.*;
import java.util.*;

public class PurchaseManager extends Manager implements Serializable {
    public List<String> purchases;
    private Map<String, List<String>> allPurchases = new HashMap<>();
    private Map<String, Double> allExpenses = new HashMap<>();

    public PurchaseManager() {
        purchases = new ArrayList<>();
    }

    public void printUpdateMenu() {
        ConsoleHelper.writeMessage("Choose the type of purchases:\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
    }

    public void printFullSubMenu() {
        ConsoleHelper.writeMessage("Choose the type of purchases:\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
    }

    public void updateSublistPurchases(int choice, String item, double price) {
        try {
            switch (choice) {
                case 1 -> ManagerFactory.getFoodManager().addToCart(item, price);
                case 2 -> ManagerFactory.getClothesManager().addToCart(item, price);
                case 3 -> ManagerFactory.getEntertainmentManager().addToCart(item, price);
                case 4 -> ManagerFactory.getOtherPurchaseManager().addToCart(item, price);
                case 5 -> {}
                default -> throw new IllegalArgumentException();
            }
            String shoppingEntry = addToCart(item, price);
            purchases.add(shoppingEntry);
            ConsoleHelper.writeMessage("Purchase was added!\n");
        } catch (IllegalArgumentException e) {
            ConsoleHelper.writeMessage("Please enter valid digits.");
            updateSublistPurchases(choice, item, price);
        }
    }

    public void printSublistPurchases(int choice) {
        try {
            switch (choice) {
                case 1 -> ManagerFactory.getFoodManager().printPurchases();
                case 2 -> ManagerFactory.getClothesManager().printPurchases();
                case 3 -> ManagerFactory.getEntertainmentManager().printPurchases();
                case 4 -> ManagerFactory.getOtherPurchaseManager().printPurchases();
                case 5 -> {
                    ConsoleHelper.writeMessage("\nAll:");
                    printPurchases();
                }
                case 6 -> mainMenu();
                default -> throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            ConsoleHelper.writeMessage("Please enter valid digits.");
            mainMenu();
        }
    }

    public List<String> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<String> purchases) {
        this.purchases = purchases;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public String addToCart(String item, double price) {
        String purchaseItem = String.format(Locale.ENGLISH, "%s $%.2f", item, price);
        expenses += price;
        return purchaseItem;
    }

    public void printPurchases() {
        if (getPurchases().isEmpty()) {
            ConsoleHelper.writeMessage("The purchase list is empty!\n");
        } else {
            getPurchases().forEach(System.out::println);
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total sum: $%.2f\n", getExpenses()));
        }
    }

    public void savePurchases() {
        allPurchases.put("all", getPurchases());
        allPurchases.put("food", ManagerFactory.getFoodManager().getPurchases());
        allPurchases.put("clothes", ManagerFactory.getClothesManager().getPurchases());
        allPurchases.put("entertainment", ManagerFactory.getEntertainmentManager().getPurchases());
        allPurchases.put("other", ManagerFactory.getOtherPurchaseManager().getPurchases());
        allExpenses.put("allExpenses", getExpenses());
        allExpenses.put("balance", getBalance());
        allExpenses.put("foodExpenses", ManagerFactory.getFoodManager().getExpenses());
        allExpenses.put("clothesExpenses", ManagerFactory.getClothesManager().getExpenses());
        allExpenses.put("entertainmentExpenses", ManagerFactory.getEntertainmentManager().getExpenses());
        allExpenses.put("otherExpenses", ManagerFactory.getOtherPurchaseManager().getExpenses());

        try (FileOutputStream fos = new FileOutputStream("purchases.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(allPurchases);
            oos.writeObject(allExpenses);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Invalid file name. Please try again");
            mainMenu();
        }
    }

    public void loadPurchases() {
        try (FileInputStream fis = new FileInputStream("purchases.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Map<String, List<String>> loadedPurchases = (HashMap) ois.readObject();
            Map<String, Double> loadedExpenses = (HashMap) ois.readObject();

            for (Map.Entry<String,List<String>> pair: loadedPurchases.entrySet()) {
                switch (pair.getKey()) {
                    case "all" -> setPurchases(pair.getValue());
                    case "food" -> ManagerFactory.getFoodManager().setPurchases(pair.getValue());
                    case "clothes" -> ManagerFactory.getClothesManager().setPurchases(pair.getValue());
                    case "entertainment" -> ManagerFactory.getEntertainmentManager().setPurchases(pair.getValue());
                    case "other" -> ManagerFactory.getOtherPurchaseManager().setPurchases(pair.getValue());
                    default -> ConsoleHelper.writeMessage("Unknown entry in file found.");
                }
            }

            for (Map.Entry<String, Double> pair: loadedExpenses.entrySet()) {
                switch(pair.getKey()) {
                    case "balance" -> balance = pair.getValue();
                    case "allExpenses" -> expenses = pair.getValue();
                    case "foodExpenses" -> ManagerFactory.getFoodManager().setExpenses(pair.getValue());
                    case "clothesExpenses" -> ManagerFactory.getClothesManager().setExpenses(pair.getValue());
                    case "entertainmentExpenses" -> ManagerFactory.getEntertainmentManager().setExpenses(pair.getValue());
                    case "otherExpenses" -> ManagerFactory.getOtherPurchaseManager().setExpenses(pair.getValue());
                    default -> ConsoleHelper.writeMessage("Unknown entry in file found.");
                }
            }
            ConsoleHelper.writeMessage("Purchases were loaded!");

        } catch (IOException | ClassNotFoundException e) {
            ConsoleHelper.writeMessage("Invalid file name. Please try again");
            mainMenu();
        }
    }
}
