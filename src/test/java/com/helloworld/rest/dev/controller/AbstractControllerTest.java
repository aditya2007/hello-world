package com.helloworld.rest.dev.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractControllerTest {

	private ObjectMapper MAPPER = new ObjectMapper();

	protected JsonNode readJsonNodeFromFile(String filePath) throws IOException {
		return MAPPER.readTree(new File(filePath));
	}

	protected List<JsonNode> readJsonNodesFromFile(String filePath) throws IOException {
		return MAPPER.readValue(new File(filePath), new TypeReference<List<JsonNode>>() {});
	}

}
