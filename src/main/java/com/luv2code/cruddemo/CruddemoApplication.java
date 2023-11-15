package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.dao.AppDAOImpl;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.text.TabExpander;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createCourseAndStudents(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {

		int theId = 1;
		System.out.println("deleting student id: "+theId);

		appDAO.deleteStudentById(theId);
		System.out.println("DONE!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		//create more courses
		Course tempCourse1 = new Course("Rubik's Cube");
		Course tempCourse2 = new Course("Atari 2006");

		//add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating student: "+tempStudent);
		System.out.println("associated courses: "+tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("DONE!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("loaded student: "+tempStudent);
		System.out.println("courses: "+tempStudent.getCourses());

		System.out.println("DONE!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theId = 10;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("loaded course: "+tempCourse);
		System.out.println("Students: "+tempCourse.getStudents());
		System.out.println("DONE!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		//create a course
		Course tempCourse = new Course(
				"Pacman - how to score 1mil points"
		);

		//create the students
		Student tempStudent1 = new Student("John","Doe","john@luv2code.com");
		Student tempStudent2 = new Student("Mary","Public","mary@luv2code.com");

		//add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the course and associated students
		System.out.println("saving the course: "+tempCourse);
		System.out.println("associated students: "+tempCourse.getStudents());

		appDAO.save(tempCourse);
		System.out.println("DONE!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		//get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		//print the course
		System.out.println(tempCourse);

		//print the reviews
		System.out.println(tempCourse.getReviews());
		System.out.println("DONE!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		//create a course
		Course tempCourse = new Course("Pacman - How to Score 1 mil points");

		//add some reviews
		tempCourse.addReview(new Review("Great course...loved it"));
		tempCourse.addReview(new Review("Cool course...job well done"));
		tempCourse.addReview(new Review("What a dumb course, idiot"));

		//save the course
		System.out.println("saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);
		System.out.println("DONE!!!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("deleting course with id: "+theId);
		appDAO.deleteCourseById(theId);
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		//find the course
		System.out.println("course id: "+theId);
		Course tempCourse = appDAO.findCourseById(theId);
		tempCourse.setTitle("Enjoyyyy");
		appDAO.update(tempCourse);
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("instructor: "+tempInstructor);
		tempInstructor.setLastName("TESTER");
		appDAO.update(tempInstructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		System.out.println("instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor "+tempInstructor);
		System.out.println("the associated courses : "+tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findCoursesWithInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);

		//find courses for instructor
		System.out.println("finding courses for instructor id: "+theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		//associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("courses: "+tempInstructor.getCourses());

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses "+tempInstructor.getCourses());

	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		//crate the instructor
		Instructor tempInstructor =
				new Instructor("Susan","Public","susan@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.youtube.com",
						"Video games");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar");
		Course tempCourse2 = new Course("The pinball");

		//add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save the instructor
		System.out.println("saving instructor id: "+tempInstructor);
		System.out.println("the course: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteInstructorById(theId);
	}

	private void findInstructorDetail(AppDAO appDAO) {
		//get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		//print the instructor detail
		System.out.println("tempInstructorDetail: "+tempInstructorDetail);

		//print the associated instructor
		System.out.println("the associated instructor: "+tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associate instructorDetail only: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		//crate the instructor
		Instructor tempInstructor =
				new Instructor("Madhu","Patel","madhu@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		//
		//NOTE: this will save the details object
		//because of Cascade.ALL
		//
		System.out.println("Saving instructor: "+tempInstructor);
		appDAO.save(tempInstructor);
	}
}
