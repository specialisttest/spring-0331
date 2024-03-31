package ru.specialist;

import java.io.Closeable;

// POJO
public class House implements Closeable {
	
	private Window window;

	public House(Window window) {
		this.window = window;
	}
	
	public House() {}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}
	
	public void ventilate() {
		getWindow().open();
	}

	@Override
	public void close(){
		System.out.println("House destroy");
	}

}
