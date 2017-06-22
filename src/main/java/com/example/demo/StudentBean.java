package com.example.demo;

import java.util.List;

public class StudentBean {

	//private StudentEntity student;


	private String studentname;

	private String degree;

	private String major;

	private List<?> courseids;
	
	private int studentId;
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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

	public List<?> getCourseids() {
		return courseids;
	}

	public void setCourseids(List<?> courseids) {
		this.courseids = courseids;
	}

	


}
