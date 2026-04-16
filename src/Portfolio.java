/*
Author: Ryan Lee
Class: Portfolio.java

Description:
Handles the players positions and functions.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Portfolio {
    private double cash;
    private ArrayList<Position> positions;
    private ArrayList<Transactions> transactions;

    public Portfolio(double startingCash) {
        this.cash = startingCash;
        this.positions = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public double getCash() { return cash; }
    public void setCash(double cash) { this.cash = cash; }
    public ArrayList<Position> getPositions() { return positions; }
    public ArrayList<Transactions> getTransactions() { return transactions; }

    // Buying a stock
    public void buyStock(String ticker, int qty, double price) {
        double totalCost = qty * price;
        if (cash >= totalCost) {
            cash -= totalCost;
            transactions.add(new Transactions("BUY", ticker, qty, price));

            // Check if we already own this stock
            boolean found = false;
            for (Position p : positions) {
                if (p.getTicker().equals(ticker)) {
                    p.addShares(qty, price);
                    found = true;
                    break;
                }
            }
            if (!found) {
                positions.add(new Position(ticker, qty, price));
            }
            System.out.println("Successfully bought " + qty + " shares of " + ticker);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // Selling a stock
    public void sellStock(String ticker, int qty, double price) {
        for (Position p : positions) {
            if (p.getTicker().equals(ticker)) {
                if (p.getQuantity() >= qty) {
                    cash += (qty * price);
                    p.removeShares(qty);
                    transactions.add(new Transactions("SELL", ticker, qty, price));

                    if (p.getQuantity() == 0) {
                        positions.remove(p); // Remove from portfolio if 0 shares left
                    }
                    System.out.println("Successfully sold " + qty + " shares of " + ticker);
                    return;
                } else {
                    System.out.println("You don't own enough shares!");
                    return;
                }
            }
        }
        System.out.println("You don't own any shares of " + ticker);
    }

    //Display for portfolio and holdings
    public void displayPortfolio(SimulationEngine market) {
        System.out.println("\n--- YOUR PORTFOLIO ---");
        System.out.printf("Available Cash: $%.2f\n", cash);
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-6s | %-6s | %-10s | %-10s | %-10s\n", "Ticker", "Shares", "Avg Cost", "Current", "P&L");
        System.out.println("---------------------------------------------------------");

        double totalAccountValue = cash;

        for (Position p : positions) {
            double currentPrice = market.getPrice(p.getTicker());
            double positionValue = p.getQuantity() * currentPrice;
            totalAccountValue += positionValue;

            //P&L Calculation
            double profitLoss = positionValue - (p.getQuantity() * p.getAverageCost());

            System.out.printf("%-6s | %-6d | $%-9.2f | $%-9.2f | $%-9.2f\n",
                    p.getTicker(), p.getQuantity(), p.getAverageCost(), currentPrice, profitLoss);
        }
        System.out.println("---------------------------------------------------------");
        System.out.printf("Total Account Value: $%.2f\n", totalAccountValue);
    }

    // Sorting logic using Comparators
    public void displayTransactions(int sortOption) {
        System.out.println("\n--- TRANSACTION HISTORY ---");

        if (sortOption == 2) {
            // Sort by Ticker Alphabetically
            Collections.sort(transactions, Comparator.comparing(Transactions::getTicker));
        } else if (sortOption == 3) {
            // Sort by Total Amount (Highest to Lowest)
            Collections.sort(transactions, Comparator.comparing(Transactions::getTotalAmount).reversed());
        }
        // Option 1 is default chronological

        for (Transactions t : transactions) {
            System.out.println(t.toString());
        }
    }
}