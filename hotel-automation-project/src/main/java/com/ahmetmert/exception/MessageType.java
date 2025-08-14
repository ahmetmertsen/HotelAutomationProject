package com.ahmetmert.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004" , "Kayıt Bulunamadı."),
	DUPLICATE_RECORD("1005", "Kayıt zaten mevcut"),
	ROOM_OCCUPIED("1006", "Oda zaten dolu."),
	INVALID_OPERATION("1007", "Geçersiz işlem."),
	GENEREL_EXCEPTION("9999" , "Genel bir hata oluştu" );
	
	private String code;
	
	private String message;
	
	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
