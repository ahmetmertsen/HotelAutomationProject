package com.ahmetmert.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004" , "Kayıt Bulunamadı."),
	DUPLICATE_RECORD("1005", "Kayıt zaten mevcut"),
	ROOM_OCCUPIED("1006", "Oda zaten dolu."),
	INVALID_OPERATION("1007", "Geçersiz işlem."),
	TOKEN_IS_EXPIRED("1008", "Token'ın süresi bitmiştir."),
	USERNAME_NOT_FOUND("1009" , "Username bulunamadı."),
	USERNAME_OR_PASSWORD_INVALID("1010", "Kullanıcı adı veya şifre hatalı."),
	REFRESH_TOKEN_NOT_FOUND("1011", "Refresh Token Bulunamadı."),
	REFRESH_TOKEN_IS_EXPIRED("1012", "Refresh Token'ın süresi bitmiştir."),
	GENEREL_EXCEPTION("9999" , "Genel bir hata oluştu" );
	
	private String code;
	
	private String message;
	
	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
