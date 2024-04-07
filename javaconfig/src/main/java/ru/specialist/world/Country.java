package ru.specialist.world;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("usa")
public class Country {
	
	/*private*/ Country() {}
	
	private String title;
	private List<City> cities;
	
	public String getTitle() {
		return title;
	}
	
	@Value("USA")
	public void setTitle(String title) {
		this.title = title;
	}
	public List<City> getCities() {
		return cities;
	}
	
	@Value("#{cities.?[population gt 100000 and state eq 'TX']}")
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	// factory method
	public static Country getInstance() {
		System.out.println("Country factory method getInstance");
		return new Country();
	}
	
	

}
