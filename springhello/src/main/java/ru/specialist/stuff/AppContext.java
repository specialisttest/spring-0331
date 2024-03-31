package ru.specialist.stuff;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.specialist.stuff")
public class AppContext {
	
	@Value("#{yandex.stuffs / 5}")
	private int specialist_size;
	
	@Bean("specialist")
	public Company specialist() {
		Company c = new Company();
		c.setTitle("Specialist.ru");
		// specialist.stuff = yandex.stuff / 5
		// specialist.setStuffs ( yandex.getStuffs() / 5 );
		
		c.setStuffs(specialist_size);
		return c;
	}

	@Bean
	public Company yandex() {
		Company c = new Company();
		c.setTitle("Yandex");
		c.setStuffs(1000);
		return c;
	}
}
