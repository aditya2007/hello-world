package com.helloworld.rest.dev.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.Before;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
