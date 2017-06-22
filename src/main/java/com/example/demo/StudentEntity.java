package com.example.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class StudentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2986625824276515715L;
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	@Column(name="studentname")
	private String studentname;
	
	@Column(name="degree")
	private String degree;
	
	@Column(name="major")
	private String major;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

}
