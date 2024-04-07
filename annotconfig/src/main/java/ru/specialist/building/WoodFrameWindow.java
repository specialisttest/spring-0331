package ru.specialist.building;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WoodFrameWindow implements Window{

	@Override
	public void open() {
		System.out.println("Open wood frame window");
	}

}
