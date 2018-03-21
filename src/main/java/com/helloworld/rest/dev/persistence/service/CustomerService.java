package com.helloworld.rest.dev.persistence.service;

import com.helloworld.rest.dev.annotations.Mirror;
import com.helloworld.rest.dev.dto.Customer;

public interface CustomerService {

	Customer createCustomer(@Mirror Customer customer);

	Customer getCustomer(Integer id);

	void deleteById(Integer id);
}