package com.app.controller;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.Vendor;
import com.app.service.VendorService;

@Controller
@RequestMapping("/vendor")
public class VendorController {
	@Autowired // autowire=byName
	@Qualifier("vendor_service_dao")
	private VendorService service;

	public VendorController() {
		System.out.println("in constr of " + getClass().getName());
	}

	// request handling method for showing login form
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show login form");
		return "/vendor/login";
	}

	// request handling method for processing login form
	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email, @RequestParam String pass, Model map, HttpSession hs,
			RedirectAttributes flashMap) {
		System.out.println("in process login form");
		Vendor details = null;
		try {
			// invoke service layer fro user validation
			details = service.validateUser(email, pass);
		} catch (NoResultException e) {
			System.out.println("err in controller " + e);
			// invalid login
			map.addAttribute("mesg", "Invalid Login Pls retry ....");
		}
		if (details != null) {
			flashMap.addFlashAttribute("mesg", "Login Successful!!!");
			// valid login
			if (details.getRole().equals("vendor")) {
				// vendor login
				hs.setAttribute("vendor_dtls", details);
				return "redirect:/vendor/details";
			} else if (details.getRole().equals("admin")) {
				// admin login
		//		flashMap.addFlashAttribute("vendor_list", service.listVendors());
				return "redirect:/vendor/list";
			}
		}
		return "/vendor/login";

	}

	@GetMapping("/details")
	public String showVendorDetails() {
		System.out.println("in show dtls");
		return "/vendor/details";
	}

	@GetMapping("/list")
	public String showVendorList(Model map) {
		System.out.println("in show vendor list");
		map.addAttribute("vendor_list", service.listVendors());
		return "/vendor/list";//forward view --server pull
	}

	@GetMapping("/logout")
	public String userLogout(Model map, HttpSession hs, HttpServletResponse rs, HttpServletRequest rq) {
		System.out.println("in user logout page");
		// get user dtls from session scope --- model map
		map.addAttribute("user_dtls", hs.getAttribute("vendor_dtls"));
		// invalidate HS
		hs.invalidate();
		// auto take user to index page after some delay
		// API of HttpServletResponse to set header
		// public void setHeader(String nm,String value)
		rs.setHeader("refresh", "5;url=" + rq.getContextPath());
		return "/vendor/logout";
	}
	
	@GetMapping("/delete")
	public String deleteVendor(@RequestParam int vid,
			RedirectAttributes flashMap)
	{
		System.out.println("in vendor delete "+vid);
		flashMap.addFlashAttribute("mesg",service.deleteVendor(vid));
		return "redirect:/vendor/list";
	}
	@GetMapping("/add")
	public String showVendorAddForm(Vendor v,Model map)
	{
		//Vendor v=new Vendor();
		//getters , modelMap.addAttribute("vendor",v);
		System.out.println("in vendor add form "+v);
		System.out.println("model map "+map);
		return "/vendor/add";
	}
	@PostMapping("/add")
	public String processVendorAddForm(@Valid Vendor v,BindingResult result,RedirectAttributes flashMap)
	{
		System.out.println("in process add vendor "+v+" "+result);
		//chk for P.L validation rules
		if(result.hasErrors()) {
			System.out.println("P.L errs");
			return "/vendor/add"; //forwarding clnt to reg form
		}
		System.out.println("no p.l errs...");
		//invoke sevice layer method
		flashMap.addFlashAttribute("mesg", service.registerVendor(v));
		return "redirect:/vendor/list";
	}
	
	@GetMapping("/update")
	public String showUpdateForm(@RequestParam int vid,Model map)
	{
		System.out.println("in show update form "+vid);
		System.out.println("map "+map);
		map.addAttribute(service.getVendorDetails(vid));
		System.out.println("map again "+map); //DETACHED
		return "/vendor/update";
	}
	@PostMapping("/update")
	public String processUpdateForm(Vendor v,RedirectAttributes flashMap)
	{
		System.out.println("in process update "+v);//DETACHED POJO with updated state
		flashMap.addFlashAttribute("mesg",service.updateVendor(v));
		return "redirect:/vendor/list";
	}
	

}



