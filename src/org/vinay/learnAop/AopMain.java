package org.vinay.learnAop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vinay.learnAop.service.ShapeService;

public class AopMain {

	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ShapeService shapeService = ctx.getBean("shapeService",ShapeService.class);
		//System.out.println(shapeService.getCircle().getName());
		//System.out.println(shapeService.getTriangle().getName());
		//shapeService.getCircle().setNameReturn("Dummy name");
		shapeService.getCircle();
		
		
		//spring uses proxy to achieve AOP
		
	}
}
