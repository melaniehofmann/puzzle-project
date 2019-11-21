package com.hofmann;
/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hofmann.puzzle.Puzzle;
import com.hofmann.puzzle.PuzzleFactory;

public class Application {
	private static final Logger LOG = LogManager.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("Application started");

		PuzzleFactory puzzleFactory = new PuzzleFactory();
		Puzzle puzzle = puzzleFactory.createPuzzle(args);

		puzzle.executePuzzle();
	}

}
