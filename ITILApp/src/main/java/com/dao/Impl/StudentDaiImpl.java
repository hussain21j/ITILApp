package com.dao.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.StudentDao;
import com.model.Student;

@Repository
public class StudentDaiImpl implements StudentDao {
	@Autowired
	private SessionFactory session;
	
	public void addStudent(Student student) {
		session.getCurrentSession().save(student);
	}
	
	public List<Student> getAllStudent() {
		System.out.println("get All student");
		return session.getCurrentSession().createQuery("from Student").list();
	}	

}
