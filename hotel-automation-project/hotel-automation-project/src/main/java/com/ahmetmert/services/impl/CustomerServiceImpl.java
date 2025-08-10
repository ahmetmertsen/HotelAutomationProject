package com.ahmetmert.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmetmert.dto.DtoCustomer;
import com.ahmetmert.dto.DtoCustomerIU;
import com.ahmetmert.entities.Customer;
import com.ahmetmert.repository.CustomerRepository;
import com.ahmetmert.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
    public List<DtoCustomer> getAllCustomers() {
    	List<DtoCustomer> dtoList = new ArrayList<>();
		List<Customer> dbList = customerRepository.findAll();
		
		for (Customer customer : dbList) {
			DtoCustomer dtoCustomer = new DtoCustomer(); 
			BeanUtils.copyProperties(customer, dtoCustomer);
			dtoList.add(dtoCustomer);
		}
		return dtoList;
    }

    public DtoCustomer getCustomerById(Long id) {
    	DtoCustomer dtoCustomer = new DtoCustomer();
        Optional<Customer> optional = customerRepository.findById(id);
        
        if(optional.isEmpty()) {
        	return null;
        }
        Customer customer = optional.get();
        BeanUtils.copyProperties(customer, dtoCustomer);
        return dtoCustomer;
    }

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
    	DtoCustomer responseCustomer = new DtoCustomer();
    	
        Customer customer = new Customer();
        customer.setIdentityNumber(dtoCustomerIU.getIdentityNumber());
        customer.setFullName(dtoCustomerIU.getFullName());
        customer.setBirthDate(dtoCustomerIU.getBirthDate());
        customer.setPhone(dtoCustomerIU.getPhone());
        
        Customer dbCustomer = customerRepository.save(customer);
        BeanUtils.copyProperties(customer, responseCustomer);
        return responseCustomer;
    }

    public void deleteCustomer(Long id) {
    	Optional<Customer> optional = customerRepository.findById(id);
		if(optional.isPresent()) {
			customerRepository.delete(optional.get());
		}
    }

}
