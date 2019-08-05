package com.app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;




public class LoggerAspect {
	
	
	public void logIt() {

		System.out.println("Logging details");
	}

}
