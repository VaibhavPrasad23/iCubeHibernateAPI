package com.ics.api.Hibernate;

import java.util.List;

import com.ics.api.Hibernate.dao.StudentDao;
import com.ics.api.Hibernate.entity.Student;


public class App {
	public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
		Student student = new Student("Vaibhav", "Prasad", "vaibhavprasad@iqbsys.com");
		studentDao.saveStudent(student);
		
		List<Student> students = studentDao.getStudents();
		for (Student s : students)
		    System.out.println(s.getFirstName());
	}
}
