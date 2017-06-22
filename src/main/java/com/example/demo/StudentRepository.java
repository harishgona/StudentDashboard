package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>{
	
	@Query(value = "SELECT s FROM StudentEntity s")
	List<StudentEntity> findAllRecords();
}




