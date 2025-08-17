package com.ahmetmert.controller;

import java.util.List;

import com.ahmetmert.dto.DtoCustomer;
import com.ahmetmert.dto.DtoCustomerIU;
import com.ahmetmert.dto.DtoUser;
import com.ahmetmert.entity.Customer;

public interface IRestCustomerController {
	
    public RestRootEntity<List<DtoCustomer>> getAllCustomers();


    public RestRootEntity<DtoCustomer> getCustomerById(Long id);


    public RestRootEntity<DtoCustomer> addCustomer(DtoCustomerIU dtoCustomerIU);


    public RestRootEntity<Void> deleteCustomer(Long id);
}
