package com.app.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



public class TransactionMgr {
	

	
	public void beginTransaction()
	{
		System.out.println("beginning tx");
	}
	
	public void commitTransaction()
	{
		System.out.println("committing tx");
	}
	
	public void discardTransaction()
	{
		System.out.println("rolling back tx");
	}
	

}
