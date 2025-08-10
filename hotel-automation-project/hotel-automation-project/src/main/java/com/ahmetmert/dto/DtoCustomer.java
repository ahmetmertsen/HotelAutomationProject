package com.ahmetmert.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomer {
	
	private Long id;
	
	private String fullName;
	
	private String phone;
	
}
