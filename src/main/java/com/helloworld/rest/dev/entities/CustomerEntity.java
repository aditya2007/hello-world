package com.helloworld.rest.dev.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class CustomerEntity extends BaseEntity implements Serializable {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	private Set<EnrolledServiceEntity> services;

	@Builder
	private CustomerEntity(Integer id, String firstName, String lastName, Set<EnrolledServiceEntity> services) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.services = services;
	}
}

