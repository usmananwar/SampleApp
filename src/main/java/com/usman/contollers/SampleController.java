package com.usman.contollers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.usman.aws.ElastiCacheClient;

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

	/*@Autowired
	private ElastiCacheClient elastiCacheClient;
*/
	@Autowired
	AuthService authService;

	@PostMapping(value = "/signUp")
	@ApiOperation(value = "User Registration", notes = "Signs up a user to the system")
	public void signUp(
			@ApiParam(value = "User's registration details", required = true) @RequestBody @Valid SignUpVO signUpVO) {

		//log.debug("Performing signUp!");
		authService.signUp(signUpVO);
	}

	@PostMapping(value = "/signIn")
	@ApiOperation(value = "User login", notes = "Lets a user to login and receive a jwt")
	public String signIn(
			@ApiParam(value = "User's credentials to perform the login operation", required = true) @RequestBody @Valid SignInVO loginVO,
			HttpServletResponse response) {

		//log.debug("Performing signIn!");
		String jwt = authService.signIn(loginVO);

		response.setHeader("SET-COOKIE", CookieUtil.prepareCookieHeader("jwt", jwt));

		return jwt;
	}

	@RequestMapping(value = "/action1", method = RequestMethod.GET)
	public String login() throws JsonProcessingException {
		System.out.println("Login action is called!!!");

		String userHeader = request.getHeader("user_id");
		/*String cookieHeader = request.getHeader("SET-COOKIE");

		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		System.out.println(om.writeValueAsString(request.getHeaderNames()));
		if (cookieHeader != null) {
			System.out.println(userHeader);
			System.out.println(cookieHeader);
		} else {
			System.out.println("NOT FOUND");
		}
*/
		System.out.println(response.getHeader("SET-COOKIE"));
		
		return userHeader;

	}
/*
	@RequestMapping(value = "/redisTest", method = RequestMethod.GET)
	public boolean redisTest() {
		return elastiCacheClient.test();
	}

	@RequestMapping(value = "/setRedis", method = RequestMethod.GET)
	public String setRedis(@RequestParam String key, @RequestParam String value) {
		return elastiCacheClient.set(key, value);
	}

	@RequestMapping(value = "/getRedis", method = RequestMethod.GET)
	public String getRedis(@RequestParam String key) {
		return elastiCacheClient.get(key);
	}*/

}