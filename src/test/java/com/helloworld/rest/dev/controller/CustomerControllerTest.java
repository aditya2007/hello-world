package com.helloworld.rest.dev.controller;

import com.helloworld.rest.dev.dto.Customer;
import com.helloworld.rest.dev.persistence.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerControllerTest extends AbstractControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@Before
	public void setUp() throws NoSuchFieldException, IllegalAccessException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testQueryCustomer() {
		when(customerService.getCustomer(anyInt())).thenReturn(getCustomer(true));
		Customer customer = customerController.getCustomer(anyInt());
		assertNotNull(customer);
		assertEquals(customer.getCustomerId().intValue(), 1);
		assertEquals(customer.getFirstName(), "Yoga");
		assertEquals(customer.getEnrolledServices().size(), 2);
		assertTrue(customer.getEnrolledServices().contains("Cable"));
	}

	@Test
	public void testCreateCustomer() {

		when(customerService.createCustomer(anyObject())).thenReturn(getCustomer(true));

		Customer customer = customerController.createCustomer(anyObject());
		assertNotNull(customer);
		assertEquals(customer.getCustomerId().intValue(), 1);
		assertEquals(customer.getFirstName(), "Yoga");
		assertEquals(customer.getEnrolledServices().size(), 2);
		assertTrue(customer.getEnrolledServices().contains("Internet"));
	}

	@Test
	public void deleteCustomer() {
		doNothing().when(customerService).deleteById(anyInt());
		customerController.delete(anyInt());
	}

	private Customer getCustomer(boolean isCreateOrQuery) {
		/*Customer customer = new Customer();
		if (isCreateOrQuery) {
			customer.setCustomerId(1);
		}
		customer.setFirstName("Yoga");
		customer.setLastName("Govinda Gowda");
		Set<String> enrolledSvcs = new HashSet<>();
		enrolledSvcs.add("Internet");
		enrolledSvcs.add("Cable");
		customer.setEnrolledServices(enrolledSvcs);*/

		Set<String> enrolledSvcs = new HashSet<>();
		enrolledSvcs.add("Internet");
		enrolledSvcs.add("Cable");
		//customer.setEnrolledServices(enrolledSvcs);
		Customer customer = Customer.builder()
				.firstName("Yoga")
				.lastName("Govinda Gowda")
				.enrolledServices(enrolledSvcs).build();
		if (isCreateOrQuery) {
			customer.setCustomerId(1);
		}


		return customer;
	}

}
