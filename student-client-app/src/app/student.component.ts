import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { StudentService } from './student.service';
import { Student } from './student';
import { Course } from './course';

@Component({
   selector: 'app-student',
   templateUrl: './student.component.html',
   styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit { 
   courses: Course[];
   allStudents: Student[];
   statusCode: number;
   requestProcessing = false;
   studentIdToUpdate = null;
   processValidation = false;
   studentForm = new FormGroup({
       studentname: new FormControl('', Validators.required),
       degree: new FormControl('', Validators.required),
       major: new FormControl('', Validators.required),  
       courseids: new FormControl('', Validators.required)    	   
   });

   //Constructor to get service instance
   constructor(private studentService: StudentService) {
   }
  
   ngOnInit(): void {
	   this.getAllStudents();
      this.getAllCourses();
   }   
  
   getAllStudents() {
        this.studentService.getAllStudents()
		  .subscribe(
                data => this.allStudents = data,
                errorCode =>  this.statusCode = errorCode);   
   }

   getAllCourses() {
        this.studentService.getAllCourses()
        .subscribe(
                data => this.courses = data,
                errorCode =>  this.statusCode = errorCode);   
   }



   
   onStudentFormSubmit() {
	   this.processValidation = true;   
      if (this.studentForm.invalid) {
	        return; 
	  }   
      this.preProcessConfigurations();
	   let studentname = this.studentForm.get('studentname').value.trim();
      let degree = this.studentForm.get('degree').value.trim();
      let major = this.studentForm.get('major').value.trim();
      let courseids = this.studentForm.get('courseids').value; 	
	  if (this.studentIdToUpdate === null) {  
	    let student= new Student(null, studentname, degree, major, courseids);	  
	    this.studentService.createStudent(student)
	      .subscribe(successCode => {
		            this.statusCode = successCode;
				    this.getAllStudents();	
					this.backToCreateStudent();
			    },
		        errorCode => this.statusCode = errorCode);
	  } else {  
   	    //Handle update student
	    let student= new Student(this.studentIdToUpdate, studentname, degree, major, courseids);	  
	    this.studentService.updateStudent(student)
	      .subscribe(successCode => {
		            this.statusCode = successCode;
				    this.getAllStudents();	
					this.backToCreateStudent();
			    },
		        errorCode => this.statusCode = errorCode);	  
	  }
   }
   //Load student by id to edit
   loadStudentToEdit(studentId : string) {
      this.preProcessConfigurations();
      this.studentService.getStudentById(studentId)
	      .subscribe(student => {
		            this.studentIdToUpdate = student.studentId;   
		            this.studentForm.setValue({ studentname: student.studentname, degree: student.degree, major: student.major, courseids: student.courseids});
					this.processValidation = true;
					this.requestProcessing = false;   
		        },
		        errorCode =>  this.statusCode = errorCode);   
   }
   //Delete student
   deleteStudent(studentId: string) {
      this.preProcessConfigurations();
      this.studentService.deleteStudentById(studentId)
	      .subscribe(successCode => {
		            this.statusCode = successCode;
				    this.getAllStudents();	
				    this.backToCreateStudent();
			    },
		        errorCode => this.statusCode = errorCode);    
   }
   //Perform preliminary processing configurations
   preProcessConfigurations() {
      this.statusCode = null;
	  this.requestProcessing = true;   
   }
   //Go back from update to create
   backToCreateStudent() {
      this.studentIdToUpdate = null;
      this.studentForm.reset();	  
	  this.processValidation = false;
   }
}
    