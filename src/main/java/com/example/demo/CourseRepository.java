package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>{
	
	@Query(value = "SELECT c.coursename FROM CourseEntity c WHERE c.id IN ?1")
	List<String> findAllRecords(List<Integer> list);
	
	@Query(value = "SELECT s FROM CourseEntity s")
	List<CourseEntity> findAllRecords();

}
