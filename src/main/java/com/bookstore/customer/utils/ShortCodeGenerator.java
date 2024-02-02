package com.bookstore.customer.utils;

public class ShortCodeGenerator {

	private ShortCodeGenerator() {
	}

	public static String generateShortCode(String inputString) {
		String[] words = inputString.split(" ");
		StringBuilder shortCodeBuilder = new StringBuilder();

		for (String word : words) {
			if (word.length() > 1) {
				shortCodeBuilder.append(word.charAt(0));
				if (shortCodeBuilder.length() >= 10) {
					break;
				}
				shortCodeBuilder.append(word.charAt(word.length() - 1));
			}
		}

		return shortCodeBuilder.length() > 10 ? shortCodeBuilder.substring(0, 10).toUpperCase()
				: shortCodeBuilder.toString().toUpperCase();
	}

}
