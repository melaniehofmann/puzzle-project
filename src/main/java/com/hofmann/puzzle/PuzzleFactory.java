/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.puzzle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hofmann.paymentCoins.SimplePaymentCoins;

public class PuzzleFactory {
	private static final Logger LOG = LogManager.getLogger(PuzzleFactory.class);

	private static final String PUZZLE_NAME_PROPERTY = "puzzlename";

	public Puzzle createPuzzle(final String[] args) {
		String activePuzzle = System.getProperty(PUZZLE_NAME_PROPERTY);

		if (activePuzzle != null && PuzzleType.findByName(activePuzzle) != null) {
			Puzzle puzzle = null;

			switch (PuzzleType.findByName(activePuzzle)) {
			case SIMPLECOIN:
				puzzle = new SimplePaymentCoins(Integer.parseInt(args[0]));
				break;
			default:
				LOG.error("Puzzle not available currently");
				break;
			}
			return puzzle;
		}
		throw new PuzzleInputException("No valid puzzle provided.");
	}
}