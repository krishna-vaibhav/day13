package com.app.bank;

public interface Account {
	void deposit(double amt);
	void withdraw(double amt);
	double getSummary();
	
}
