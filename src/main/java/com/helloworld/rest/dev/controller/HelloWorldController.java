package com.helloworld.rest.dev.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helloworld.rest.dev.concurrent.AccountTransaction;
import com.helloworld.rest.dev.concurrent.ThreadMonitor;
import com.helloworld.rest.dev.dto.Post;
import com.helloworld.rest.dev.dto.ThreadSummary;
import com.helloworld.rest.dev.dto.Word;
import com.helloworld.rest.dev.helper.HelloWorldHelper;
import com.helloworld.rest.dev.persistence.service.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * This class exposes following REST endpoints, to demonstrate some use cases.
 *  - GET  /v1/hello or v1/hello?saySomeThing=REST
 *  - POST /v1/uniqueWords
 *  - GET  /v1/fibonacci?n=5
 *  - POST /v1/deadlock
 *  - GET  /v1/monitor?elapsedTime=5000
 *  - GET  /v1/posts or v1/posts?userId=2
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class HelloWorldController {

	@Autowired
	private HelloWorldHelper helper;

	@Autowired
	private AccountTransaction transaction;

	@Autowired
	private ThreadMonitor threadMonitor;

	@Autowired
	private ExternalService externalService;

	@Value("${external.service.url}")
	private String postResourceUrl;

	/**
	 * This REST endpoint respond "Hello World!" by default.
	 * If user provide a request param "?saySomeThing=Yoga",
	 * then it respond "Hello Yoga".
	 *
	 * @param saySomeThing
	 * @return "Hello %$"
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/hello",
				    produces = MediaType.TEXT_PLAIN_VALUE)
	public String sayHello(@RequestParam(required = false) String saySomeThing) {

		String hello = "Hello %s";
		String response = StringUtils.isEmpty(saySomeThing)
				? String.format(hello, "World!") : String.format(hello, saySomeThing);
		return response;
	}

	/**
	 * This REST endpoint, returns unique words with number of occurrences,
	 * for a given paragraph of text.
	 *
	 * @param node - contains a paragraph of text
	 * @return Array of JsonNode having unique word and number of occurrences.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/uniqueWords",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode[] createUniqueWords(@RequestBody JsonNode node) {

		if (Objects.isNull(node) || (Objects.nonNull(node)
				&& StringUtils.isEmpty(node.path("paragraph").asText())))
		{
			log.error("Bad Request contains invalid input");
			throw new RuntimeException(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}

		try {
			String paraGraph = node.path("paragraph").asText();
			List<JsonNode> jsonNodes = helper.getUniqueJsonNodes(paraGraph);
			log.info("Before unique words response commit :: ");
			return jsonNodes.toArray(new JsonNode[jsonNodes.size()]);
		} catch (Exception jpe) {
			log.error("Error occurred while creating unique words {} ", jpe);
			throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), jpe);
		}
	}

	/**
	 * This endpoint for an example to use a java model to represent
	 * the unique words data.
	 *
	 * @param word - contains a paragraph of text
	 * @return Array of Word having unique word and number of occurrences.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/uniqueWords1",
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Word[] createUniqueWords(@RequestBody Word word) {
		try {
			if (Objects.isNull(word) || (Objects.nonNull(word)
					&& StringUtils.isEmpty(word.getParagraph()))) {
				log.error("Bad Request contains invalid input");
				throw new RuntimeException(HttpStatus.BAD_REQUEST.getReasonPhrase());
			}
			List<Word> listOfUniqueWords = helper.getUniqueWords(word.getParagraph());
			return  listOfUniqueWords.toArray(new Word[listOfUniqueWords.size()]);
		} catch (Exception jpe) {
			log.error("Error occurred while creating unique words {} ", jpe);
			throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), jpe);
		}
	}

	/**
	 * Generate a fibonacci series of given "n" number.
	 *
	 * @param n - input number
	 * @return - fibonacci series of number "n"
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/fibonacci",
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer[] generateFibonacciSeries(@RequestParam(required = true,
													defaultValue = "5") Integer n) {

		if (n < 0 || n > Integer.MAX_VALUE) {
			log.error("The given number is out of range Integer");
			throw new RuntimeException(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		List<Integer> fibList = new ArrayList<>();
		helper.generateFibRecursive(n, 0, 0, fibList);
		return fibList.toArray(new Integer[fibList.size()]);
	}

	/**
	 * Create a dead locks while 2 threads tries to get a object monitor
	 * on 2nd resource which is already locked by other thread.
	 *
	 * @return - an REST endpoint URL to monitor dead locks.
	 * 			"http://localhost:8080/v1/monitor?elapsedTime=5000"
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deadlock",
							produces = MediaType.TEXT_PLAIN_VALUE)
	public String createDeadLock() {

		AccountTransaction account1 = new AccountTransaction(1000d);
		AccountTransaction account2 = new AccountTransaction(500d);

		Thread t1 = new Thread(() -> {
				transaction.transfer(account1, account2, 100d);
		},"Account1");
		t1.start();

		Thread t2 =new Thread(() -> {
				transaction.transfer(account2, account1, 50d);
		},"Account2");
		t2.start();

		StringBuilder builder = new StringBuilder("This REST endpoint created a dead lock, ");
		builder.append("please monitor the dead lock by visiting this endpoint.")
				.append("'http://localhost:8080/v1/monitor?elapsedTime=5000'");
		return builder.toString();
	}

	/**
	 * Function to monitor any threads dead lock in the system.
	 *
	 * @param elapsedTime - Mointor call wait upto given elapsed time
	 * @return - Threads Deadlock details
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/monitor",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ThreadSummary> monitorThread(@RequestParam(required = true,
											defaultValue = "5000") long elapsedTime) {
		try {
			Thread.sleep(elapsedTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return threadMonitor.checkDeadLocks();
	}

	/**
	 * Invoke an external service using spring RestTemplate.
	 *
	 * @param userId - optional
	 * @return - if userId is not given, then complete list of
	 * 			 Posts will be returned else sub list of Posts
	 * 			 will be return based on Posts.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/posts",
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Post[]> getPosts(@RequestParam(required = false) Integer userId) {

		RestTemplate restTemplate = new RestTemplate();
		if (Objects.nonNull(userId) && userId > 0) {
			postResourceUrl += "?userId=" + userId;
		}
		return restTemplate.getForEntity(postResourceUrl, Post[].class);
		//return externalService.execute(postResourceUrl, Post[].class, userId).getBody();
	}

	/**
	 * Workday problem:
	 *
	 * Make a GET REST call to get all the movies with Title "Spiderman", the response will be 10 per page
	 * and the total pages will be 2. Get all the title sort them and return an array of titles.
	 *
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/movietitles",
					produces = MediaType.APPLICATION_JSON_VALUE)
	public String[] getMovieTitles(@RequestParam(required = true, defaultValue = "Spiderman") String searchParam) {
		return getMovieTitlesFromExtSvc(searchParam);
		//return getMovieTitlesfromFiles(searchParam);
	}

	private String[] getMovieTitlesFromExtSvc(String searchParam) {
		List<String> movieTitles = new ArrayList<>();
		try {
			String moviePage1 = "https://jsonmock.hackerrank.com/api/movies/search?Title=" + searchParam + "&page=1";
			String moviePage2 = "https://jsonmock.hackerrank.com/api/movies/search?Title=" + searchParam + "&page=2";
			String[] movieUrls = {moviePage1, moviePage2};
			for (String movie : movieUrls) {
				URL url = new URL(movie);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
				movieTitles.addAll(getMovieTitles(conn.getInputStream(), searchParam));
			}

			/*BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}*/

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortMoviesTtitles(movieTitles);
	}

	private String[] getMovieTitlesfromFiles(String searchParam) {
		String movie1 = "/json/input/movie_title_1.json";
		String movie2 = "/json/input/movie_title_2.json";
		String[] movies = {movie1, movie2};
		List<String> movieTitles = new ArrayList<>();
		for (String movie : movies) {
			movieTitles.addAll(getMovieTitles(this.getClass().getResourceAsStream(movie), searchParam));
		}
		return sortMoviesTtitles(movieTitles);
	}

	private List<String> getMovieTitles(InputStream stream, String searchParam) {
		List<String> movieTitles = new ArrayList<>();
		try {
			Gson gson = new Gson();
			InputStreamReader reader = new InputStreamReader(stream);
			JsonElement json = gson.fromJson(reader, JsonElement.class);
			JsonObject jsonObj = json.getAsJsonObject();
			for (JsonElement element : jsonObj.getAsJsonArray("data")) {
				if (element.getAsJsonObject().get("Title").getAsString().contains(searchParam)) {
					movieTitles.add(element.getAsJsonObject().get("Title").getAsString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieTitles;
	}

	private String[] sortMoviesTtitles(List<String> movieTitles) {
		movieTitles.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		return movieTitles.toArray(new String[movieTitles.size()]);
	}

	public static void main(String[] args) {
//		System.out.println(Arrays.toString(
//				new HelloWorldController().getMovieTitlesfromFiles("Spiderman")));
		HelloWorldController hello = new HelloWorldController();
		System.out.println(hello.jsonEncode(null));
		System.out.println(hello.jsonEncode("abc"));
		System.out.println(hello.jsonEncode(123));
		System.out.println(hello.jsonEncode(false));
		List<Object> list = new ArrayList<>();
		list.add(111);
		list.add("xyz");
		list.add(100.10);
		list.add(true);
		System.out.println(hello.jsonEncode(list));
		List<Object> nestedList = new ArrayList<>();
		nestedList.add(50);
		nestedList.add("pqr");
		nestedList.add(list);
		nestedList.add(77.99);
		nestedList.add(false);
		System.out.println(hello.jsonEncode(nestedList));
		int[] nums = {1,2,3,4};
		System.out.println(hello.jsonEncode(nums));
	}

	/**
	 * NerdWallet :
	 * Write a function to encode a gievn object to an encoded json
	 *
	 * @param obj
	 * @return
	 */
	private String jsonEncode(Object obj) {
		if (obj == null) {
			return "null";
		} else if (obj instanceof String) {
			return "\"" + obj + "\"";
		} else if (obj instanceof Number) {
			return "" + obj;
		} else if (obj instanceof Boolean) {
			return "" + obj;
		} else if(obj instanceof List) {
			StringBuilder builder = new StringBuilder("[");
			for (int i = 0; i < ((List) obj).size(); i++) {
				builder.append(jsonEncode(((List)obj).get(i))).append(",");
			}
			builder.append("]");
			return builder.toString().replace(",]", "]");
		} else if(obj instanceof Array) { // This needs to check
			System.out.println("This is Array instance");
		} else if (obj instanceof Map){ // Need to implement
			System.out.println("This is Map instance");
		}
		return obj + "";
	}
}
