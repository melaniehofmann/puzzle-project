/*
 * Copyright, 2019, DI Hofmann Melanie - All Rights Reserved.
 */
package com.hofmann.paymentCoins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hofmann.puzzle.Puzzle;

/**
 * Calculates amount of possibilities to pay a cent amount in cent coins only.
 * https://trainyourprogrammer.de/java-239-begleichung-einer-rechnung-ausschlie%C3%9Flich-mit-muenzen.html
 * @author mhofmann
 *
 */
public class SimplePaymentCoins implements Puzzle {
	private static final Logger LOG = LogManager.getLogger(SimplePaymentCoins.class);
	private static final int[] COINS = {1,2,5,10};
	
	private final int amountInCent;
	
	public SimplePaymentCoins(final int amountInCent) {
		this.amountInCent = amountInCent;
	}
	
	@Override
	public void executePuzzle() {
		ArrayList<int[]> results= new ArrayList<>();
		
		this.recursiveCalculation(results, COINS, new int[0], amountInCent);
		
		LOG.info("Total options: " + results.size());
	}
	
	private void recursiveCalculation(ArrayList<int[]> matchedSets, final int[] setOfCoinsToCompare2, final int[] coinSet, final int value) {
		int[] coinSetC = Arrays.copyOf(coinSet, coinSet.length + 1);
		if (continueCalculation(coinSetC, value) ) {
			
			for (int i = 0; i < setOfCoinsToCompare2.length; i++) {
				final int currentCoinToBegin = setOfCoinsToCompare2[i];
				final int[] setOfCoinsToCompare = Arrays.copyOfRange(setOfCoinsToCompare2, i, setOfCoinsToCompare2.length);
				coinSetC[coinSetC.length - 1] = currentCoinToBegin;
				this.recursiveCalculation(matchedSets, setOfCoinsToCompare, coinSetC, value);
			}
			
		} else if(matchesCalculation(coinSetC, value)) {
			LOG.debug("Coin Set match: " + getCalculationAsString(coinSetC));
			matchedSets.add(coinSetC);
		} else {
			// LOG.debug("Coin Set not an option: " + getCalculationAsString(coinSetC));
		}
	}
	
	private boolean continueCalculation(final int[] coins, final int value) {
		return coins.length < 0 || IntStream.of(coins).sum() < value;
	}
	
	private boolean matchesCalculation(final int[] coins, final int value) {
		return IntStream.of(coins).sum() == value;
	}
	
	private String getCalculationAsString(final int[] coins) {
		return StringUtils.join( ArrayUtils.toObject(coins), "+");
	}
}
