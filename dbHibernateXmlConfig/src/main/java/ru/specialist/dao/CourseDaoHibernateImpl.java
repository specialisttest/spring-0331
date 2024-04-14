package ru.specialist.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;


import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("courseDao")
public class CourseDaoHibernateImpl implements CourseDao {
	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Value("#{sessionFactory}")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly = true)
	@Override
	public Course findById(int id) {
		return getSessionFactory().getCurrentSession().
				byId(Course.class).load(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Course> findAll() {
		return getSessionFactory().getCurrentSession().
				createSelectionQuery("from Course c", Course.class).list(); // HQL
	}

	@Transactional(readOnly = true)
	@Override
	public List<Course> findByTitle(String title) {
		return getSessionFactory().getCurrentSession()
				.createSelectionQuery("from Course c where title LIKE :title", Course.class)
				.setParameter("title", "%" + title + "%").list(); // HQL
	}

	@Override
	public void insert(Course course) {
		getSessionFactory().getCurrentSession().save(course); // saveOrUpdate()
	}

	@Override
	public void update(Course course) {
		getSessionFactory().getCurrentSession().update(course);
	}

	@Override
	public void delete(int id) {
		Course c = new Course();
		c.setId(id);
		getSessionFactory().getCurrentSession().remove(c);
	}

}
