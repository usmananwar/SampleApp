package com.usman.contollers;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpVO {

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@Nullable
	@Email
	private String email;

}
