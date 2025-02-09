package com.stocktracker.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.stocktracker.models.Stock;

public class StockDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/StockPortfolio";
    private static final String USER = "sarmi";
    private static final String PASSWORD = "sarmi";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS stocks ("
                   + "id INT AUTO_INCREMENT PRIMARY KEY, "
                   + "stock_name VARCHAR(100), "
                   + "stock_type VARCHAR(50), "
                   + "quantity INT, "
                   + "buy_price DOUBLE, "
                   + "current_price DOUBLE, "
                   + "buy_date DATE)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStock(Stock stock) {
        String sql = "INSERT INTO stocks (stock_name, stock_type, quantity, buy_price, current_price, buy_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, stock.getStockName());
            stmt.setString(2, stock.getStockType());
            stmt.setInt(3, stock.getQuantity());
            stmt.setDouble(4, stock.getBuyPrice());
            stmt.setDouble(5, stock.getCurrentPrice());
            stmt.setString(6, stock.getBuyDate());

            stmt.executeUpdate();
            System.out.println("Stock added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStockPrice(int id, double newPrice) {
        String sql = "UPDATE stocks SET current_price = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newPrice);
            stmt.setInt(2, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stock updated successfully!");
            } else {
                System.out.println("Stock ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStock(int id) {
        String sql = "DELETE FROM stocks WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Stock deleted successfully!");
            } else {
                System.out.println("Stock ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Stock> getAllStocks() {
        List<Stock> stockList = new ArrayList<>();
        String sql = "SELECT * FROM stocks";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Stock stock = new Stock(
                    rs.getString("stock_name"),
                    rs.getString("stock_type"),
                    rs.getInt("quantity"),
                    rs.getDouble("buy_price"),
                    rs.getDouble("current_price"),
                    rs.getString("buy_date")
                );
                stockList.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }
}
