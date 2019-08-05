package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.*;

@Repository("stock_dao")
@Transactional
public class StockDaoImpl implements StockDao {

	@Autowired
	private SessionFactory sf;

	@Override
	// @Transactional(timeout=2)
	public String increaseStock(String isbn, int qty) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to increase book stock");
		String jpql = "select s from BookStock s where s.isbn=:isbn";
		BookStock stock = sf.getCurrentSession().createQuery(jpql, BookStock.class).setParameter("isbn", isbn)
				.getSingleResult();
		stock.setAvailableStock(stock.getAvailableStock() + qty);

		System.out.println(threadName + " - Book stock increased by " + qty);
		sleep(threadName);

		boolean flag = true;
		System.out.println(threadName + " - Trying to roll back Book stock - " + flag);
		if (flag)
			throw new RuntimeException("Increased by mistake");

		return "Stock with ISBN " + isbn + " updated";
	}

	@Override
	// @Transactional(timeout=2)
	public String increaseStockTxIsolation(String isbn, int qty) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to increase book stock");
		String jpql = "update BookStock s set s.availableStock=s.availableStock+:st where s.isbn=:isbn";
		int rows = sf.getCurrentSession().createQuery(jpql).setParameter("st", qty).setParameter("isbn", isbn)
				.executeUpdate();
		System.out.println(threadName + " - Book stock increased by " + qty);
		sleep(threadName);

		boolean flag = true;
		System.out.println(threadName + " - Trying to roll back Book stock - " + flag);
		if (flag)
			throw new RuntimeException("Increased by mistake");

		return "Stock with ISBN " + isbn + " updated";
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public int checkStock(String isbn) {
		String threadName = Thread.currentThread().getName();
		String jpql = "select s.availableStock from BookStock s where s.isbn=:isbn";
		System.out.println(threadName + " - Prepare to check book stock");
		int stock = sf.getCurrentSession().createQuery(jpql, Integer.class).setParameter("isbn", isbn)
				.getSingleResult();
		System.out.println(threadName + " - Book stock is " + stock);
		return stock;

	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public int checkStockRepeatable(String isbn) {
		String threadName = Thread.currentThread().getName();
		String jpql = "select s.availableStock from BookStock s where s.isbn=:isbn";
		System.out.println(threadName + " - Prepare to check book stock");
		int stock = sf.getCurrentSession().createQuery(jpql, Integer.class).setParameter("isbn", isbn)
				.getSingleResult();
		System.out.println(threadName + " - Book stock is " + stock);

		sleep(threadName);
		System.out.println(threadName + " - Prepare to check book stock again");
		stock = sf.getCurrentSession().createQuery(jpql, Integer.class).setParameter("isbn", isbn).getSingleResult();
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

	@Override
	public String reduceStock(String isbn, int qty) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to increase book stock");
		String jpql = "update BookStock s set s.availableStock=s.availableStock-:st where s.isbn=:isbn";
		int rows = sf.getCurrentSession().createQuery(jpql).setParameter("st", qty).setParameter("isbn", isbn)
				.executeUpdate();
		System.out.println(threadName + " - Book stock reduced by " + qty);
		boolean flag = false;
		System.out.println(threadName + " - Trying to roll back Book stock - " + flag);
		if (flag)
			throw new RuntimeException("Increased by mistake");

		return "Stock with ISBN " + isbn + " updated";

	}
	

}
