package com.ahmetmert.services;

import java.util.List;

import com.ahmetmert.dto.DtoCustomer;
import com.ahmetmert.dto.DtoCustomerIU;
import com.ahmetmert.entities.Customer;

public interface ICustomerService {

	public List<DtoCustomer> getAllCustomers();
	
	public DtoCustomer getCustomerById(Long id);
	
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public void deleteCustomer(Long id);
}
