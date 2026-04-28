/*
Author: Ryan Lee
Class: Portfolio.java

Description:
Handles the transactions for when a player buys or sells.
*/

public class Transactions {
    private String type; // "BUY" or "SELL"
    private String ticker;
    private int quantity;
    private double price;
    private double totalAmount;

    // Default Constructor
    public Transactions(String type, String ticker, int quantity, double price) {
        this.type = type;
        this.ticker = ticker;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = quantity * price;
    }

    // Setters and Getters
    public String getType() {
        return type;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-5s | Qty: %-4d | Price: $%-8.2f | Total: $%.2f",
                type, ticker, quantity, price, totalAmount);
    }
}