package ru.specialist.dbHibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;
import ru.specialist.dao.DaoConfig;

/*
 * Домашнее задание
 * Передалать конфигурацию (xml + annot) на (javconfig + annot)
 * При связывании SessionFactory и TransactionManager использовать
 * метод getObject()
 * 
 * 
 */

public class App {

	public static void main(String[] args) {
		
		//ClassPathXmlApplicationContext context = 
				//		new ClassPathXmlApplicationContext("applicationContext.xml");
    	AnnotationConfigApplicationContext context = 
    			new AnnotationConfigApplicationContext (DaoConfig.class);
		{
			CourseDao dao = context.getBean(CourseDao.class);
			

			for(Course course : dao.findAll())
				System.out.println(course);
			
			//for(Course course : dao.findByTitle("web"))
			//	System.out.println(course);

			
		}
	}

}
