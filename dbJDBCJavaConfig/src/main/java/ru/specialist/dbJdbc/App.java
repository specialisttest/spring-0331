package ru.specialist.dbJdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;
import ru.specialist.dao.CourseDaoConfig;

public class App {

	public static void main(String[] args) {
		
		try (AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(CourseDaoConfig.class))
		{
			CourseDao dao = context.getBean(CourseDao.class);
			
			for(Course course : dao.findAll())
				System.out.println(course);
			
			//for(Course course : dao.findByTitle("web"))
			//	System.out.println(course);
			
			
		}
	}

}
