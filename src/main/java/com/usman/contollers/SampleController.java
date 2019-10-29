package com.usman.contollers;




import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usman.aws.CognitoService;

@RestController
@RequestMapping("/sample")
public class SampleController {

	//Logger logger = LogManager.getLogger(SampleController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CognitoService cognitoService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		System.out.println("Login action is called!!!");
		cognitoService.authenticate("usman.a", "usman123");
	}

}