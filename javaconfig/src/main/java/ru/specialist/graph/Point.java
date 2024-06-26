package ru.specialist.graph;

import org.springframework.beans.factory.BeanNameAware;

public class Point extends Shape implements BeanNameAware {
	private Coords coords;
	
	
	public Coords getCoords() {
		return coords;
	}
	public void setCoords(Coords coords) {
		this.coords = coords;
	}
	public Point(Coords coords) {
		this.coords = coords;
	}
	
	
	public int getX() {
		return getCoords().getX();
	}
	public void setX(int y) {
		getCoords().setX(y);
	}
	public int getY() {
		return getCoords().getY();
	}
	
	public void setY(int y) {
		getCoords().setY(y);
	}
	
	public void draw() {
		System.out.printf("Point id: %s (%d, %d) Color: %s\n", 
				this.id, getX(), getY(), getColor());
		
	}
	
	private String id;
	@Override
	public void setBeanName(String name) {
		this.id = name;
	}

}
