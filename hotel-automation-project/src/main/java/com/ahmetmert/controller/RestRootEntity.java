package com.ahmetmert.controller;



import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class RestRootEntity<T> {
	
private Integer status;
	
	private T payload;
	
	private String errorMessage;
	
	public static <T> RestRootEntity<T> ok(T payload) {
		RestRootEntity<T> rootEntity = new RestRootEntity<>();
		rootEntity.setStatus(HttpStatus.OK.value());
		rootEntity.setErrorMessage(null);
		rootEntity.setPayload(payload);
		
		return rootEntity;
	}
	
	public static <T> RestRootEntity<T> error(String errorMessage) {
		RestRootEntity<T> rootEntity = new RestRootEntity<>();
		rootEntity.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		rootEntity.setErrorMessage(errorMessage);
		rootEntity.setPayload(null);
		
		return rootEntity;
	}

}
