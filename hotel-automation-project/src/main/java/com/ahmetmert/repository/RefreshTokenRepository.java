package com.ahmetmert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ahmetmert.entity.RefreshToken;

import java.util.List;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	// elle yazılmış hali @Query(value = "from RefreshToken r WHERE r.refreshToken = :refreshToken")
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
	
}