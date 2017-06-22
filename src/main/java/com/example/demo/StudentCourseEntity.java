package com.example.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_course")
public class StudentCourseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1651572234205075271L;
	
	@Id
	@Column(name="studentid")
	private int studentid;
	
	@Column(name="courseid")
	private int courseid;
	
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	

}
