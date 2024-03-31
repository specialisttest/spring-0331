package ru.specialist;

import java.io.Closeable;
import java.io.IOException;

/*
 * 
 * 1. Create object (House)
 * 2. Init object (including link with another object - dependency injection)
 * 3. Control lifetime (creation object, event, destroy object)
 * 
 * Spring: interface ApplicationContext
 */
public class Container extends Fabrica implements Closeable{
	
	private House house; // component - java bean
	
	@Override
	protected void onHouseCreated(House house) {
		super.onHouseCreated(house);
		this.house = house;
	}
	
	@Override
	public House getWoodHouse() {
		if (house == null)
			return super.getWoodHouse();
		else
			return house;
	}

	@Override
	public void close(){
		if (house != null && house instanceof Closeable)
			house.close();
		house = null;
		
	}

}
