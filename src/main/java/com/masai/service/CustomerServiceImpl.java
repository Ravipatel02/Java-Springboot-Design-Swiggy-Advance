package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository repo;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
	    try {
	        if (customer == null ||customer.getCustomerId()==null ||customer.getEmail()==null || customer.getUsername()== null) {
	            throw new CustomerException("customer is null data");
	        }
	        Optional<Customer> findById = this.repo.findById(customer.getCustomerId());
	        if(findById.isPresent()) {
	            throw new CustomerException("customer is allredy present");
	        	
	        }
	        Customer savedCustomer = repo.save(customer);
	        return savedCustomer;
	    } catch (CustomerException e) {
	       
	        throw e;
	    } catch (Exception e) {
	        throw new CustomerException("Error occurred while adding customer: " + e.getMessage(), e);
	    }
	}

	@Override
	public Customer getCustomer(Integer id) throws CustomerException {
		try {
			Optional<Customer> findById = this.repo.findById(id);
			if(findById.isPresent()) {
				Customer customer = findById.get();
				return customer;
			}else {
				throw new CustomerException("Customer is not present with "+id);
			}
			
		} catch (Exception e) {
			throw new CustomerException("Error occurred while find customer", e);
		}
	}

	@Override
	public Customer updateCustomerName(Integer customerId, String customerName) throws CustomerException {
		try {
			Optional<Customer> findById = this.repo.findById(customerId);
			if(findById.isPresent()) {
				Customer customer = findById.get();
				System.out.println(customerName +" my result");
				customer.setUsername(customerName.trim());
				Customer save = this.repo.save(customer);
				return save;
			}else {
				throw new CustomerException("customer is not present with "+customerId);
			}
			
		} catch (Exception e) {
			throw new CustomerException("Error occurred while update cutomer",e);
		}
	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {
	    try {
	        // Find the customer by ID
	        Optional<Customer> findById = this.repo.findById(customerId);
	        
	        if(!findById.isPresent()) {
	        	throw new CustomerException("Customer id is not presen "+customerId);
	        }
	        Customer customer = findById.get();
	        this.repo.deleteById(customerId);
	        return customer;
	    } catch (CustomerException e) {
	        throw e;
	    }catch (Exception e) {
			throw new CustomerException("Error Occured when delete cutomer ",e);
		}
	}

	@Override
	public Customer customerByName(String name) throws CustomerException {
		try {
			Customer findByUsername = this.repo.findByUsername(name);
			return findByUsername;
			
		} catch (Exception e) {
			throw new CustomerException("Error Occured while find name",e);
		}
	}

}

