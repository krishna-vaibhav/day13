package com.app.bank;

import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("acct")
public class AccountImpl implements Account {
	private Random r1=new Random();

	@Override
	public void deposit(double amt) {
		System.out.println("in deposit");
	//	throw new RuntimeException("some error");
		
	}

	@Override
	public void withdraw(double amt) {
		System.out.println("in withdraw");
		throw new RuntimeException("acct overdrawn");
		
	}

	@Override
	public double getSummary() {
		System.out.println("in get summary");
		return (r1.nextInt(5000)+1000);
	}

}
