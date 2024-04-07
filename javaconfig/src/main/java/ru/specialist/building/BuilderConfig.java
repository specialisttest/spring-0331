package ru.specialist.building;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("ru.specialist.building")
@PropertySource("house.properties")
//@EnableTransactionManagement // transaction support (db)
//@EnableWebMvc // web mvc support
public class BuilderConfig {
	
	/*@Bean // id == bricks
	@Scope("prototype")
	public Material bricks() {
		return new Brick();
	}*/
	
	@Bean("mybrick")
	@Scope("prototype")
	public Material myBricks() {
		return new Brick();
	}
	
	@Bean("log")
	@Scope("prototype")
	public Material logs() {
		return new Wood();
	}
	
	
	@Bean("woodFrame")
	public Window woodFrameWindow() {
		return new WoodFrameWindow();
	}
	
	@Bean("plasticFrame")
	public Window plasticFrameWindow() {
		System.out.println("plasticFrameWindow()");
		return new PlasticWindow();
	}
	
	@Bean
	@Scope("prototype")
	public Door woodDoor() {
		return new WoodDoor();
	}
	
	@Bean
	// Scope == singleton
	public Door metalDoor() {
		return new MetalDoor();
	}
	
	//@Value("${house.height}")
	//private int houseHeight;
	
	@Autowired
	private Environment env;
	
	@Bean
	@Lazy
	public House house(@Value("${house.height}") int houseHeight) {
		Window w1 = plasticFrameWindow();
		Window w2 = plasticFrameWindow();
		System.out.println(w1);
		System.out.println(w2);
		House h = new House(
				plasticFrameWindow(), // это НЕ ВЫЗОВ МЕТОДА, заменяется на обращение к контейнеру
									  // context.getBean("plasticFrame", Window.class)
									  // реализуется с помощью аспектов (AOP)
				houseHeight);
		
		//h.setWall(bricks());
		
		h.setDoors(new HashMap<String, Door>());
		h.getDoors().put("A", metalDoor());
		
		int innerDoorCount = env.getProperty(
				"house.innerDoorCount", Integer.class, 1);
		
		for(int i = 0; i < innerDoorCount; i++)
			h.getDoors().put( String.valueOf((char)('B'+i)), woodDoor());
		
		return h;
		
	}
		

}
