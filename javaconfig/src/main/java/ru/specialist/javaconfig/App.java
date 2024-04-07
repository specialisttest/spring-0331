package ru.specialist.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.specialist.building.BuilderConfig;
import ru.specialist.building.House;
import ru.specialist.world.Country;
import ru.specialist.world.WorldConfig;

/*
 * Домашнее задание
 * Создать конфигурацию графических объектов 
 * используя JavaConfig
 * 
 * 
 */

public class App {
	
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(
						BuilderConfig.class,
						WorldConfig.class
						))
		{
			
			House h = context.getBean(House.class); 
			
			h.buildWalls();
			h.ventilate();
			h.installDoors();
			
			Country usa = context.getBean("usa", Country.class);
			System.out.println(usa.getTitle());
			for(var city : usa.getCities())
				System.out.printf("%-20s %s : %d\n", 
						city.getName(), city.getState(), city.getPopulation());			
			
						
			
		}
	}

}
