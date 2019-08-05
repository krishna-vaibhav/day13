package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("book_dao")
@Transactional
public class BookDaoImpl implements BookDao {
	@Autowired
	private SessionFactory sf;

	@Override
	public double getPrice(String isbn) {
		String jpql = "select b.price from Book b where b.isbn = :isbn";
		return sf.getCurrentSession().createQuery(jpql, Double.class).setParameter("isbn", isbn).getSingleResult();
	}

}
