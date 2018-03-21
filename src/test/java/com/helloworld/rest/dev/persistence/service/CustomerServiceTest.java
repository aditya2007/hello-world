package com.helloworld.rest.dev.persistence.service;

import com.helloworld.rest.dev.dto.Customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	@Test
	@Transactional
	public void testCreateCustomer() {
		Customer customer = customerService.createCustomer(getCustomer());
		Assert.notNull(customer, "Customer is null");
		Assert.isTrue(customer.getFirstName().equals("Yoga"), "First name is not matching");
		Assert.isTrue(customer.getLastName().equals("Govinda Gowda"), "Last name is not matching");
		Assert.notNull(customer.getEnrolledServices(), "Enrolled Services is null");
		Assert.isTrue(customer.getEnrolledServices().size() > 0, "Enrolled services is empty");
		Assert.isTrue(customer.getEnrolledServices().contains("Internet"),
						"Enrolled services not contain 'Internet'");
	}

	@Test
	@Transactional
	public void testGetCustomer() {
		Customer cust = customerService.createCustomer(getCustomer());
		Assert.notNull(cust, "Customer is null");

		Customer customer = customerService.getCustomer(cust.getCustomerId());
		Assert.notNull(customer, "Customer is null");
		Assert.isTrue(customer.getFirstName().equals("Yoga"), "First name is not matching");
		Assert.isTrue(customer.getLastName().equals("Govinda Gowda"), "Last name is not matching");
		Assert.notNull(customer.getEnrolledServices(), "Enrolled Services is null");
		Assert.isTrue(customer.getEnrolledServices().size() == 2,
				"Enrolled services is not matching");
		Assert.isTrue(customer.getEnrolledServices().contains("Internet"),
				"Enrolled services not contain 'Internet'");
	}

	@Test
	@Transactional
	public void testDeleteCustomer() {
		Customer customer = customerService.createCustomer(getCustomer());
		Assert.notNull(customer, "Customer is null");

		customerService.deleteById(customer.getCustomerId());

		try {
			customerService.getCustomer(customer.getCustomerId());
		} catch(RuntimeException re) {
			Assert.isTrue(re.getMessage().equals("No Data Found"), "Data found");
		}
	}

	private Customer getCustomer() {
		/*Customer customer = new Customer();
		customer.setFirstName("Yoga");
		customer.setLastName("Govinda Gowda");*/
		Set<String> enrolledSvcs = new HashSet<>();
		enrolledSvcs.add("Internet");
		enrolledSvcs.add("Cable");
		//customer.setEnrolledServices(enrolledSvcs);
		Customer customer = Customer.builder()
				.firstName("Yoga")
				.lastName("Govinda Gowda")
				.enrolledServices(enrolledSvcs).build();
		return customer;
	}
}
