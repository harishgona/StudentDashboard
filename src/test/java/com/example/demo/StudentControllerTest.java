package com.example.demo;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentRepository studentRepo;
	
	@MockBean
	private StudentCourseRepository studentCourseRepository;
	
	@MockBean
	private CourseRepository courseRepository;
	
	String exampleStudentJson = "{ \"studentname\": \"Harish\",\"degree\": \"MS\",\"major\": \"CE\",\"courseids\": [1, 2]}";
	
	String exampleStudentJson1 = "{\"studentId\": 2, \"studentname\": \"Harish\",\"degree\": \"MS\",\"major\": \"CE\",\"courseids\": [1, 2]}";
	
	@Test
	public void getStudent() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/student?id=2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{studentname: Harish, degree: MS, major: CE,studentId: 2}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	@Test
	public void saveStudents() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students")
				.accept(MediaType.APPLICATION_JSON).content(exampleStudentJson)
				.contentType(MediaType.APPLICATION_JSON);	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/student/?id=2", response.getHeader(HttpHeaders.LOCATION));
	}
	
	@Test 
	public void updateStudent() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/student")
				.accept(MediaType.APPLICATION_JSON).content(exampleStudentJson1)
				.contentType(MediaType.APPLICATION_JSON);	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("http://localhost/student/?id=2", response.getHeader(HttpHeaders.LOCATION));
		
		
	}
	
	@Test
	public void deleteStudent() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/student?id=2");	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		
	}
	
	
}
	


	
	

