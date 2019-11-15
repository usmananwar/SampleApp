package com.usman.contollers;

import org.springframework.stereotype.Component;

@Component
public class UserHelper {

	public User convertToUser(SignUpVO signUpVO) {
		return User.builder().username(signUpVO.getUsername()).password(signUpVO.getPassword())
				.firstName(signUpVO.getFirstName()).lastName(signUpVO.getLastName()).email(signUpVO.getEmail()).build();
	}

}
