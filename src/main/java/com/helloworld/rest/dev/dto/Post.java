package com.helloworld.rest.dev.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post implements Serializable {

	private Integer userId;
	private Integer id;
	private String title;
	private String body;
}
