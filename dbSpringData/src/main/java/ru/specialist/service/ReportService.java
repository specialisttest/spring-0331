package ru.specialist.service;

/*
 * Домашнее задание
 * Создать реализацию бина (@Service)
 * и протестировать методы, считающие среднию и
 * медианную длительность курса
 * 
 * Использовать готовый репозиторий CourseDao для
 * извлечения данных
 * 
 */

public interface ReportService {
	double getMedianaCourseLength();
	double getAverageCourseLength();
}
