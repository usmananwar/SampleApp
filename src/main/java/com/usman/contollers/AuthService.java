package com.usman.contollers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	JwtProvider tokenProvider;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserHelper userHelper;

	public String signIn(SignInVO loginVO) {
		User user = userRepository.findByUsernameAndPassword(loginVO.getUsername(), loginVO.getPassword());
		if (user == null) {
			throw new RuntimeException("Invalid credentials");
		}
		String jwt = tokenProvider.generateToken(user.getUsername(), new ArrayList<String>());
		return jwt;
	}

	public void signUp(SignUpVO signUpVO) {
		User user = userHelper.convertToUser(signUpVO);
		userRepository.save(user);
	}

}
