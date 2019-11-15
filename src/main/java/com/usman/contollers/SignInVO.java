package com.usman.contollers;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInVO {

	@NotNull
	private String username;

	@NotNull
	private String password;

}
