/*
Author: Ryan Lee
Class: Main.java

Description:
Manages the frontend and backend commands for the simulation. Primary class that the player
interacts with.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Initializes classes for program.
        Scanner scanner = new Scanner(System.in);
        SimulationEngine market = new SimulationEngine();
        Portfolio portfolio = new Portfolio(10000.00); // Start with $10,000

        // Greeting for menu.
        System.out.println("=====================================");
        System.out.println(" Welcome to TradeSim! ");
        System.out.println("=====================================");

        FileHandler.loadData(portfolio);

        // Instance to check if program is still running.
        boolean running = true;

        // Command prompts recognized by their respective number.
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Market Prices");
            System.out.println("2. View Portfolio");
            System.out.println("3. Buy Stock");
            System.out.println("4. Sell Stock");
            System.out.println("5. View Transaction History");
            System.out.println("6. Advance to Next Day");
            System.out.println("7. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            // Switch instances to recognize and call specific commands with necessary methods if called.
            switch (choice) {
                case "1":
                    market.displayMarket();
                    break;
                case "2":
                    portfolio.displayPortfolio(market);
                    break;
                case "3":
                    System.out.print("Enter Ticker Symbol to BUY: ");
                    String buyTicker = scanner.nextLine().toUpperCase();
                    if (market.isValidTicker(buyTicker)) {
                        System.out.print("Enter quantity to buy: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        portfolio.buyStock(buyTicker, qty, market.getPrice(buyTicker));
                    } else {
                        System.out.println("Invalid Ticker Symbol.");
                    }
                    break;
                case "4":
                    System.out.print("Enter Ticker Symbol to SELL: ");
                    String sellTicker = scanner.nextLine().toUpperCase();
                    if (market.isValidTicker(sellTicker)) {
                        System.out.print("Enter quantity to sell: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        portfolio.sellStock(sellTicker, qty, market.getPrice(sellTicker));
                    } else {
                        System.out.println("Invalid Ticker Symbol.");
                    }
                    break;
                case "5":
                    System.out.println("Sort by:[1] Chronological  [2] Ticker Symbol  [3] Highest Amount");
                    System.out.print("Choice: ");
                    int sortChoice = Integer.parseInt(scanner.nextLine());
                    portfolio.displayTransactions(sortChoice);
                    break;
                case "6":
                    market.advanceDay();
                    break;
                case "7":
                    FileHandler.saveData(portfolio);
                    System.out.println("Exiting TradeSim. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}