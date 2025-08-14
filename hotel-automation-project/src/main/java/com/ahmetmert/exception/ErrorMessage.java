package com.ahmetmert.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

	private MessageType messageType;

	private String ofStatic;
	
	//hata mesajı hazırla
	public String prepareErrorMessage() {
		StringBuilder builder = new StringBuilder(); // Stringleri birleştirmek için kullanılan bir sınıf.
		builder.append(messageType.getMessage());
		if (this.ofStatic!=null) {
			builder.append(" : " + ofStatic); 
		}
		return builder.toString();
	}
	
}
