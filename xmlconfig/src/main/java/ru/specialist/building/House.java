package ru.specialist.building;

public class House {
	
	private int height;
	private Window window;
	private Material wall;
	
	public void ventilate() {
		getWindow().open();
	}
	
	public House() {}
	
	public House(Window window, int height) {
		this.window = window;
		this.height = height;
	}	
	
	public void buildWalls() {
		for(int i=1; i <= getHeight(); i++) {
			System.out.printf("Floor %d. ", i);
			getWall().buildUp();
		}
	}	
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Window getWindow() {
		return window;
	}
	public void setWindow(Window window) {
		this.window = window;
	}
	public Material getWall() {
		return wall;
	}
	public void setWall(Material wall) {
		this.wall = wall;
	}
	
	public void onCreate() {
		System.out.println("Creating house");
	}
	
	public void onDestroy() {
		System.out.println("Destroying house");
	}	
	
	
	
	
}
