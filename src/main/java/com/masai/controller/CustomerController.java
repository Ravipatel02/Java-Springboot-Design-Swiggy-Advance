package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerServiceImpl service;
	
	@PostMapping("/")
	public Customer addCustomer(@RequestBody Customer customer) {
		Customer addCustomer = this.service.addCustomer(customer);
		return addCustomer;
		
	}
	@GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
    	Customer customer = this.service.getCustomer(id);
    	return customer;
    	
    }
	@PutMapping("/{customerId}/{customerName}")
    public  Customer updateCustomerName(@PathVariable Integer customerId,@PathVariable String customerName){
		System.out.println(customerName);
		Customer updateCustomerName = this.service.updateCustomerName(customerId, customerName);
		return updateCustomerName;
    	
    }
	
	@DeleteMapping("/{customerId}")
    public Customer deleteCustomer(@PathVariable Integer customerId){
		Customer deleteCustomer = this.service.deleteCustomer(customerId);
		return deleteCustomer;
    	
    }
	@GetMapping("/names/{name}")
    public  Customer customerByName(@PathVariable String name){
    	Customer customerByName = this.service.customerByName(name);
    	return customerByName;
    	
    }

}
