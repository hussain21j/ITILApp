package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.StudentDao;
import com.model.Student;
import com.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentDao;
	
	@Transactional
	public void addStudent(Student student) {
		studentDao.addStudent(student);
	}
	
	@Transactional
	public List<Student> getAllStudent() {
		return studentDao.getAllStudent();
	}

}
