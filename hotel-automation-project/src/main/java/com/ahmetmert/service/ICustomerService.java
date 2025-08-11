package com.ahmetmert.service;

import java.util.List;

import com.ahmetmert.dto.DtoCustomer;
import com.ahmetmert.dto.DtoCustomerIU;
import com.ahmetmert.entity.Customer;

public interface ICustomerService {

	public List<DtoCustomer> getAllCustomers();
	
	public DtoCustomer getCustomerById(Long id);
	
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public void deleteCustomer(Long id);
}
