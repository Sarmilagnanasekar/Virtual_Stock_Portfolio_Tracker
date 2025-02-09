package com.stocktracker.models;

class EquityStock extends Stock {
    public EquityStock(String stockName, int quantity, double buyPrice, double currentPrice, String buyDate) {
        super(stockName, "Equity", quantity, buyPrice, currentPrice, buyDate);
    }
}



