package ru.specialist.building;

import java.io.Closeable;
import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;

@Component("bricks")
@Scope("prototype")
public class Brick implements Material, Closeable {

	@Override
	public void buildUp() {
		System.out.println("Create wall using bricks");
	}
	
	@PreDestroy
	public void onDestroy()
	{
		System.out.println("Brick onDestroy()");
	}

	@Override
	public void close() {
		System.out.println("Brick close()");
		
	}

}
