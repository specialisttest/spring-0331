package ru.specialist.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ru.specialist.controllers", 
	"ru.specialist.models"})
public class MVCConfig implements WebMvcConfigurer {
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
        converters.add(getJacksonHttpMessageConverter());
	}

    @Bean("jacksonHttpMessageConverter")
    public MappingJackson2HttpMessageConverter getJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = 
        		new MappingJackson2HttpMessageConverter();
        converter.setPrettyPrint(true);
        return converter;
    }
	
	
	// folder for static resources
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/*").
			addResourceLocations("/resources/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Bean
	public InternalResourceViewResolver jspViewResolver()
	{
		InternalResourceViewResolver viewResolver = 
				new InternalResourceViewResolver();
		viewResolver.setOrder(1);
		
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	

}
