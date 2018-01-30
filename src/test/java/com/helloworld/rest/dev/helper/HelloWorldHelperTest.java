package com.helloworld.rest.dev.helper;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class HelloWorldHelperTest {

	private HelloWorldHelper helper;

	@Before
	public void setUp() {
		helper = new HelloWorldHelper();
	}

	@Test
	public void testGetUniqueWords() {
		String paragraph = "XYZ is a brand of ABC Cable Communications,LLC, a subsidiary of the ABC Corporation," +
				"used to market consumer cable television, internet, telephone, and wireless services provided by " +
				"the company.ABC company always works towards consumer.";

		List<JsonNode> uniqueWords = helper.getUniqueJsonNodes(paragraph);
		Assert.notNull(uniqueWords, "JsonNodes list is null");
		Assert.isTrue(uniqueWords.size() == 28, "Number of Unique words are not equal to 28");

		for (JsonNode node : uniqueWords) {
			if (!node.path("company").isMissingNode()) {
				Assert.isTrue((node.path("company").asInt() == 2),
						"The word 'company' might have occurred <> 2");
				break;
			}
		} // end of for loop
	}

	@Test
	public void testGenerateFibonacciSeries() {
		List<Integer> fibList = new ArrayList<>();
		helper.generateFibRecursive(5, 0, 0, fibList);
		Assert.isTrue(fibList.size() == 5, "Wrong number of Fibonacci series");

		List<Integer> expectedList = new ArrayList<>();
		expectedList.add(0);
		expectedList.add(1);
		expectedList.add(1);
		expectedList.add(2);
		expectedList.add(3);

		Assert.isTrue(fibList.containsAll(expectedList), "Generated Fibonacci Series are not correct");
	}
}
