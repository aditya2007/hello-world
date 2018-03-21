package com.helloworld.rest.dev.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class PhoneEntity extends BaseEntity {

	@Column(name = "number")
	private String number;

	@ManyToOne
	@JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
	private PersonEntity person;

	@Builder
	private PhoneEntity(Integer id, String number, PersonEntity person) {
		super(id);
		this.number = number;
		this.person = person;
	}

}
