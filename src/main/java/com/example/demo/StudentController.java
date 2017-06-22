package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private StudentCourseRepository studentCourseRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@RequestMapping(value="/students", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseStatus(HttpStatus.OK)
	public List<StudentBean> getStudents(){
		
		List<StudentBean> studentBeanList = new ArrayList<StudentBean>();
		List<StudentEntity> studentEntityList = studentRepo.findAllRecords();
		for(StudentEntity studentEntity : studentEntityList){
			StudentBean studentBean = new StudentBean();
			studentBean.setStudentId(studentEntity.getId());
			studentBean.setStudentname(studentEntity.getStudentname());
			studentBean.setDegree(studentEntity.getDegree());
			studentBean.setMajor(studentEntity.getMajor());
			List<Integer> courseIdList = getCourseIds(studentEntity.getId());
			studentBean.setCourseids(getCourseEntity(courseIdList));
			studentBeanList.add(studentBean);	
		}
		return studentBeanList;
	}
	
	@GetMapping("student")
	@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity<StudentBean> getStudent(@RequestParam("id") String id){
		
		StudentEntity studentEntity = studentRepo.findOne(Integer.parseInt(id));
		StudentBean studentBean = new StudentBean();
		studentBean.setStudentId(studentEntity.getId());
		studentBean.setStudentname(studentEntity.getStudentname());
		studentBean.setDegree(studentEntity.getDegree());
		studentBean.setMajor(studentEntity.getMajor());
		List<Integer> courseIdList = getCourseIds(studentEntity.getId());
		studentBean.setCourseids(courseIdList);
		return new ResponseEntity<StudentBean>(studentBean, HttpStatus.OK);
	}
	
	
	
	private List<Integer> getCourseIds(int id){
		
		return studentCourseRepository.findById(id);
	}
	
	private List<String> getCourseEntity(List<Integer> courseIdList){
		
		return courseRepository.findAllRecords(courseIdList);
	}
	
	@RequestMapping(value="/students", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseStatus(HttpStatus.CREATED)
	public StudentEntity saveStudents(@RequestBody PostStudentRequestBody postStudentRequestBody){
		StudentCourseEntity studentCourseEntity = new StudentCourseEntity();
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setStudentname(postStudentRequestBody.getStudentname());
		studentEntity.setDegree(postStudentRequestBody.getDegree());
		studentEntity.setMajor(postStudentRequestBody.getMajor());
		studentRepo.save(studentEntity);
		int studentid = studentEntity.getId();
		for(int courseid : postStudentRequestBody.getCourseids())
		{
			studentCourseEntity = new StudentCourseEntity();
			studentCourseEntity.setStudentid(studentid);
			studentCourseEntity.setCourseid(courseid);
			studentCourseRepository.save(studentCourseEntity);
		}
			return studentEntity;
		
	}
	
	
	
	
	@PutMapping("student")
	@ResponseStatus(HttpStatus.OK)
	public PostStudentRequestBody updateStudent(@RequestBody PostStudentRequestBody postStudentRequestBody) {
		
		StudentEntity studentEntity = new StudentEntity();
		studentEntity = studentRepo.findOne(Integer.parseInt(postStudentRequestBody.getStudentId()));
		List<Integer> courseIds = getCourseIds(Integer.parseInt(postStudentRequestBody.getStudentId()));
		studentEntity.setStudentname(postStudentRequestBody.getStudentname());
		studentEntity.setDegree(postStudentRequestBody.getDegree());
		studentEntity.setMajor(postStudentRequestBody.getMajor());
		studentRepo.save(studentEntity);
		for (int courseid : postStudentRequestBody.getCourseids())
		{
			if (courseIds.contains(courseid))
			{
				continue;
			}
			else {
				
				StudentCourseEntity studentCourseEntity = new StudentCourseEntity();
				studentCourseEntity.setStudentid(Integer.parseInt(postStudentRequestBody.getStudentId()));
				studentCourseEntity.setCourseid(courseid);
				studentCourseRepository.save(studentCourseEntity);
			}
		}
		return postStudentRequestBody;
	}
		
	@DeleteMapping("student")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteStudent(@RequestParam("id") String id) {
		
		int sid = Integer.parseInt(id);
		List<Integer> courseIds = getCourseIds(sid);
		StudentEntity studentEntity = new StudentEntity();
		
		for (int courseid : courseIds)
		{
			StudentCourseEntity studentCourseEntity = studentCourseRepository.findCourseRecord(sid, courseid);
			studentCourseRepository.delete(studentCourseEntity);
		}
		studentEntity = studentRepo.findOne(sid);
		studentRepo.delete(studentEntity);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	
	}
	
	@RequestMapping(value="/courses", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseStatus(HttpStatus.OK)
	public List<CourseEntity> getCourses(){
		List<CourseEntity> courseEntity = courseRepository.findAllRecords();
		return courseEntity;
		
	}

}


