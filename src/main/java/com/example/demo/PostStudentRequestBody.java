package com.example.demo;

import java.util.List;

public class PostStudentRequestBody {
	
	private String studentId;

	private String studentname;
	
	private String degree;
	
	private String major;

	private List<Integer> courseids;
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
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


	public List<Integer> getCourseids() {
		return courseids;
	}

	public void setCourseids(List<Integer> courseids) {
		this.courseids = courseids;
	}

	

}
