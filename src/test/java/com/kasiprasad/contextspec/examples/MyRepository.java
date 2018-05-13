package com.kasiprasad.contextspec.examples;

/**
 * An example of a class which has some behavior to test
 */
public class MyRepository {

	public String get(int id) throws IllegalArgumentException {

		if (id == 1) {
			return String.format("I looked up the given id: %d", id);
		}

		throw new IllegalArgumentException("The only id supported is 1");

	}
}
