package com.helloworld.rest.dev.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.helloworld.rest.dev.serializer.SerializerApp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * This is for serialization testing
 */
@Getter
@ToString
@NoArgsConstructor
//@JsonSerialize(using = SerializerApp.ItemSerializer.class) // alternatively registter the module with ObjectMapper
public class Item { //implements Serializable {
	private int id;
	private String itemName;
	private Customer owner;

	public Item(int id, String itemName, Customer owner) {
		this.id = id;
		this.itemName = itemName;
		this.owner = owner;
	}


}
