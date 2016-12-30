package com.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.model.Student;
import com.model.User;
import com.service.StudentService;
import com.service.UserService;

@Controller
@SessionAttributes("loggedInUser")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	
	@RequestMapping({"/index", "/"})
	public String setUpForm(Map<String, Object> map, Principal principal){
		System.out.println("loggged in user :"+principal.getName());
		
		User loggedInUser = userService.findUser("251055");
		System.out.println("user name :"+loggedInUser.getUsername());
		
		Student student = new Student();
		map.put("student", student);
		map.put("loggedInUser",loggedInUser);
		map.put("studentList", studentService.getAllStudent());
		return "student";
	}
	
	
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public String doActions(@ModelAttribute Student student, BindingResult result, @RequestParam String action, Map<String, Object> map){
		Student studentResult = new Student();
		if(action.equalsIgnoreCase("add")){
			studentService.addStudent(student);
			studentResult = student;
		}
		map.put("student", studentResult);
		map.put("studentList", studentService.getAllStudent());
		return "student";
	}
	
}
