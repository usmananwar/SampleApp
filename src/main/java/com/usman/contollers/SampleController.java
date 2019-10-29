package com.usman.contollers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		System.out.println("Login action is called!!!");
	}

}