package ru.specialist.dao;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Transactional(isolation = Isolation.READ_COMMITTED,
		timeout = 20, propagation = Propagation.REQUIRED /* default */)
@Repository("courseDao")
public class CourseDaoJpaImpl implements CourseDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	private PlatformTransactionManager  tm;
	private TransactionTemplate tt;
	
	@Autowired
	public CourseDaoJpaImpl(PlatformTransactionManager tm) {
		this.tm = tm;
		this.tt = new TransactionTemplate(tm);
	}
	
	public void TestTransaction() {
		//TransactionalOperator op = TransactionalOperator.create(tm);
		
		
		this.tt.setIsolationLevel(   Isolation.READ_COMMITTED.value() );
		this.tt.setTimeout(20);
		this.tt.executeWithoutResult(new Consumer<TransactionStatus>() {
			
			@Override
			public void accept(TransactionStatus t) {
				
				
			}
		});
		
		
	}
	
	@Transactional(readOnly = true)
	@Override
	//@Cacheable(value = "courses", key="#parameter_name")
	@Cacheable("courses")
	public Course findById(int id) {
		return em.find(Course.class, id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Course> findAll() {
		return  em.createQuery("select c from Course c", // JPQL
					Course.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Course> findByTitle(String title) {
		return em.createQuery("select c from Course c where c.title LIKE :title",
				Course.class)
				.setParameter("title", "%"+title.trim()+"%")
				.getResultList();
	}

	@Override
	@CachePut(value = "courses", key="#course.id")
	public void insert(Course course) {
		em.persist(course);
	}

	@Override
	@CachePut(value = "courses", key="#course.id")
	public void update(Course course) {
		if (course.getId() != 0) {
			Course updatedCourse = em.merge(course);
		}
	}

	@Override
	@CacheEvict("courses")
	public void delete(int id) {
		em.remove(findById(id));
	}

}
