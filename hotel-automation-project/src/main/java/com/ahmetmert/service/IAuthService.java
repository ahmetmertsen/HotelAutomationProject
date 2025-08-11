package com.ahmetmert.service;

import com.ahmetmert.dto.DtoUser;
import com.ahmetmert.jwt.AuthRequest;
import com.ahmetmert.jwt.AuthResponse;

public interface IAuthService {

	public DtoUser register(AuthRequest authRequest);
	
	public AuthResponse authenticate(AuthRequest request);
	
}
