package com.jitendra.mehra.shaadi.utils;

import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.commons.lang3.text.WordUtils;

public class StringHelper {

	 public static String capitalize(String source) {
	        return Optional.ofNullable(source)
	            .map(str -> IntStream.concat(
	                str.codePoints().limit(1).map(Character::toUpperCase),
	                str.codePoints().skip(1)))
	            .map(stream -> stream.toArray())
	            .map(arr -> new String(arr, 0, arr.length))
	            .orElse(null);
	    }
	 
	 public static String upperCaseWords(String sentence) {
	        String words[] = sentence.replaceAll("\\s+", " ").trim().split(" ");
	        String newSentence = "";
	        for (String word : words) {
	            for (int i = 0; i < word.length(); i++)
	                newSentence = newSentence + ((i == 0) ? word.substring(i, i + 1).toUpperCase(): 
	                    (i != word.length() - 1) ? word.substring(i, i + 1).toLowerCase() : word.substring(i, i + 1).toLowerCase().toLowerCase() + " ");
	        }

	        return newSentence;
	    }
	 
	 public static void main(String[] args) {
		System.out.println(WordUtils.capitalizeFully("jitendra kumar sagoriya"));
	}
}
