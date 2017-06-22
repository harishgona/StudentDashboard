package com.example.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class CourseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6488709532372965223L;

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="coursename")
	private String coursename;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	

}
