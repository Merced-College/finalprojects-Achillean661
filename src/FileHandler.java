/*
Author: Ryan Lee
Class: FileHandler.java

Description:
Handles the saving and loading of csv files.
*/

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {


    // Handles the saving of player data and writes it into a csv file.
    public static void saveData(Portfolio portfolio) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("portfolio_data.csv"));
            // Save Cash
            writer.println(portfolio.getCash());

            // Save Positions
            for (Position p : portfolio.getPositions()) {
                writer.println("POS," + p.getTicker() + "," + p.getQuantity() + "," + p.getAverageCost());
            }

            // Save Transactions
            for (Transactions t : portfolio.getTransactions()) {
                writer.println("TXN," + t.getType() + "," + t.getTicker() + "," + t.getQuantity() + "," + t.getPrice());
            }

            writer.close();
            System.out.println("Data successfully saved to portfolio_data.csv");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Loads data from portfolio using the csv file.
    public static void loadData(Portfolio portfolio) {
        File file = new File("portfolio_data.csv");
        if (!file.exists()) {
            System.out.println("No previous save data found. Starting fresh.");
            return;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            if (fileScanner.hasNextLine()) {
                // Load Cash
                double savedCash = Double.parseDouble(fileScanner.nextLine());
                portfolio.setCash(savedCash);
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                if (data[0].equals("POS")) {
                    String ticker = data[1];
                    int qty = Integer.parseInt(data[2]);
                    double avgCost = Double.parseDouble(data[3]);
                    portfolio.getPositions().add(new Position(ticker, qty, avgCost));
                }
                else if (data[0].equals("TXN")) {
                    String type = data[1];
                    String ticker = data[2];
                    int qty = Integer.parseInt(data[3]);
                    double price = Double.parseDouble(data[4]);
                    portfolio.getTransactions().add(new Transactions(type, ticker, qty, price));
                }
            }
            fileScanner.close();
            System.out.println("Previous save data loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}