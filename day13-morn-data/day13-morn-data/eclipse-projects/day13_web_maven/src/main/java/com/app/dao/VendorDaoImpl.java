package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Vendor;

@Repository // mandatory
public class VendorDaoImpl implements VendorDao {
	// dependency --singleton , immutable , inhernetly thrd safe SF
	@Autowired // byType
	private SessionFactory sf;

	@Override
	public Vendor validateUser(String em1, String pass1) {
		String jpql = "select v from Vendor v where v.email = :em " + "and v.password = :pass";
		return sf.getCurrentSession().createQuery(jpql, Vendor.class).setParameter("em", em1)
				.setParameter("pass", pass1).getSingleResult();
	}

	@Override
	public List<Vendor> listVendors() {
		String jpql = "select v from Vendor v where v.role=:rl";
		return sf.getCurrentSession().createQuery(jpql, Vendor.class).setParameter("rl", "vendor").getResultList();
	}

	@Override
	public Vendor getVendorDetails(int id) {
		return sf.getCurrentSession().get(Vendor.class, id);
	}

	@Override
	public String deleteVendorDetails(Vendor v) {
		sf.getCurrentSession().delete(v);
		return "Vendor details with ID " + v.getId() + " deleted...";
	}

	@Override
	public String registerVendor(Vendor v) {
		sf.getCurrentSession().persist(v);
	/*	boolean flag = true;
		if (flag)
			throw new RuntimeException("some error!!!!!");
	*/	return "Vendor added with ID " + v.getId();
	}

	@Override
	public String updateVendor(Vendor v) {
		// i/p detched pojo ref. --added to L1 cache &
		// Upon commit --update query will be fired.
		sf.getCurrentSession().update(v);
		return "Vendor Details for ID " + v.getId() + " updated...";
	}

}
