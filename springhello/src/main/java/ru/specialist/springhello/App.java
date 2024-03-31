package ru.specialist.springhello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.specialist.stuff.AppContext;
import ru.specialist.stuff.Company;
import ru.specialist.stuff.Person;

/*
 * 1. Создать bean Person на основе класса Person (Используя аннотации @Component и @Value)
 * 2. Определить момент (точку в коде) создания объекта Person
 * 3. Дважды вызвав метод getBean определить, получаем ли мы ссылки на один объект или на
 * разные (например по hashCode)
 * 
 */
public class App {

	public static void main(String[] args) {
		System.out.println("Before context creation");
		ApplicationContext context = 
				//new AnnotationConfigApplicationContext("ru.specialist.stuff");
				new AnnotationConfigApplicationContext(AppContext.class);
		System.out.println("After context creation");
		
		Person p1 = context.getBean(Person.class);
		Person p2 = context.getBean(Person.class);
		
		System.out.println(p1.getCompany().getTitle());
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p1 == p2);
		
		Company yandex = context.getBean("yandex", Company.class);
		System.out.printf("Company title: %s : %d\n", yandex.getTitle(), yandex.getStuffs());

		Company s  = context.getBean("specialist", Company.class);
		System.out.printf("Company title: %s : %d\n", s.getTitle(), s.getStuffs());
		

	}

}
