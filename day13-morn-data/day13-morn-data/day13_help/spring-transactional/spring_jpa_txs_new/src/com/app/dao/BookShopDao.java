package com.app.dao;

 public interface BookShopDao {
     void purchase(String isbn, String username);
     void increaseStock(String isbn, int stock);
     int checkStock(String isbn);
    //for phantom read checking
     int checkStockRows(int limit);
     void insertNewStock(String isbn, int stock);
}
