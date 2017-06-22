import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

import { Student } from './student';

import { Course } from './course';

@Injectable()
export class StudentService {

    allStudentsUrl = "http://localhost:8080/students";
    studentUrl = "http://localhost:8080/student";
    coursesUrl = "http://localhost:8080/courses"
	//Create constructor to get Http instance
	constructor(private http:Http) { 

	}

    getAllStudents(): Observable<Student[]> {
        return this.http.get(this.allStudentsUrl)
		   		.map(this.extractData)
		        .catch(this.handleError);

    }

     getAllCourses(): Observable<Course[]> {
        return this.http.get(this.coursesUrl)
		   		.map(this.extractData)
		        .catch(this.handleError);

    }
	

    createStudent(student: Student): Observable<number> {
	    let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.allStudentsUrl, student, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
	

    getStudentById(studentId: string): Observable<Student> {
		let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
		let cpParams = new URLSearchParams();
		cpParams.set('id', studentId);			
		let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
		return this.http.get(this.studentUrl, options)
			   .map(this.extractData)
			   .catch(this.handleError);
    }	
	

    updateStudent(student: Student):Observable<number> {
	    let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.put(this.studentUrl, student, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
    

    deleteStudentById(studentId : string): Observable<number> {
		let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
		let cpParams = new URLSearchParams();
		cpParams.set('id', studentId);			
		let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
		return this.http.delete(this.studentUrl, options)
			   .map(success => success.status)
			   .catch(this.handleError);
    }

	private extractData(res: Response) {
	    let body = res.json();
        return body;
    }

    private handleError (error: Response | any) {
		console.error(error.message || error);
		return Observable.throw(error.status);
    }
    
}