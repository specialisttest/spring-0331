package ru.specialist;

public class App {

	public static void main(String[] args) {
		// client code
		
		//PlasticWindow wnd = new PlasticWindow(); 
		//House house = new House(wnd);
		
		//House house = new House();
		//house.setWindow(new PlasticWindow());
		//house.setWindow(new WoodFrameWindow());
		
		//House house = new Fabrica().getWoodHouse();
		try(var context = new Container())
		{
			House house = context.getWoodHouse();
			house.ventilate();
		}
		
	}

}
