package ru.specialist.dbHibernate;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;

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
		
		try (ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("appContext.xml"))
		{
			CourseDao dao = context.getBean(CourseDao.class);
			

			for(Course course : dao.findAll())
				System.out.println(course);
			
			//for(Course course : dao.findByTitle("web"))
			//	System.out.println(course);

			
		}
	}

}
