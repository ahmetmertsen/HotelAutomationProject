package com.ahmetmert.controller;

import java.util.List;

import com.ahmetmert.dto.DtoCustomer;
import com.ahmetmert.dto.DtoCustomerIU;
import com.ahmetmert.entities.Customer;

public interface IRestCustomerController {
	
    public List<DtoCustomer> getAllCustomers();


    public DtoCustomer getCustomerById(Long id);


    public DtoCustomer addCustomer(DtoCustomerIU dtoCustomerIU);


    public void deleteCustomer(Long id);
}
