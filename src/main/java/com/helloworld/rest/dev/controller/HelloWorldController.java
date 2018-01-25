package com.helloworld.rest.dev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.rest.dev.entities.Word;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(@RequestParam(required = false) String saySomeThing) {
		String hello = "Hello %s";
		String response = StringUtils.isEmpty(saySomeThing)
				? String.format(hello, "World!") : String.format(hello, saySomeThing);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uniqueWords",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Word[] getUniqueWords(@RequestBody Word word) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = word.getParagraph();

		} catch (Exception jpe) {
			throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), jpe);
		}

		return null;
	}
}
