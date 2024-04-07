package ru.specialist.building;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("singleton") // default
public class MetalDoor implements Door {

	@Override
	public void install() {
		System.out.println("Install metal door");

	}

}
