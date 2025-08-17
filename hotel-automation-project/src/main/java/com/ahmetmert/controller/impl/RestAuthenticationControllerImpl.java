package com.ahmetmert.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahmetmert.controller.IRestAuthenticationController;
import com.ahmetmert.controller.RestBaseController;
import com.ahmetmert.controller.RestRootEntity;
import com.ahmetmert.dto.DtoUser;
import com.ahmetmert.jwt.AuthRequest;
import com.ahmetmert.jwt.AuthResponse;
import com.ahmetmert.jwt.RefreshTokenRequest;
import com.ahmetmert.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;
	
	
	@PostMapping("/register")
	@Override
	public RestRootEntity<DtoUser> register(@Valid @RequestBody  AuthRequest request) {
		return ok(authenticationService.register(request));
	}

	@PostMapping("/authenticate")
	@Override
	public RestRootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
		return ok(authenticationService.authenticate(request));
	}

	@PostMapping("/refreshToken")
	@Override
	public RestRootEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
		return ok(authenticationService.refreshToken(request));
	}

	
	
}
