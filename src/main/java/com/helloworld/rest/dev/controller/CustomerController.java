package com.helloworld.rest.dev.controller;

import com.helloworld.rest.dev.dto.Customer;
import com.helloworld.rest.dev.persistence.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * http://localhost:8080/swagger-ui.html
 *
 * This class exposes following REST endpoints,
 * to demonstrate create, query and delete of system of records
 * using in memory database.
 *
 * - GET     /v1/customer/2
 * - POST    /v1/customer
 * - DELETE  /v1/customer/2
 */
@Api(value = "customer", description = "This class exposes following REST endpoints,"
	+ "to demonstrate create, delete and query of Customer")
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * Query the database to get the Customer for a given customer id.
	 *
	 * @param id - The customer id
	 * @return Customer - Customer (see @com.helloworld.rest.dev.dto.Customer)
	 * 					  data for a given id.
	 */
	@ApiOperation(value = "Query a customer for a given id", response = Customer.class)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}",
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomer(@PathVariable("id") Integer id) {
		return customerService.getCustomer(id);
	}

	/**
	 * Create a new system of record of type Customer
	 *
	 * @param customer -
	 * @return Customer - Newly created Customer
	 * 					(see @com.helloworld.rest.dev.dto.Customer)
	 */
	@ApiOperation(value = "Add a customer", response = Customer.class)
	@RequestMapping(method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer createCustomer(@RequestBody Customer customer) {
		return  customerService.createCustomer(customer);
	}

	/**
	 * Delete the system of record of type Customer
	 *
	 * @param id - Customer Id
	 */
	@ApiOperation(value = "Remove a customer for a given id", response= Void.class)
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}",
					produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("id") Integer id) {
		customerService.deleteById(id);
	}
}
