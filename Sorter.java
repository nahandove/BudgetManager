package budget;

import java.io.IOException;
import java.util.*;

public class Sorter {
    List<String> allList;
    List<String> foodList = ManagerFactory.getFoodManager().getPurchases();;
    List<String> clothesList= ManagerFactory.getClothesManager().getPurchases();;
    List<String> entertainmentsList = ManagerFactory.getEntertainmentManager().getPurchases();;
    List<String> otherList= ManagerFactory.getOtherPurchaseManager().getPurchases();;

    public Sorter() {
        allList = new ArrayList<>();
        allList.addAll(ManagerFactory.getFoodManager().getPurchases());
        allList.addAll(ManagerFactory.getClothesManager().getPurchases());
        allList.addAll(ManagerFactory.getEntertainmentManager().getPurchases());
        allList.addAll(ManagerFactory.getOtherPurchaseManager().getPurchases());
    }

    static Map<String, Double> expensesMap = new HashMap<>();

    public void printMenu() {
        ConsoleHelper.writeMessage("how do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");

        try {
            int choice = Integer.parseInt(ConsoleHelper.readString());
            switch(choice) {
                case 1 -> sort(allList, "All:");
                case 2 -> sortByType();
                case 3 -> printSubMenu();
                case 4 -> Main.manager.mainMenu();
                default -> throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | IOException e) {
            ConsoleHelper.writeMessage("Please enter a valid digit\n");
            printMenu();
        }
    }

    public void printSubMenu() throws IOException {
        ConsoleHelper.writeMessage("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");

        int choice = Integer.parseInt(ConsoleHelper.readString());

        switch(choice) {
            case 1 -> sort(foodList, "Food:");
            case 2 -> sort(clothesList, "Clothes:");
            case 3 -> sort(entertainmentsList, "Entertainment:");
            case 4 -> sort(otherList, "Other:");
            default -> throw new IllegalArgumentException();
        }
        printMenu();
    }

    public void sort(List<String> list, String type) throws IOException {
        if (list.isEmpty()) {
            ConsoleHelper.writeMessage("\nThe purchase list is empty!\n");
            printMenu();
        }

        Collections.sort(list, (o1, o2) -> {
            int o1PriceTag = o1.lastIndexOf("$");
            int o2PriceTag = o2.lastIndexOf("$");
            double o1Price = Double.parseDouble(o1.substring(o1PriceTag + 1));
            double o2Price = Double.parseDouble(o2.substring(o2PriceTag + 1));
            if (o1Price > o2Price) {
                return -1;
            } else if (o1Price < o2Price ){
                return 1;
            } else {
                return 0;
            }
        });

        double total = 0.0;
        ConsoleHelper.writeMessage("\n" + type);
        for (String item: list) {
            ConsoleHelper.writeMessage(item);
            int priceTag = item.lastIndexOf("$");
            double price = Double.parseDouble(item.substring(priceTag + 1));
            total += price;
        }
        ConsoleHelper.writeMessage(String.format("Total: $%.2f\n", total));
        printMenu();
    }

    public void sortByType() {
        ConsoleHelper.writeMessage("");
        expensesMap.put("Food", ManagerFactory.getFoodManager().getExpenses());
        expensesMap.put("Clothes", ManagerFactory.getClothesManager().getExpenses());
        expensesMap.put("Entertainment", ManagerFactory.getEntertainmentManager().getExpenses());
        expensesMap.put("Other", ManagerFactory.getOtherPurchaseManager().getExpenses());

        List<Double> mapValues = new ArrayList<>();
        for (Map.Entry<String, Double> pair : expensesMap.entrySet()) {
            mapValues.add(pair.getValue());
        }

        Collections.sort(mapValues, (o1, o2) -> {
            if (o1 > o2) {
                return -1;
            } else if (o1 < o2) {
                return 1;
            }
            return 0;
        });

        Map<String, Double> sortedMap = new LinkedHashMap<>();

        for (double value: mapValues) {
            String key = getKey(expensesMap, value);
            sortedMap.put(key, value);
        }

        double total = 0.0;

        for (Map.Entry<String, Double> pair : sortedMap.entrySet()) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - $%.2f", pair.getKey(), pair.getValue()));
            double price = pair.getValue();
            total += price;
        }
        ConsoleHelper.writeMessage(String.format("Total sum: $%.2f:", total));
        ConsoleHelper.writeMessage("");
        printMenu();
    }

    public String getKey(Map<String, Double> map, double value) {
        for (Map.Entry<String, Double> pair: map.entrySet()) {
            if (value == pair.getValue()) {
                return pair.getKey();
            }
        }
        return null;
    }
}
