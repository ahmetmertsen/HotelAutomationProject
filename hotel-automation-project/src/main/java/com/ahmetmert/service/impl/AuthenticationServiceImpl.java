package com.ahmetmert.service.impl;

import java.net.Authenticator.RequestorType;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ahmetmert.dto.DtoUser;
import com.ahmetmert.jwt.RefreshTokenRequest;
import com.ahmetmert.entity.RefreshToken;
import com.ahmetmert.entity.User;
import com.ahmetmert.exception.BaseException;
import com.ahmetmert.exception.ErrorMessage;
import com.ahmetmert.exception.MessageType;
import com.ahmetmert.jwt.AuthRequest;
import com.ahmetmert.jwt.AuthResponse;
import com.ahmetmert.jwt.JwtService;
import com.ahmetmert.repository.RefreshTokenRepository;
import com.ahmetmert.repository.UserRepository;
import com.ahmetmert.service.IAuthenticationService;


@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	
	@Override
	public DtoUser register(AuthRequest input) {
		DtoUser dtoUser = new DtoUser();
		
		User savedUser = userRepository.save(createUser(input));
		BeanUtils.copyProperties(savedUser, dtoUser);
		
		return dtoUser;
	}
	
	
	@Override
	public AuthResponse authenticate(AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken auth =
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
			authenticationProvider.authenticate(auth); // kullanıcının kullanıcı adı ve şifresi doğru mu diye kontrol ediliyor.
			
			Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
			String accessToken = jwtService.generateToken(optionalUser.get());
			
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optionalUser.get()));
			
			return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
			
		} catch (Exception e) {
			throw new BaseException(
					new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID , e.getMessage()));
		}
	}
	
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if (optionalRefreshToken.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken()));
		}
		if (!isValidRefreshToken(optionalRefreshToken.get().getExpireDate())) {
			throw new BaseException(
					new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, request.getRefreshToken()));
		}
		
		User user = optionalRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}
	
	
	private User createUser(AuthRequest input) {
		User user = new User();
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		
		return user;
	}
	
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setUser(user);
		
		return refreshToken;
	}
	

	public boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}
	
}

