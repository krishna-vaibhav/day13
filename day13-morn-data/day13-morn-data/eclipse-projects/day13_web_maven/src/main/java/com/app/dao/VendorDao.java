package com.app.dao;

import java.util.List;

import com.app.pojos.Vendor;

public interface VendorDao {
	Vendor validateUser(String em,String pass);
	List<Vendor> listVendors();
	Vendor getVendorDetails(int id);
	String deleteVendorDetails(Vendor v);
	String registerVendor(Vendor v);
	String updateVendor(Vendor v);

}
