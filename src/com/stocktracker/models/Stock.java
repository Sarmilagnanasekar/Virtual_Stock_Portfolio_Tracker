package com.stocktracker.models;

public class Stock {
    private int id;
    private String stockName;
    private String stockType;
    private int quantity;
    private double buyPrice;
    private double currentPrice;
    private String buyDate;

    public Stock(String stockName, String stockType, int quantity, double buyPrice, double currentPrice, String buyDate) {
        this.stockName = stockName;
        this.stockType = stockType;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        this.buyDate = buyDate;
    }

    // Getters
    public int getId() {
    	return id; 
    	}
    public String getStockName() { 
    	return stockName; 
    	}
    public String getStockType() { 
    	return stockType; 
    	}
    public int getQuantity() { 
    	return quantity;
    	}
    public double getBuyPrice() { 
    	return buyPrice;
    	}
    public double getCurrentPrice() {
    	return currentPrice; 
    	}
    public String getBuyDate() { 
    	return buyDate;
    	}

    // Setters (Only for modifiable fields)
    public void setQuantity(int quantity) { 
    	this.quantity = quantity;
    	}
    public void setCurrentPrice(double currentPrice) { 
    	this.currentPrice = currentPrice; 
    	}
    public void setBuyDate(String buyDate) { 
    	this.buyDate = buyDate;
    	}

    // Profit/Loss Calculation
    public double calculateProfitLoss() {
        return (currentPrice - buyPrice) * quantity;
    }
}
