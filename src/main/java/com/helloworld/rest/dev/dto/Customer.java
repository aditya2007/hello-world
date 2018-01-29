package com.helloworld.rest.dev.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Serializable {

	private Integer customerId;
	private String firstName;
	private String lastName;
	private Set<String> enrolledServices;

}
