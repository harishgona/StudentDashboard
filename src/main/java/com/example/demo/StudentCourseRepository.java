package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer>{
	
	@Query(value = "SELECT sc.courseid FROM StudentCourseEntity sc WHERE sc.studentid=?1")
	List<Integer> findById(int id); 
	
	@Query(value = "SELECT sc FROM StudentCourseEntity sc WHERE sc.studentid = ?1 and sc.courseid = ?2")
	StudentCourseEntity findCourseRecord(int studentid, int courseid);
	
	@Query(value = "SELECT sc FROM StudentCourseEntity sc")
	List<StudentCourseEntity> findAllRecords();

}
