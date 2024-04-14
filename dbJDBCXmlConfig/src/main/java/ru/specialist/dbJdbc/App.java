package ru.specialist.dbJdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.dao.Course;
import ru.specialist.dao.CourseDao;

public class App {

	public static void main(String[] args) {
		
		try (ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("appContext.xml"))
		{
			CourseDao dao = context.getBean(CourseDao.class);
			
			/*Course s = new Course();
			s.setTitle("Spring");
			s.setLength(40);
			s.setDescription("Spring intro");
			
			dao.insert(s);
			System.out.println(s);
			*/
			
			for(Course course : dao.findAll())
				System.out.println(course);
			//Course c = dao.findById(3);
			//System.out.println(c);
			//c.setLength(33);
			//dao.update(c);
			//dao.delete(3);
			
			
		}
	}

}
