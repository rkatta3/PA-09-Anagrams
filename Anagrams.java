/*
 * Author: Rithvik Katta
 * Course: CSC 210 - Summer 2025
 * File: Anagrams.java
 * Purpose: This program finds all possible anagram phrases from a given phrase 
 * using words from a word list. It uses recursive backtracking 
 * to generate all combinations of words that use up all letters 
 * from the phrase, subject to an optional word count limit.
 */

package com.gradescope.anagrams;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Anagrams {

	/*
	 * getWordList(filename) -- Reads all words from a file into a HashSet. Each
	 * word is converted to lowercase and trimmed of spaces. Returns a HashSet
	 * containing the valid words.
	 */
	public static HashSet<String> getWordList(String filename) throws FileNotFoundException {
		HashSet<String> words = new HashSet<>();
		Scanner input = new Scanner(new File(filename));
		while (input.hasNextLine()) {
			String word = input.nextLine().trim().toLowerCase();
			if (!word.isEmpty()) {
				words.add(word);
			}
		}
		input.close();
		return words;
	}

	/*
	 * getChars(phrase) -- Converts a phrase into a list of lowercase characters.
	 * Spaces are removed. Returns an ArrayList of characters.
	 */
	public static ArrayList<Character> getChars(String phrase) {
		ArrayList<Character> chars = new ArrayList<>();
		for (char c : phrase.toLowerCase().toCharArray()) {
			if (c != ' ') {
				chars.add(c);
			}
		}
		return chars;
	}

	/*
	 * getCombinations(...) -- Recursively builds combinations of letters to find
	 * valid words from the dictionary. letters: available letters prefix: current
	 * word being formed validWords: dictionary set solutions: set of valid words
	 * found
	 */
	public static void getCombinations(ArrayList<Character> letters, String prefix, HashSet<String> validWords,
			HashSet<String> solutions) {
		// If prefix is a valid word, it is stored
		if (validWords.contains(prefix) && !prefix.isEmpty()) {
			solutions.add(prefix);
		}

		// add each remaining letter to the prefix
		for (int i = 0; i < letters.size(); i++) {
			char c = letters.get(i);
			ArrayList<Character> remaining = new ArrayList<>(letters);
			remaining.remove(i);
			getCombinations(remaining, prefix + c, validWords, solutions);
		}
	}

	/*
	 * getAnagrams(...) -- Uses recursive backtracking to find all valid anagram
	 * phrases from the word list that match the original phrase. totalLength: total
	 * letters in phrase choices: possible words original: original phrase without
	 * spaces current: current partial solution maxWords: limit on words (-1 for no
	 * limit) currentLength: letters used so far allResults: list of complete
	 * anagram solutions
	 */
	public static void getAnagrams(int totalLength, ArrayList<String> choices, String original,
			ArrayList<String> current, int maxWords, int currentLength, ArrayList<ArrayList<String>> allResults) {

		// Base case: all letters used up exactly
		if (currentLength == totalLength) {
			allResults.add(new ArrayList<>(current));
			return;
		}

		// Stop if maximum word count is reached
		if (maxWords != -1 && current.size() >= maxWords) {
			return;
		}

		// add each possible word to the current anagram
		for (String word : choices) {
			if (canForm(original, current, word)) {
				current.add(word); // Choose
				getAnagrams(totalLength, choices, original, current, maxWords, currentLength + word.length(),
						allResults);
				current.remove(current.size() - 1); // Backtrack
			}
		}
	}

	/*
	 * canForm(original, current, word) -- Checks if the given word can be formed
	 * from the remaining letters in the original phrase after removing letters from
	 * all words in current. Returns true if possible, false otherwise.
	 */
	public static boolean canForm(String original, ArrayList<String> current, String word) {
		ArrayList<Character> remaining = getChars(original);

		// Remove letters already used in current
		for (String used : current) {
			for (char c : used.toCharArray()) {
				remaining.remove((Character) c);
			}
		}

		// remove each letter of the new word
		for (char c : word.toCharArray()) {
			if (!remaining.remove((Character) c)) {
				return false;
			}
		}
		return true;
	}
}
