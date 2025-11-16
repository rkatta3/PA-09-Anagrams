# PA-09-Anagrams
PA9-Anagrams
Overview
This assignment practices recursive backtracking, decomposition using multiple methods, and working with arrays and collections.​

An anagram is a word or phrase made by rearranging the letters of another word or phrase. For example, "midterm" and "trimmed" are anagrams, and "Clint Eastwood" and "old west action" are multi-word anagrams.​

This program finds all anagram phrases that match a given word or phrase using a word list file.​

Usage
Command line format: java Anagrams <wordlist_file> <phrase> <max_words>​

wordlist_file: Text file containing one word per line

phrase: Word or phrase to find anagrams of (no spaces)

max_words: Maximum number of words in each anagram (0 = no limit)

Example
text
java Anagrams words1.txt barbarabush 0
Output:

text
Phrase to scramble: barbarabush

All words found in barbarabush:
[abash, aura, bar, barb, brush, bus, hub, rub, shrub, sub]

Anagrams for barbarabush:
[abash, bar, rub]
[abash, rub, bar]
[bar, abash, rub]
[bar, rub, abash]
[rub, abash, bar]
[rub, bar, abash]
Algorithm
Uses recursive backtracking to generate all anagrams.​

Find all words that can be formed from subsets of letters in the phrase

Use only these words as "choices" in the decision tree (for efficiency)

Recursively choose words that use letters from the phrase

Backtrack when no valid choices remain or max words exceeded

Print complete anagrams that use all letters

Note: The same word can appear multiple times in an anagram (e.g., "bar" twice from "barbara bush").​

Implementation Requirements
Class name: Anagrams.java

Use the provided main method structure (see specification)

Implement helper methods:

getWordList() - reads word list file

getChars() - extracts characters from phrase

getCombinations() - finds all valid words from phrase letters

getAnagrams() - recursive backtracking to generate anagrams

Program should be under 200 lines (excluding file header comments)

Each method should be under 30 lines

Grading
80% Correctness

20% Decomposition and Code Clarity

Proper use of recursive backtracking

Well-decomposed methods

Meaningful variable names (camelCase)

Clear comments and documentation

Avoiding redundancy

Submission
Submit Anagrams.java to Gradescope with package: com.gradescope.anagrams​
