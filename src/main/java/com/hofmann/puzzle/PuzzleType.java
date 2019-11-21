/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.puzzle;

import java.util.Arrays;

public enum PuzzleType {

	SIMPLECOIN("simplecoin");

	private String name;

	private PuzzleType(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static PuzzleType findByName(final String name) {
		return Arrays.stream(values()).filter(value -> value.getName().equals(name)).findFirst().orElse(null);
	}
}
