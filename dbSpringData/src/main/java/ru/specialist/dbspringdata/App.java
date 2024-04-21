package ru.specialist.dbspringdata;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;
import ru.specialist.dao.DaoConfig;

public class App {

	public static void main(String[] args) {
		
    	AnnotationConfigApplicationContext context = 
    			new AnnotationConfigApplicationContext (DaoConfig.class);
		{
			CourseDao dao = context.getBean(CourseDao.class);
			
			dao.findById(1);
			Optional<Course> oc = dao.findById(1);
			
			if(oc.isPresent())
				System.out.println(oc.get());
			else
				System.out.println("no course");
			
			int n = dao.changeCourseLength(32, 34);
			System.out.println(n);
			
			List<Course> courses = dao.findAll();
			
			//for(Course course : dao.findAll())
			//	System.out.println(course);
			//for(Course course : dao.findByLength(40))
			//		System.out.println(course);
			//for(Course course : dao.findShortCourses(35))
			//	System.out.println(course);
			
			for(Course course : dao.findByTitle("web"))
				System.out.println(course);

			
		}
	}

}
