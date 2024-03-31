package ru.specialist.stuff;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
//@Scope("prototype")
public class Person {

	private String name;
	private int age;
	private Company company;
	
	public String getName() {
		return name;
	}
	
	@Value("Sergey")
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	
	@Value("#{T(java.lang.Math).random() * 10 + 30}") // Spring Expression Language (SpEL)
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public Company getCompany() {
		return company;
	}

	@Value("#{specialist}")
	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return String.format("%s - %d : %d", getName(), getAge(), hashCode());
	}
	
	public Person() {
		System.out.println("Person ctor()");
	}
	
}
