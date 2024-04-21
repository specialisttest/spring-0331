package ru.specialist.dbJPA;

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
			
			//dao.delete(9);
			/*Course spring = new Course();
			spring.setTitle("Spring");
			spring.setLength(40);
			spring.setDescription("Spring framework");
			dao.insert(spring);*/
			
			/*Course w = dao.findById(1);
			w.setLength(33);
			dao.update(w);*/
			
			Course w1 = dao.findById(1);
			Course w2 = dao.findById(1);
			
			System.out.println(w1);
			System.out.println(w2);
			
			
			//for(Course course : dao.findAll())
			//	System.out.println(course);
			
			//for(Course course : dao.findByTitle("web"))
			//	System.out.println(course);

			
		}
	}

}
