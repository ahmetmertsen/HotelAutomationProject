package com.ahmetmert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmetmert.dto.DtoCustomer;
import com.ahmetmert.dto.DtoCustomerIU;
import com.ahmetmert.entity.Customer;
import com.ahmetmert.exception.BaseException;
import com.ahmetmert.exception.ErrorMessage;
import com.ahmetmert.exception.MessageType;
import com.ahmetmert.repository.CustomerRepository;
import com.ahmetmert.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
    public List<DtoCustomer> getAllCustomers() {
    	List<DtoCustomer> dtoList = new ArrayList<>();
		List<Customer> dbList = customerRepository.findAll();
		if (dbList.isEmpty()) {
		    throw new BaseException(
		    		new ErrorMessage(MessageType.NO_RECORD_EXIST, null));
		}
		
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
        	throw new BaseException(
        			new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
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
        
        try {
            Customer dbCustomer = customerRepository.save(customer);
            BeanUtils.copyProperties(dbCustomer, responseCustomer);
            return responseCustomer;
        } catch (Exception e) {
            throw new BaseException(
            		new ErrorMessage(MessageType.GENEREL_EXCEPTION, e.getMessage()));
        }
    }

    public void deleteCustomer(Long id) {
    	Optional<Customer> optional = customerRepository.findById(id);
		if(optional.isEmpty()) {
			throw new BaseException(
					new ErrorMessage( MessageType.NO_RECORD_EXIST, id.toString()));
		}
		customerRepository.delete(optional.get());
    }

}
