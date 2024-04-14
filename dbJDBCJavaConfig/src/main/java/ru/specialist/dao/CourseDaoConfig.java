package ru.specialist.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Configuration
@PropertySource("jdbc.properties")
@ComponentScan("ru.specialist.dao")
public class CourseDaoConfig {
	
	@Autowired
	private Environment env;
	
	//@Bean(destroyMethod = "close")
	@Bean
	public DataSource webDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		ds.setUrl(env.getRequiredProperty("jdbc.url"));
		ds.setUsername(env.getRequiredProperty("jdbc.username"));
		ds.setPassword(env.getRequiredProperty("jdbc.password"));
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate( webDataSource() );
	}
	
	/*@Bean
	public RowMapper<Course> courseRowMapper() {
		return new CourseRowMapper();
	}
	
	@Bean
	public CourseDao courseDao() {
		CourseDaoJdbcImpl dao = new CourseDaoJdbcImpl();
		dao.setCourseRowMapper( courseRowMapper() );
		dao.setJdbcTemplate( jdbcTemplate() );
		
		return dao;
	}*/

}
