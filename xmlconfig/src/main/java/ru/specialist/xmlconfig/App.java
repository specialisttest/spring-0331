package ru.specialist.xmlconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.building.House;

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
			
			System.out.println(context.getBean(MainWin.class));
			System.out.println(context.getBean(MainWin.class));
			
		} // context.close()
		

		/* Scope:
		 * 	singleton - только один объект на контейнер
		 *  prototype - при каждом запросе контейнера создаётся новый экземпляр бина
		 */
	}

}
