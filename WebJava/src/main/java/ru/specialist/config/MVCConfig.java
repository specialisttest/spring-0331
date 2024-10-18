package ru.specialist.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;



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
		viewResolver.setOrder(2);
		
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	@Description("Spring Message Resolver")
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
	
	@Bean
	@Description("Thymeleaf Template Resolver")
	public SpringResourceTemplateResolver  templateResolver() {
		SpringResourceTemplateResolver  templateResolver = new SpringResourceTemplateResolver ();
	    templateResolver.setPrefix("/WEB-INF/thymeleaf/views/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    templateResolver.setCharacterEncoding("UTF-8");
	    templateResolver.setCacheable(false);

	    return templateResolver;
	}

	@Bean
	@Description("Thymeleaf Template Engine")
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    templateEngine.setTemplateEngineMessageSource(messageSource());
	    
	    return templateEngine;
	}
	
	@Bean
	@Description("Thymeleaf View Resolver")
	public ThymeleafViewResolver viewResolver() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setCharacterEncoding("UTF-8");
	    
	    viewResolver.setOrder(1);
	    
	    return viewResolver;
	}

}
