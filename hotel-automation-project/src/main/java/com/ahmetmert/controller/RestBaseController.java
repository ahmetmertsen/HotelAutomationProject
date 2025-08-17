package com.ahmetmert.controller;

public class RestBaseController {
	
	public <T> RestRootEntity<T> ok(T payload) {
		return RestRootEntity.ok(payload);
	}
	
	public <T> RestRootEntity<T> error(String errorMessage) {
		return RestRootEntity.error(errorMessage);
	}

}
