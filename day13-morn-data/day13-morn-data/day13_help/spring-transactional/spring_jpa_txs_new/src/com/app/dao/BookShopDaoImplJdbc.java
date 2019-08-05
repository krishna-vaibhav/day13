package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

//@Repository
//@Transactional
public class BookShopDaoImplJdbc implements BookShopDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void purchase(String isbn, String username) {
		int price = jdbcTemplate.queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, isbn);

		jdbcTemplate.update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 " + "WHERE ISBN = ?", new Object[] { isbn });

		jdbcTemplate.update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? " + "WHERE USERNAME = ?",
				new Object[] { price, username });
	}

	@Transactional
	public void increaseStock(String isbn, int stock) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to increase book stock");

		jdbcTemplate.update("UPDATE BOOK_STOCK SET STOCK = STOCK + ? WHERE ISBN = ?", stock, isbn);

		System.out.println(threadName + " - Book stock increased by " + stock);
	//	sleep(threadName);

/*		System.out.println(threadName + " - Trying to roll back Book stock");
		throw new RuntimeException("Increased by mistake");
*/	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public int checkStock(String isbn) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to check book stock");

		int stock = jdbcTemplate.queryForObject("SELECT STOCK FROM BOOK_STOCK WHERE ISBN = ?", Integer.class, isbn);

		System.out.println(threadName + " - Book stock is " + stock);
		sleep(threadName);
		
		System.out.println(threadName + " - Prepare to check book stock again");

		 stock = jdbcTemplate.queryForObject("SELECT STOCK FROM BOOK_STOCK WHERE ISBN = ?", Integer.class, isbn);

		System.out.println(threadName + " - Book stock is " + stock);
	
		return stock;
	}

	private void sleep(String threadName) {
		System.out.println(threadName + " - Sleeping");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}

		System.out.println(threadName + " - Wake up");
	}
	@Transactional
	public void insertNewStock(String isbn, int stock) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to insert book ");

		jdbcTemplate.update("insert into book_stock values(?,?)",  isbn,stock);

		System.out.println(threadName + " - Book  inserted by ");
	//	sleep(threadName);

/*		System.out.println(threadName + " - Trying to roll back Book stock");
		throw new RuntimeException("Increased by mistake");
*/	}
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public int checkStockRows(int limit) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to check rows having stock < "+limit);

		int numRows = jdbcTemplate.queryForObject("select count(*) from book_stock where stock < ?", Integer.class, limit);

		System.out.println(threadName + " Matching no of rows " + numRows);
		sleep(threadName);
		System.out.println(threadName + " - Prepare to check rows again , having stock < "+limit);
		numRows = jdbcTemplate.queryForObject("select count(*) from book_stock where stock < ?", Integer.class, limit);
		System.out.println(threadName + " Matching no of rows " + numRows);
		return numRows;
	}

}
