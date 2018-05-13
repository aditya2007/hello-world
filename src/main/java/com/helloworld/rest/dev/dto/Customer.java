package com.helloworld.rest.dev.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Serializable {

	private Integer customerId;
	private String firstName;
	private String lastName;
	private Set<String> enrolledServices;

	@Builder
	private Customer(Integer customerId, String firstName, String lastName, Set<String> enrolledServices) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enrolledServices = enrolledServices;
	}

}
