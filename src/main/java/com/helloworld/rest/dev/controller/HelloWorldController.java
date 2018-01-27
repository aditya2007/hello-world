package com.helloworld.rest.dev.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.helloworld.rest.dev.entities.Word;
import com.helloworld.rest.dev.helper.HelloWorldHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1")
public class HelloWorldController {

	@Autowired
	private HelloWorldHelper helper;

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String sayHello(@RequestParam(required = false) String saySomeThing) {
		String hello = "Hello %s";
		String response = StringUtils.isEmpty(saySomeThing)
				? String.format(hello, "World!") : String.format(hello, saySomeThing);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uniqueWords",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode[] createUniqueWords(@RequestBody JsonNode node) {
		if (Objects.isNull(node) || (Objects.nonNull(node) && StringUtils.isEmpty(node.path("paragraph").asText())))
		{
			throw new RuntimeException(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}

		try {
			String paraGraph = node.path("paragraph").asText();
			List<JsonNode> jsonNodes = helper.getUniqueJsonNodes(paraGraph);
			return jsonNodes.toArray(new JsonNode[jsonNodes.size()]);
		} catch (Exception jpe) {
			throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), jpe);
		}
	}

	/**
	 * This endpoint for an example to use a java entity for request and response
	 *
	 * @param word
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/uniqueWords1",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Word[] createUniqueWords(@RequestBody Word word) {
		try {
			if (Objects.isNull(word) || (Objects.nonNull(word)
					&& StringUtils.isEmpty(word.getParagraph()))) {
				throw new RuntimeException(HttpStatus.BAD_REQUEST.getReasonPhrase());
			}
			List<Word> listOfUniqueWords = helper.getUniqueWords(word.getParagraph());
			return  listOfUniqueWords.toArray(new Word[listOfUniqueWords.size()]);
		} catch (Exception jpe) {
			throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), jpe);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fibonacci",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer[] generateFibonacciSeries(@RequestParam(required = true) Integer n) {
		if (n < Integer.MIN_VALUE || n > Integer.MAX_VALUE) {
			throw new RuntimeException(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		List<Integer> fibList = new ArrayList<>();
		helper.generateFibRecursive(n, 0, 0, fibList);
		return fibList.toArray(new Integer[fibList.size()]);
	}
}
