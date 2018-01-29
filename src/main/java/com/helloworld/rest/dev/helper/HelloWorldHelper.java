package com.helloworld.rest.dev.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.helloworld.rest.dev.dto.Word;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class HelloWorldHelper {

	private final ObjectMapper MAPPER = new ObjectMapper();

	public List<JsonNode> getUniqueJsonNodes(String json) {
		String[] words = json.split("[ \n\t\r.,;:!?(){]");
		Map<String, Integer> uniqueWords = new TreeMap<String, Integer>();
		List<JsonNode> jsonNodes = new ArrayList<JsonNode>();
		for (String w : words) {
			if (StringUtils.isEmpty(w)) continue;

			if (uniqueWords.containsKey(w)) {
				uniqueWords.put(w, uniqueWords.get(w) + 1);
			} else {
				uniqueWords.put(w.toLowerCase(), 1);
			}
		} //end  of for loop

		for (Map.Entry<String, Integer> entry : uniqueWords.entrySet()) {
			JsonNode node = MAPPER.createObjectNode();
			((ObjectNode) node).put(entry.getKey(), entry.getValue());
			jsonNodes.add(node);
		}
		return jsonNodes;
	}

	public List<Word> getUniqueWords(String json) {
		String[] words = json.split("[ \n\t\r.,;:!?(){]");
		Map<String, Integer> uniqueWords = new TreeMap<String, Integer>();
		List<Word> listOfUniqueWords = new ArrayList<Word>();
		List<JsonNode> jsonNodes = new ArrayList<JsonNode>();
		for (String w : words) {
			if (StringUtils.isEmpty(w)) continue;

			if (uniqueWords.containsKey(w)) {
				uniqueWords.put(w, uniqueWords.get(w) + 1);
			} else {
				uniqueWords.put(w.toLowerCase(), 1);
			}
		} //end  of for loop

		for (Map.Entry<String, Integer> entry : uniqueWords.entrySet()) {
			Word uniqueWord = new Word();
			uniqueWord.setUniqueWord(entry.getKey());
			uniqueWord.setOccurrences(entry.getValue());
			listOfUniqueWords.add(uniqueWord);
		}

		Collections.sort(listOfUniqueWords, new Comparator<Word>() {
			@Override
			public int compare(Word o1, Word o2) {
				return o1.getUniqueWord().compareTo(o2.getUniqueWord());
			}
		});
		return listOfUniqueWords;
	}

	public Integer[] generateFibonacciRecursive(Integer n) {

		return null;
	}

	public static void generateFibLoop(Integer n) {
		int prev = 0;
		int current = 1;
		int fib;

		List<Integer> fibList = new ArrayList<>();
		fibList.add(prev);
		fibList.add(current);
		while ((n - 2) > 0) {
			fib = prev + current;
			fibList.add(fib);
			prev = current;
			current = fib;
			n--;
		}
		System.out.println(fibList);
	}

	public void generateFibRecursive(int n, int curr, int next, List<Integer> fibList) {
		if(n == 0) return;
		if(curr == 0) {
			fibList.add(curr);
			curr = 1;
			generateFibRecursive(--n, curr, next, fibList);
			return;
		}
		next += curr;
		fibList.add(curr);
		generateFibRecursive(--n, next, curr, fibList);
	}
}
