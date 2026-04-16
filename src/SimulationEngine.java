/*
Author: Ryan Lee
Class: SimulationEngine.java

Description:
Handles the main backend running for the game.
*/


import java.util.HashMap;
import java.util.Random;

public class SimulationEngine {
    private HashMap<String, Double> marketPrices;
    private Random random;

    public SimulationEngine() {
        marketPrices = new HashMap<>();
        random = new Random();

        // Initialize market with some default stocks
        marketPrices.put("AAPL", 150.00);
        marketPrices.put("TSLA", 200.00);
        marketPrices.put("MSFT", 300.00);
        marketPrices.put("GOOG", 120.00);
        marketPrices.put("AMZN", 100.00);
    }

    public void displayMarket() {
        System.out.println("\n--- CURRENT MARKET PRICES ---");
        for (String ticker : marketPrices.keySet()) {
            System.out.printf("%-5s : $%.2f\n", ticker, marketPrices.get(ticker));
        }
    }

    public boolean isValidTicker(String ticker) {
        return marketPrices.containsKey(ticker);
    }

    public double getPrice(String ticker) {
        return marketPrices.getOrDefault(ticker, 0.0);
    }

    //Market Price Simulation Logic (Goes off Random Walk model)
    public void advanceDay() {
        System.out.println("\nAdvancing to the next day... Market prices have changed!");
        for (String ticker : marketPrices.keySet()) {
            double currentPrice = marketPrices.get(ticker);
            // Random fluctuation between -5% and +5%
            double changePercent = -0.05 + (0.10 * random.nextDouble());
            double newPrice = currentPrice * (1 + changePercent);
            marketPrices.put(ticker, newPrice);
        }
    }
}