package com.helloworld.rest.dev.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.helloworld.rest.dev.concurrent.AccountTransaction;
import com.helloworld.rest.dev.concurrent.ThreadMonitor;
import com.helloworld.rest.dev.helper.HelloWorldHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HelloWordControllerTest extends AbstractControllerTest {

	@Mock
	private HelloWorldHelper helper;

	@Mock
	private AccountTransaction transaction;

	@Mock
	private ThreadMonitor threadMonitor;

	@InjectMocks
	private HelloWorldController helloWorldController;

	@Before
	public void setUp() throws NoSuchFieldException, IllegalAccessException {
		MockitoAnnotations.initMocks(this);
		Field postResourceUrl = helloWorldController.getClass().getDeclaredField("postResourceUrl");
		postResourceUrl.setAccessible(true);
		postResourceUrl.set(helloWorldController, "https://jsonplaceholder.typicode.com/posts");
	}

	@Test
	public void testSayHelloDefault() {
		String response = helloWorldController.sayHello("");
		assertNotNull("Response is null", response);
		assertEquals("Hello World!", response);
	}

	@Test
	public void testSayHello() {
		String response = helloWorldController.sayHello("Yoga");
		assertNotNull("Response is null", response);
		assertEquals("Hello Yoga", response);
	}

	@Test
	public void testCreateUniqueWords() throws IOException {
		when(helper.getUniqueJsonNodes(anyString()))
				.thenReturn(readJsonNodesFromFile("src/test/resources/json/output/uniquewords.json"));
		JsonNode[] uniqueWords = helloWorldController.createUniqueWords(
				readJsonNodeFromFile("src/test/resources/json/input/uniquewords.json"));
		assertNotNull(uniqueWords);
		assertTrue(uniqueWords.length == 28);
		for (JsonNode node : uniqueWords) {
			if (!node.path("company").isMissingNode()) {
				assertTrue("The word 'company' might have occurred <> 2",
						(node.path("company").asInt() == 2));
				break;
			}
		} // end of for loop
	}

	@Test
	public void testCreateUniqueWordsBadRequest() {
		try {
			helloWorldController.createUniqueWords((JsonNode) null);
		} catch (RuntimeException re) {
			assertEquals(re.getMessage(), "Bad Request");
		}
	}

	@Test
	public void testGenerateFibonacciSeries() {
		List<Integer> fibList = new ArrayList<>();
		doAnswer(invocation -> fibList).when(helper)
				.generateFibRecursive(anyInt(), anyInt(), anyInt(), anyListOf(Integer.class));
		Integer[] fibonacci = helloWorldController.generateFibonacciSeries(5);
		assertNotNull(fibonacci);
	}




}
