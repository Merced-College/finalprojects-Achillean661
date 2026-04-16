public class Position {
	
	// Fields required for tracking positions.
    private String ticker;
    private int quantity;
    private double averageCost;

    
    // Default Constructor
    public Position(String ticker, int quantity, double averageCost) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.averageCost = averageCost;
    }

    // Getters
    public String getTicker() { return ticker; }
    public int getQuantity() { return quantity; }
    public double getAverageCost() { return averageCost; }

    // Weighted Average Cost Calculation for positions.
    public void addShares(int qty, double price) {
        double totalCost = (this.quantity * this.averageCost) + (qty * price);
        this.quantity += qty;
        this.averageCost = totalCost / this.quantity;
    }

    // Method to remove shares if sold.
    public void removeShares(int qty) {
        this.quantity -= qty;
    }
}