package ru.specialist.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;


import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/*
 * 1. Реализовать метод findByTitle (используя оператор WHERE title LIKE ? )
 * 2. Переделать xml конфигурацию на javaConfig 
 * 
 * 
 */

public class CourseDaoJdbcImpl implements CourseDao {
	
	private static final String SQL_SELECT_COURSE = 
			"SELECT id, title, length, description FROM courses";
	
	private static final String SQL_SELECT_COURSE_BY_ID = 
			SQL_SELECT_COURSE + " WHERE id = ?";	
	
	private static final String SQL_DELETE_COURSE_BY_ID =
			 "DELETE FROM courses WHERE id = ?";
	
	private static final String SQL_INSERT_COURSE =
			 "INSERT INTO courses (title, length, description) VALUES (?, ?, ?)";
	
	private static final String SQL_UPDATE_COURSE =
			 "UPDATE courses SET title = ?, length = ?, description = ? WHERE id = ?";
	
			
	
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private RowMapper<Course> courseRowMapper;
	public RowMapper<Course> getCourseRowMapper() {
		return courseRowMapper;
	}
	public void setCourseRowMapper(RowMapper<Course>courseRowMapper) {
		this.courseRowMapper = courseRowMapper;
	}
	@Override
	public Course findById(int id) {
		return getJdbcTemplate().queryForObject(
				SQL_SELECT_COURSE_BY_ID, 
				new Object[] {id},
				getCourseRowMapper()
				);
	}

	@Override
	public List<Course> findAll() {
		/*List<Map<String, Object>> rows = 
				getJdbcTemplate().queryForList(SQL_SELECT_COURSE);
		
		List<Course> courses = new ArrayList<Course>();
		for(Map<String, Object>row : rows) {
			Course c = new Course();
			c.setId( (int)row.get("id") );
			c.setTitle( (String)row.get("title") );
			c.setLength( (int)row.get("length") );
			c.setDescription( (String)row.get("description") );
			courses.add(c);
		}*/
		
		List<Course> courses = 
				getJdbcTemplate().query(SQL_SELECT_COURSE, 
						getCourseRowMapper());
		return courses;
	}

	@Override
	public List<Course> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Course course) {
		PreparedStatementCreatorFactory f =
				new PreparedStatementCreatorFactory(SQL_INSERT_COURSE,
					Types.NVARCHAR, Types.INTEGER, Types.NVARCHAR);
			
		f.setGeneratedKeysColumnNames("id");
		KeyHolder kh = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(
			f.newPreparedStatementCreator(new Object[] {
				course.getTitle(), course.getLength(), course.getDescription()}),
			kh);
		
		course.setId(kh.getKey().intValue());
		
	}

	@Override
	public void update(Course course) {
		getJdbcTemplate().update(SQL_UPDATE_COURSE,
				course.getTitle(), course.getLength(),
				course.getDescription(), course.getId());
		
	}

	@Override
	public void delete(int id) {
		getJdbcTemplate().update(SQL_DELETE_COURSE_BY_ID, id);
		
	}

}
