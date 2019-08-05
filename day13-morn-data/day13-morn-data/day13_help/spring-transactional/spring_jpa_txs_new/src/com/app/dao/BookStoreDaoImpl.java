package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.*;

@Repository("store_dao")
@Transactional
public class BookStoreDaoImpl implements BookStoreDao {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private StockDao stockDao;
	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional(timeout = 2)
	public String purchaseBooks(String email, String isbn, int qty) {
		
			double price = bookDao.getPrice(isbn);
			String sts = stockDao.reduceStock(isbn, qty);
			try {
				sts = sts.concat(" " + accountDao.updateBalance(email, price * qty));
			} catch (Exception e) {
				System.out.println("err : " + e);
				sts = sts.concat(" err in update bal " + e.getMessage());
			}
			return "Purchase status " + sts;
		
		
	}

}
