package budget;

import java.util.ArrayList;
import java.util.List;

public class OtherPurchaseManager extends PurchaseManager {
    private double expenses = 0.0;
    private List<String> purchases = new ArrayList<>();

    public String addToCart(String item, double price) {
        String shoppingEntry = super.addToCart(item, price);
        purchases.add(shoppingEntry);
        expenses += price;
        return shoppingEntry;
    }

    public List<String> getPurchases() {
        return purchases;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setPurchases(List<String> purchases) {
        this.purchases = purchases;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public void printPurchases() {
        ConsoleHelper.writeMessage("\nOther:");
        super.printPurchases();
    }
}
