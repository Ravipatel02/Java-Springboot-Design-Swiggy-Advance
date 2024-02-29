package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws CustomerException;
    Customer getCustomer(Integer id) throws CustomerException;
    Customer updateCustomerName(Integer customerId, String customerName) throws CustomerException;
    Customer deleteCustomer(Integer customerId) throws CustomerException;
    Customer customerByName(String name) throws CustomerException;
}
