package com.ahmetmert.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomerIU {
	
	@NotBlank
    @Size(min = 11, max = 11)
	private String identityNumber;
	
	@NotBlank
	private String fullName;
	
	@NotNull
	@Past
	private LocalDate birthDate;
	
	@NotBlank
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Geçerli bir telefon numarası giriniz.")
	private String phone;
	
}
