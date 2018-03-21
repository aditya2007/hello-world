package com.helloworld.rest.dev.persistence.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#identifiers
 */

@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class PersonEntity extends BaseEntity {

	@Builder
	private PersonEntity(Integer id) {
		super(id);
	}
}
