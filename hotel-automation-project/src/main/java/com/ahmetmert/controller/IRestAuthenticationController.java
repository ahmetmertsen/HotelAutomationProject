package com.ahmetmert.controller;

import com.ahmetmert.dto.DtoUser;
import com.ahmetmert.jwt.AuthRequest;
import com.ahmetmert.jwt.AuthResponse;
import com.ahmetmert.jwt.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RestRootEntity<DtoUser> register(AuthRequest request);
	
	public RestRootEntity<AuthResponse> authenticate(AuthRequest request);
	
	public RestRootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}