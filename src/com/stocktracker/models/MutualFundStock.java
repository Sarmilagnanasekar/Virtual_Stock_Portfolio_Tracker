package com.stocktracker.models;

class MutualFundStock extends Stock {
    public MutualFundStock(String stockName, int quantity, double buyPrice, double currentPrice, String buyDate) {
        super(stockName, "Mutual Fund", quantity, buyPrice, currentPrice, buyDate);
    }
}
