package com.ahmetmert.services;

import com.ahmetmert.jwt.AuthResponse;
import com.ahmetmert.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {
	
	public AuthResponse refreshToken(RefreshTokenRequest request);

}
