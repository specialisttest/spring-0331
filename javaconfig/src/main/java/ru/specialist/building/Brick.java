package ru.specialist.building;

import org.springframework.stereotype.Component;

@Component("bricks")
public class Brick implements Material {

	@Override
	public void buildUp() {
		System.out.println("Create wall using bricks");
	}

}
