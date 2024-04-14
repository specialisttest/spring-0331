package ru.specialist.building;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class House implements Closeable {
	
	private int height;
	private Window window;
	private Material wall;
	
	//private List<Door> doors;
	private Map<String, Door> doors;
	
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
	
	public void installDoors() {
		/*for(Door d : doors)
			d.install();*/
		for(Map.Entry<String, Door> e : doors.entrySet()) {
			System.out.printf("Key : %s. ", e.getKey());
			e.getValue().install();
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
	
	@Value("#{bricks}")
	public void setWall(Material wall) {
		this.wall = wall;
	}
	
	
	/*public List<Door> getDoors() {
		return doors;
	}

	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}*/
	
	
	
	@PostConstruct
	public void onCreate() {
		System.out.println("Creating house");
	}
	
	public Map<String, Door> getDoors() {
		return doors;
	}

	public void setDoors(Map<String, Door> doors) {
		this.doors = doors;
	}

	@PreDestroy
	public void onDestroy() {
		System.out.println("Destroying house");
	}

	@Override
	public void close()  {
		System.out.println("House close()");
	}	
	
	public void shutdown()  {
		System.out.println("House shutdown()");
	}	
	
	
	
}
