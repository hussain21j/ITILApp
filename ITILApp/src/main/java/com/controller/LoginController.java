package com.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String doLogin(){
		System.out.println("start of the method doLogin");
		return "login";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testAction(Map<String, Object> map){
		System.out.println("testAction link");
		map.put("testmessage", "this is message from the test action");
		return "test";
	}
}
