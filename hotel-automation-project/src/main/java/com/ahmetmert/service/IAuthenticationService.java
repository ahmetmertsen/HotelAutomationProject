package com.ahmetmert.service;

import com.ahmetmert.dto.DtoUser;
import com.ahmetmert.jwt.AuthRequest;
import com.ahmetmert.jwt.AuthResponse;
import com.ahmetmert.jwt.RefreshTokenRequest;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest authRequest);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
	
}
