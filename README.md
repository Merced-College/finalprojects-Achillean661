[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=23299731)

# Final Project CPSC-39-12704 (TradeSim)

TradeSim is a stock market simulator I created that uses RNG to simulate the Random Walk hypothesis.

## Background
The Random Walk hypothesis is a market theory that states all price movements showcase random movements, which make it difficult to profit
off of in short timeframes. This theory is often used to deter beginners from delving into trading due to the strong likelihood of loss. Price movements in my simulation are generated through RNG, making it near impossible to reliably generate outsized profits as was proposed in the Random Walk hypothesis.

## Mechanics
TradeSim starts the player off with $10,000 in starting capital. From there, the player will be able to view market prices, their portfolio (which is empty starting off), and transaction history. In option 1, the player can see the avaliable stocks on the market and be able to purchase the respective stock using option 3 if they have the sufficient capital for how many shares they intend to buy or sell. In addition, the player can save their holdings and capital to a csv file where the game will load it the next time they play. As of writing this, there are no features to save and load selective save files. After the player makes their wanted transactions, they can choose option 6 to pass the day and see if they profit (or lose) and can adjust their portfolio accordingly. The goal is to maximize profit as much as possible.

## Data Structures
Most of my data structures are relatively simple with primtive types, a HashMap for storing ticker letters and stock price, ArrayLists to keep track of transactions and price, and Arrays to help read previous save files.