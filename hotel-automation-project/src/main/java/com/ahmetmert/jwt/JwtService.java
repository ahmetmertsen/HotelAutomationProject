package com.ahmetmert.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	public static final String SECRET_KEY = "DrONYW8B7ctnd+r4+eRzMmlj/GbC94mYgS+xE3rG5IY=";

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claimsMap = new HashMap<>();
		claimsMap.put("role", "ADMIN");
			
		return Jwts.builder()
		.setSubject(userDetails.getUsername())
		.addClaims(claimsMap)
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2))
		.signWith(getKey(), SignatureAlgorithm.HS256)
		.compact();
	}
	
	
	public Object getClaimsByKey(String token , String key) {
		Claims claims = getClaims(token);
		return claims.get(key);
	}
	
	public Claims getClaims(String token) {
		Claims claims = Jwts
			.parserBuilder()
			.setSigningKey(getKey())
			.build()
			.parseClaimsJws(token).getBody();
		return claims;
	}
	
	public <T> T exportToken(String token , Function<Claims, T> claimsFunction) {
		Claims claims = getClaims(token);
		return claimsFunction.apply(claims);
	}
	
	
	public String getUsernameByToken(String token) {
		return exportToken(token, Claims::getSubject);
	}
	
	
	public boolean isTokenExpired(String token) {
		Date expiredDate =  exportToken(token, Claims::getExpiration);
		//12.26
		//expiredDate : 12.36
		return new Date().before(expiredDate);
	}
	
	
	public Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}

