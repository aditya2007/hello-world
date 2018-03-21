package com.helloworld.rest.dev.persistence.service;

import com.helloworld.rest.dev.annotations.Mirror;
import com.helloworld.rest.dev.dto.Customer;
import com.helloworld.rest.dev.persistence.entities.CustomerEntity;
import com.helloworld.rest.dev.persistence.entities.EnrolledServiceEntity;
import com.helloworld.rest.dev.persistence.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	public Customer createCustomer(@Mirror Customer customer) {
		return toCustomer(customerRepository.save(toCustomerEntity(customer)));
	}

	public Customer getCustomer(Integer id) {
		return toCustomer(customerRepository.findById(id));
	}

	@Transactional
	public void deleteById(Integer id) {
		customerRepository.deleteById(id);;
	}

	private Customer toCustomer(CustomerEntity entity) {
		if (Objects.isNull(entity)) {
			throw new RuntimeException("No Data Found");
		}

		Customer customer = Customer.builder()
				.customerId(entity.getId())
				.firstName(entity.getFirstName())
				.lastName(entity.getLastName())
				.enrolledServices(entity.getServices().stream()
						.map(service -> service.getServiceName()).collect(Collectors.toSet())).build();
//		customer.setCustomerId(entity.getId());
//		customer.setFirstName(entity.getFirstName());
//		customer.setLastName(entity.getLastName());
//		customer.setEnrolledServices(entity.getServices().stream()
//				.map(service -> service.getServiceName()).collect(Collectors.toSet()));
		return customer;
	}

	private CustomerEntity toCustomerEntity(Customer customer) {
		CustomerEntity customerEntity = CustomerEntity.builder()
										.firstName(customer.getFirstName())
										.lastName(customer.getLastName()).build();

		Set<EnrolledServiceEntity> enrolledServiceEntities = customer.getEnrolledServices().stream()
				.map(svcName -> EnrolledServiceEntity.builder()
						.serviceName(svcName).customer(customerEntity).build()).collect(Collectors.toSet());

		customerEntity.setServices(enrolledServiceEntities);
		return customerEntity;
	}

}
