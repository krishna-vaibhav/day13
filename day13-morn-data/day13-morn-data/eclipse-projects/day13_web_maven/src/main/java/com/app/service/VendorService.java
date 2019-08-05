package com.app.service;

import java.util.List;

import com.app.pojos.Vendor;

public interface VendorService {
	Vendor validateUser(String email,String pass);
	List<Vendor> listVendors();
	String deleteVendor(int vendorId);
	String registerVendor(Vendor v);
	Vendor getVendorDetails(int id);
	String updateVendor(Vendor v);
}
