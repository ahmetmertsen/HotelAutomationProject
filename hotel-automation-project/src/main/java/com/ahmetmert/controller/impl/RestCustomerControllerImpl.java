package com.ahmetmert.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmetmert.controller.IRestCustomerController;
import com.ahmetmert.controller.RestBaseController;
import com.ahmetmert.controller.RestRootEntity;
import com.ahmetmert.dto.DtoCustomer;
import com.ahmetmert.dto.DtoCustomerIU;
import com.ahmetmert.entity.*;
import com.ahmetmert.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {
	
	@Autowired
	private ICustomerService customerService;

	@GetMapping(path =  "/list")
	@Override
	public RestRootEntity<List<DtoCustomer>> getAllCustomers() {
		return ok(customerService.getAllCustomers());
	}
	
	@GetMapping(path = "/list/{id}")
	@Override
	public RestRootEntity<DtoCustomer> getCustomerById(@PathVariable(name = "id") Long id) {
		return ok(customerService.getCustomerById(id));
	}

	@PostMapping(path = "/add")
	@Override
	public RestRootEntity<DtoCustomer> addCustomer(@RequestBody DtoCustomerIU dtoCustomerIU) {
	    return ok(customerService.saveCustomer(dtoCustomerIU));
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public RestRootEntity<Void> deleteCustomer(@PathVariable(name = "id") Long id) {
	    customerService.deleteCustomer(id);
	    return ok(null);
	}

	
	
}
