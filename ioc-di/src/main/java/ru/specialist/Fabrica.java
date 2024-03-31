package ru.specialist;

/*
 * 1. Create object (House)
 * 2. Init object (including link with another object - dependency injection)
 * 
 * 
 * Spring: interface BeanFactory
 */
public class Fabrica {
	
	// Hook
	protected void onHouseCreated(House house) {}
	
	public House getWoodHouse() {
		// house depends on window
		
		House house = new House();
		Window wnd = new WoodFrameWindow();
		house.setWindow(wnd);
		
		onHouseCreated(house);
		
		return house;
		
		//return new House(new WoodFrameWindow());
	}

}
