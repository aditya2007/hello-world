package com.helloworld.rest.dev.persistence.repository;

import com.helloworld.rest.dev.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

	CustomerEntity findById(Integer id);

	void deleteById(Integer id);

}
