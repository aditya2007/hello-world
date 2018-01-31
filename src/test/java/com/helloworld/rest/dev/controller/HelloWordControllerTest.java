package com.helloworld.rest.dev.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.helloworld.rest.dev.concurrent.AccountTransaction;
import com.helloworld.rest.dev.concurrent.ThreadMonitor;
import com.helloworld.rest.dev.dto.Post;
import com.helloworld.rest.dev.dto.ThreadSummary;
import com.helloworld.rest.dev.helper.HelloWorldHelper;
import com.helloworld.rest.dev.persistence.service.ExternalService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HelloWordControllerTest extends AbstractControllerTest {

	@Mock
	private HelloWorldHelper helper;

	@Mock
	private AccountTransaction transaction;

	@Mock
	private ThreadMonitor threadMonitor;

	@Mock
	private ExternalService externalService;


	@InjectMocks
	private HelloWorldController helloWorldController;

	ResponseEntity responseEntity;

	@Captor
	ArgumentCaptor<List<Integer>> fibArgumentCaptor;


	@Before
	public void setUp() throws NoSuchFieldException, IllegalAccessException {
		MockitoAnnotations.initMocks(this);
		responseEntity = mock(ResponseEntity.class);
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
		fibList.add(0);
		fibList.add(1);
		fibList.add(1);
		fibList.add(2);
		fibList.add(3);
		doAnswer((Answer) invocation -> {
			//return invocation.getArguments()[3];
			return fibList;
		}).when(helper)
				.generateFibRecursive(anyInt(), anyInt(), anyInt(), anyList());
		Integer[] fibSeries = helloWorldController.generateFibonacciSeries(5);
		assertNotNull(fibSeries);
	}

	@Test
	public void testCreateDeadLock() {
		StringBuilder builder = new StringBuilder("This REST endpoint created a dead lock, ");
		builder.append("please monitor the dead lock by visiting this endpoint.")
				.append("'http://localhost:8080/v1/monitor?elapsedTime=5000'");

		doNothing().when(transaction).transfer(anyObject(), anyObject(), anyDouble());
		String deadLockTrace = helloWorldController.createDeadLock();

		assertEquals(builder.toString(), deadLockTrace);
	}

	@Test
	public void testMonitorThread() {
		List<ThreadSummary> deadLockSummaries = new ArrayList<>();
		ThreadSummary summary1 = new ThreadSummary();
		summary1.setThreadInfo("Account1");
		deadLockSummaries.add(summary1);
		ThreadSummary summary2 = new ThreadSummary();
		summary1.setThreadInfo("Account2");
		deadLockSummaries.add(summary2);

		when(threadMonitor.checkDeadLocks()).thenReturn(deadLockSummaries);

		List<ThreadSummary> resSummaries = helloWorldController.monitorThread(500);
		assertNotNull(resSummaries);
		assertEquals(resSummaries.size(), 2);
	}

	//@Test //Need to fix this test case
	public void testExternalRestService() {
		ResponseEntity<Post[]> responseEntity = new ResponseEntity<>(getPosts(), HttpStatus.OK);
		doReturn(responseEntity).when(externalService).execute(anyString(), eq(Post[].class));//.thenReturn(responseEntity);
		ResponseEntity<Post[]> postsResEntity =  helloWorldController.getPosts(anyInt());
		assertNotNull(postsResEntity);
	}

	private Post[] getPosts() {
		List<Post> posts = new ArrayList<>();
		Post post = new Post();
		post.setId(1);
		post.setUserId(1);
		post.setTitle("Test1");
		post.setBody("Here to test the external service for first");
		posts.add(post);

		Post post1 = new Post();
		post1.setId(2);
		post1.setUserId(1);
		post1.setTitle("Test2");
		post1.setBody("Here to test the external service for second");
		posts.add(post1);
		return posts.toArray(new Post[posts.size()]);
	}

}
