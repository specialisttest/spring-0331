package ru.specialist.xmlconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.building.House;
import ru.specialist.graph.Circle;
import ru.specialist.graph.Point;
import ru.specialist.graph.Scene;

/* Labs
 * Coords
 * 	x,y
 * abstract Shape
 * 	String color
 *  abstract draw()
 *  
 * Point extends Shape
 * 	Coords coords
 *  draw();
 *  setX(), setY()
 * 
 * Circle extends Shape
 *   Coords center, int radius
 *   draw()
 *   
 *   Beans: Coords, Point, Circle
 *   
 * Scene
 *    List<Shape> objects
 *    
 *    1. Inject list to scene (constructor Scene(List<Shape>)) //autowire="constructor"
 *    2. draw() // drawing all objects
 *    3. Singleton
 *  * 
 */


public class App {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("appContext.xml"))
		{
		
			House h = context.getBean(House.class);
			
			h.buildWalls();
			//System.out.println(h);
			h.ventilate();
			
			h.installDoors();
			
			
			//System.out.println(context.getBean(MainWin.class));
			//System.out.println(context.getBean(MainWin.class));
			
			Country usa = context.getBean("usa", Country.class);
			System.out.println(usa.getTitle());
			for(var city : usa.getCities())
				System.out.printf("%-20s %s : %d\n", 
						city.getName(), city.getState(), city.getPopulation());
			
			
		} // context.close()
		

		try (ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("graphConfig.xml"))
		{
			//context.getBean(Point.class).draw();
			//context.getBean(Circle.class).draw();
			context.getBean(Scene.class).draw();
		}
		
		
		/* Scope:
		 * 	singleton - только один объект на контейнер
		 *  prototype - при каждом запросе контейнера создаётся новый экземпляр бина
		 *  https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html
		 */
		
		/*
		 * Внедрение коллекций
		 * 
			Элемент	коллекция	 Назначение
			<list> 				Связывание списка значений, допускаются повторяющиеся
								значения
			<set>				 Связывание множества значений, гарантирует отсутствие
								повторяющихся значений
			<map>				 Связывание коллекций пар имя/значение, где имя и значение
								могут быть значениями любых типов
			<props>				 Связывание коллекций пар имя/значениее, где имя и значение
								должны имеет строковый тип (String) 		 
		*/		
				
	}

}
