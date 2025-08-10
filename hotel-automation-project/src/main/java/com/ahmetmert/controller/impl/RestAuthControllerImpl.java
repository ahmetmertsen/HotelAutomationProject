package com.ahmetmert.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahmetmert.controller.IRestAuthController;
import com.ahmetmert.dto.DtoUser;
import com.ahmetmert.jwt.AuthRequest;
import com.ahmetmert.jwt.AuthResponse;
import com.ahmetmert.jwt.RefreshTokenRequest;
import com.ahmetmert.services.IAuthService;
import com.ahmetmert.services.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

	@Autowired
	private IAuthService authService;
	
	@Autowired
	private IRefreshTokenService refreshTokenService;
	
	
	@PostMapping("/register")
	@Override
	public DtoUser register(@Valid @RequestBody  AuthRequest request) {
		
		return authService.register(request);
	}

	@PostMapping("/authenticate")
	@Override
	public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
		return authService.authenticate(request);
	}

	@PostMapping("/refreshToken")
	@Override
	public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
		return refreshTokenService.refreshToken(request);
	}

	
	
}
