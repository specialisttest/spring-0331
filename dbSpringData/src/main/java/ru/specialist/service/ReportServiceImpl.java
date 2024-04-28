package ru.specialist.service;

import java.util.Arrays;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.specialist.dao.CourseDao;

@Service("reports")
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CourseDao courseDao;

	
	// O(n^2)
	@Override
	public double getMedianaCourseLength() {
		int[] m = courseDao.findAll().stream()
			.mapToInt(c -> c.getLength()).toArray();
		
		Arrays.sort(m);
		
		if (m.length == 0) return 0d;
		if (m.length % 2 == 1)
			return m[m.length / 2];
		else
			return (m[m.length / 2] + m[m.length / 2 - 1]) / 2d;
	}

	@Override
	public double getAverageCourseLength() {
		OptionalDouble r = courseDao.findAll().stream()
			.mapToInt(c -> c.getLength()).average();
		return r.orElse(0);
	}

}
