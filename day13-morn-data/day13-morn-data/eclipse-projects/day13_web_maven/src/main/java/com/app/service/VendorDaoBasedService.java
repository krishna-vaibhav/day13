package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.VendorDao;
import com.app.pojos.Vendor;

@Service("vendor_service_dao") // To tell SC --business logic methods
@Transactional // to enable auto tx management
public class VendorDaoBasedService implements VendorService {
	// dependency
	@Autowired
	private VendorDao dao;// inject dao i/f n not imple class

	@Override
	public Vendor validateUser(String email, String pass) {
		// TODO Auto-generated method stub
		return dao.validateUser(email, pass);
	}

	@Override
	@Transactional(timeout=5,readOnly=true)
	public List<Vendor> listVendors() {
		System.out.println("adding a dly....");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sleep over...");
		return dao.listVendors();
	}

	@Override
	public String deleteVendor(int vendorId) {
		Vendor v = dao.getVendorDetails(vendorId);
		if (v != null)
			return dao.deleteVendorDetails(v);
		return "Vendor deletion failed : Invalid ID " + v.getId();
	}

	@Override
	public String registerVendor(Vendor v) {
		return dao.registerVendor(v);
	}

	@Override
	public Vendor getVendorDetails(int id) {
		// TODO Auto-generated method stub
		return dao.getVendorDetails(id);
	}

	@Override
	public String updateVendor(Vendor v) {
		// TODO Auto-generated method stub
		return dao.updateVendor(v);
	}

}
