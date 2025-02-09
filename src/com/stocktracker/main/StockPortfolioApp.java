package com.stocktracker.main;

import com.stocktracker.dao.StockDAO;
import com.stocktracker.models.Stock;
import java.util.Scanner;
import java.util.List;

public class StockPortfolioApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockDAO.createTable(); 

        while (true) {
            System.out.println("\n1. Add Stock \n2. Update Stock Price \n3. Delete Stock \n4. View Stocks \n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Stock Name: ");
                    String stockName = scanner.next();
                    System.out.print("Enter Type (Equity/MutualFund): ");
                    String stockType = scanner.next();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Buy Price: ");
                    double buyPrice = scanner.nextDouble();
                    System.out.print("Enter Current Price: ");
                    double currentPrice = scanner.nextDouble();
                    System.out.print("Enter Buy Date (YYYY-MM-DD): ");
                    String buyDate = scanner.next();

                    Stock stock = new Stock(stockName, stockType, quantity, buyPrice, currentPrice, buyDate);
                    StockDAO.addStock(stock);
                    break;

                case 2:
                    System.out.print("Enter Stock ID to update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    StockDAO.updateStockPrice(id, newPrice);
                    break;

                case 3:
                    System.out.print("Enter Stock ID to delete: ");
                    int deleteId = scanner.nextInt();
                    StockDAO.deleteStock(deleteId);
                    break;

                case 4:
                    List<Stock> stocks = StockDAO.getAllStocks();
                    for (Stock s : stocks) {
                        System.out.println(s.getStockName() + " | " + s.getStockType() + " | " + s.getQuantity() + " | ₹" + s.calculateProfitLoss());
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
