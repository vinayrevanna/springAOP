package org.vinay.learnAop.service;

import org.vinay.learnAop.aspect.Loggable;
import org.vinay.learnAop.model.Circle;
import org.vinay.learnAop.model.Triangle;

public class ShapeService {

	private Circle circle;
	private Triangle triangle;
	
	@Loggable
	public Circle getCircle() {
		return circle;
	}
	
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	public Triangle getTriangle() {
		return triangle;
	}
	
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
	
	
}
