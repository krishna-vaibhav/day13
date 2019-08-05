package com.app.dao;

public interface StockDao {
	String increaseStock(String isbn, int qty);
	String increaseStockTxIsolation(String isbn, int qty);
	String reduceStock(String isbn, int qty);
	 int checkStock(String isbn);
	 int checkStockRepeatable(String isbn);
}
