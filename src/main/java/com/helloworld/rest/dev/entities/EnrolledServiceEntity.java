package com.helloworld.rest.dev.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enrolled_service")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class EnrolledServiceEntity extends BaseEntity implements Serializable {

	@Column(name = "service_name")
	private String serviceName;

	@ManyToOne
	@JoinColumn(name  ="customer_id", nullable = false)
	private CustomerEntity customer;

	@Builder
	private EnrolledServiceEntity(Integer id, String serviceName, CustomerEntity customer) {
		super(id);
		this.serviceName = serviceName;
		this.customer = customer;
	}
}
