package com.usman.contollers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RestController
@RequestMapping("/MS1")
public class SampleController {

	// Logger logger = LogManager.getLogger(SampleController.class);

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/action1", method = RequestMethod.GET)
	public void login() throws JsonProcessingException {
		System.out.println("Login action is called!!!");

		String userHeader = request.getHeader("user_id");
		String cookieHeader = request.getHeader("SET-COOKIE");

		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		System.out.println(om.writeValueAsString(request.getHeaderNames()));
		if (cookieHeader != null) {
			System.out.println(userHeader);
			System.out.println(cookieHeader);
		} else {
			System.out.println("NOT FOUND");
		}

	}

}