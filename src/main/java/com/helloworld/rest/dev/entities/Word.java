package com.helloworld.rest.dev.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Word implements Serializable {
	private static final long serialVersionUID = 3888538238586315559L;

	private String paragraph;
	private String uniqueWord;
	private int occurrences;
}
