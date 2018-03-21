package com.java.core.dsl.trie;

import java.util.*;

public class SimpleTrieApp {

	private TrieNode root;

	public SimpleTrieApp() {
		root = new TrieNode();
	}

	public static void main(String[] args) {
		Map<Character, Integer> map = new HashMap<>();
		//for (map.key)
		SimpleTrieApp trieApp = new SimpleTrieApp();
		/*String[] words = {"bead", "back", "bend", "addition", "add", "leet","be", "code", "le"};
		Arrays.stream(words).forEach(word -> trieApp.addWord(word));
		System.out.println(trieApp.root);

		String[] searchWords = {"beat", "beck", "bend", "attack", "add", "leetcode", "code", "le"};
		Arrays.stream(searchWords).forEach(word ->
				System.out.println(word + " found in the tree :: "
						+ trieApp.searchWord(word)));

		System.out.println(trieApp.searchWithPrefix("b"));*/

//		String[] wordsToFindFromBoard = {"oath","pea","eat","rain"};
//		trieApp.testFindWords(wordsToFindFromBoard);
	}

	// Inserts a word into the Trie
	public void addWord(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currChar = word.charAt(i);
			if (!node.containsKey(currChar)) {
				node.add(currChar);
			}
			node = node.get(currChar);
		} //end of for loop
		node.setEnd(true);
		node.setWord(word);
	}

	public boolean searchWord(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currChar = word.charAt(i);
			if (!node.containsKey(currChar)) return false;
			node = node.get(currChar);
		}
		return node.isEnd();
	}

	public List<String> searchWithPrefix(String prefix) {
		List<String> foundWords = new ArrayList<>();
		TrieNode node = root;
		for (int i =0; i < prefix.length(); i++) {
			char currChar = prefix.charAt(i);
			if (!node.containsKey(currChar)) return foundWords;
			node = node.get(currChar);
		}
		search(node, foundWords);
		return foundWords;
	}

	private void search(TrieNode node, List<String> foundWords) {
		if (node.isEnd()) {
			foundWords.add(node.getWord());
		}

		for (TrieNode child : node.getChildren()) {
			if (Objects.nonNull(child)) {
				search(child, foundWords);
			}
		}
	}

	private void testFindWords(String[] wordsToFound) {
		char[][] board = new char[4][4];
		board[0][0] = 'o';
		board[0][1] = 'a';
		board[0][2] = 'a';
		board[0][3] = 'n';

		board[1][0] = 'e';
		board[1][1] = 't';
		board[1][2] = 'a';
		board[1][3] = 'e';

		board[2][0] = 'i';
		board[2][1] = 'h';
		board[2][2] = 'k';
		board[2][3] = 'r';

		board[3][0] = 'i';
		board[3][1] = 'f';
		board[3][2] = 'l';
		board[3][3] = 'v';

		System.out.println(Arrays.deepToString(board));
		List<String> foundWords = findWords(board,wordsToFound);
		System.out.println("Found Words from board :: " + foundWords);
	}

	/**
	 * Given a 2D board and a list of words from the dictionary, find all words in the board.

	 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

	 For example,
	 Given words = ["oath","pea","eat","rain"] and board =

	 [
	 ['o','a','a','n'],
	 ['e','t','a','e'],
	 ['i','h','k','r'],
	 ['i','f','l','v']
	 ]
	 Return ["eat","oath"].

	 Solution :
	 * Intuitively, start from every cell and try to build a word in the dictionary.
	 * Backtracking (dfs) is the powerful way to exhaust every possible ways. Apparently,
	 * we need to do pruning when current character is not in any word.
	 */
	public List<String> findWords(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();
		TrieNode rootNode = buildTrie(words);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(board, i, j, rootNode, result);
			}
		}
		return result;
	}

	private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
		char ch = board[i][j];
		if (ch == '#' || !node.containsKey(ch)) return;
		node = node.get(ch);
		if (node.getWord() != null) { // found one
			result.add(node.getWord());
			node.word = null; // de-duplicate
		}

		board[i][j] = '#';
		if (i > 0) dfs(board, i - 1, j, node, result);
		if (j > 0) dfs(board, i, j - 1, node, result);
		if (i < board.length - 1) dfs(board, i + 1, j, node, result);
		if (j < board[0].length - 1) dfs(board, i, j + 1, node, result);

		board[i][j] = ch;
	}

	private TrieNode buildTrie(String[] words) {
		TrieNode rootNode = new TrieNode();
		for (String word : words) {
			TrieNode curr = rootNode;
			for (char ch : word.toCharArray()) {
				if (!curr.containsKey(ch)) {
					curr.add(ch);
				}
				curr = curr.get(ch);
			} // end of inner for loop
			curr.word = word;
			curr.setEnd(true);
		} // end of outer loop
		return rootNode;
	}

	class TrieNode {
		// Links to the children
		private TrieNode[] children;
		private String word;
		private boolean isEnd;

		public TrieNode() {
			children = new TrieNode[26];
		}

		public boolean containsKey(char ch) {
			return children[ch - 'a'] != null;
		}

		public TrieNode get(char ch) {
			return children[ch - 'a'];
		}

		public void add(char ch) {
			children[ch - 'a'] = new TrieNode();
		}

		public TrieNode[] getChildren() {
			return children;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public boolean isEnd() {
			return isEnd;
		}

		public void setEnd(boolean end) {
			isEnd = end;
		}
	}
}
