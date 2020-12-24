package org.vinay.learnAop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


public class LoggingAspect1 {

	public Object myAroundAdvice(ProceedingJoinPoint pjp){
		Object returnValue = null;
		try {
			System.out.println("Before the advice");
			returnValue = pjp.proceed();  
			System.out.println("After returning");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("after throwing");
		}

		System.out.println("After finally");
		return returnValue;
	}
	
}
