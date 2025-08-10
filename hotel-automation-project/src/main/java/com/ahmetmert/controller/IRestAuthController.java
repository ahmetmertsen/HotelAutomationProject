package com.ahmetmert.controller;

import com.ahmetmert.dto.DtoUser;
import com.ahmetmert.jwt.AuthRequest;
import com.ahmetmert.jwt.AuthResponse;
import com.ahmetmert.jwt.RefreshTokenRequest;

public interface IRestAuthController {

	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
}