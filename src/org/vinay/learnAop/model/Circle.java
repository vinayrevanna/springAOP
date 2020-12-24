package org.vinay.learnAop.model;

public class Circle {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("Circle's setter is called");  //two times it executed when set once because of spring initilizes from spring xml
		//throw(new RuntimeException());
	}
	
	public String setNameReturn(String name) {
		this.name = name;
		System.out.println("Circle's return setter is called"); 
		return name;
	}
}
