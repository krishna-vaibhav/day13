package com.app.pojos;
import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
	private String email;
	private double balance;
	public Account() {
		System.out.println("in cnstr of " + getClass().getName());
	}
	public Account(String email, double balance) {
		super();
		this.email = email;
		this.balance = balance;
	}
	@Id
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [email=" + email + ", balance=" + balance + "]";
	}
	
	

}
