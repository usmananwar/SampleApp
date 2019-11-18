package com.usman.contollers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/MS1")
public class SampleController {

	// Logger logger = LogManager.getLogger(SampleController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletRequest response;

	/*
	 * @Autowired private ElastiCacheClient elastiCacheClient;
	 * 
	 */
	@Autowired
	AuthService authService;

	@PostMapping(value = "/signUp")
	@ApiOperation(value = "User Registration", notes = "Signs up a user to the system")
	public void signUp(
			@ApiParam(value = "User's registration details", required = true) @RequestBody @Valid SignUpVO signUpVO) {

		// log.debug("Performing signUp!");
		authService.signUp(signUpVO);
	}

	@PostMapping(value = "/signIn")
	@ApiOperation(value = "User login", notes = "Lets a user to login and receive a jwt")
	public String signIn(
			@ApiParam(value = "User's credentials to perform the login operation", required = true) @RequestBody @Valid SignInVO loginVO,
			HttpServletResponse response) {

		// log.debug("Performing signIn!");
		String jwt = authService.signIn(loginVO);

		response.setHeader("SET-COOKIE", CookieUtil.prepareCookieHeader("jwt", jwt));

		return jwt;
	}

	@GetMapping(value = "/authCheck")
	@ApiOperation(value = "Verifies the user_id and SET-COOKIE header", notes = "Reads and returns the user_id and SET-COOKIE header values")
	public String authCheck() {
		String userHeader = request.getHeader("user_id");
		String setCookieHeader = request.getHeader("SET-COOKIE");
		
		System.out.println(setCookieHeader);
		
		return "Found user_id: " + userHeader + " and SET-COOKIE: " + setCookieHeader;
	}

	

}