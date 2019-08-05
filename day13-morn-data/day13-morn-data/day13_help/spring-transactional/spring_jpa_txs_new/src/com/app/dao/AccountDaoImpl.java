package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.*;

@Repository("acct_dao")
@Transactional
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private SessionFactory sf;

	@Override
	@Transactional//( propagation = Propagation.REQUIRES_NEW)
	public String updateBalance(String email, double amt) {
		String jpql = "select a from Account a where a.email=:em";
		Account a = sf.getCurrentSession().createQuery(jpql, Account.class).setParameter("em", email).getSingleResult();
		a.setBalance(a.getBalance() - amt);
		boolean flag = false;
		
		if (flag)
			throw new RuntimeException("some err");
		return "Account bal updated";
	}

}
