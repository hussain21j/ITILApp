package com.service;

import java.util.List;

import com.model.Student;

public interface StudentService {
	public void addStudent(Student student);
	public List<Student> getAllStudent();
}
