package budget;
import java.io.IOException;
import java.util.*;

public abstract class Manager {
    protected double balance = 0.0;
    protected double expenses = 0.0;

    private static PurchaseManager purchaseManager;

    public static PurchaseManager getInstance() {
        if (purchaseManager == null) {
            return new PurchaseManager();
        }
        return purchaseManager;
    }

    public double getBalance() {
        return balance;
    }

    public double getExpenses() {
        return expenses;
    }

    public abstract List<String> getPurchases();

    public abstract void setPurchases(List<String> purchases);

    public abstract void setExpenses(double expenses);

    public abstract String addToCart(String item, double price);

    public abstract void printPurchases();

    public void mainMenu() {
        ConsoleHelper.writeMessage("\nChoose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");

        try {
            int choice;

            choice = Integer.parseInt(ConsoleHelper.readString());
            ConsoleHelper.writeMessage("");

            switch (choice) {
                case 1 -> Main.manager.putIncome();
                case 2 -> Main.manager.putPurchases();
                case 3 -> {
                    if (Main.manager.getPurchases().isEmpty()) {
                        ConsoleHelper.writeMessage("The purchase list is empty!");
                        mainMenu();
                    }
                    Main.manager.getPurchaseList();
                }
                case 4 -> Main.manager.printBalance();
                case 5 -> Main.manager.savePurchases();
                case 6 -> Main.manager.loadPurchases();
                case 7 -> {
                    Sorter sorter = new Sorter();
                    sorter.printMenu();
                }
                case 0 -> {
                System.out.println("Bye!");
                System.exit(0);
                }
                default -> throw new IllegalArgumentException();
            }
            mainMenu();

        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong while processing input");
        } catch (IllegalArgumentException e) {
            ConsoleHelper.writeMessage("Please enter a valid digit");
            mainMenu();
        }
    }

    public void putIncome() throws IOException {
        ConsoleHelper.writeMessage("Enter income:");
        double income = Double.parseDouble(ConsoleHelper.readString());
        addIncome(income);
    }

    public void putPurchases() throws IOException {
        Main.manager.printUpdateMenu();
        int choice = Integer.parseInt(ConsoleHelper.readString());
        while (choice != 5) {
            ConsoleHelper.writeMessage("\nEnter purchase name:");
            String purchaseName = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Enter its price:");
            double price = Double.parseDouble(ConsoleHelper.readString());
            Main.manager.updateSublistPurchases(choice, purchaseName, price);
            Main.manager.printUpdateMenu();
            choice = Integer.parseInt(ConsoleHelper.readString());
        }
        mainMenu();
    }

    public void getPurchaseList() throws IOException {
        int choice = 0;
        while (choice != 6) {
            Main.manager.printFullSubMenu();
            choice = Integer.parseInt(ConsoleHelper.readString());
            Main.manager.printSublistPurchases(choice);
        }
        mainMenu();
    }

    public void addIncome(double income) {
        balance += income;
        ConsoleHelper.writeMessage("Income was added!");
    }

    public void calculateBalance() {
        balance -= getExpenses();
    }

    public void printBalance() {
        calculateBalance();
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Balance: $%.2f", getBalance()));
    }
}
