package com.oliverwilkie.scrabble.impl;

import com.oliverwilkie.scrabble.Tile;

public class PlainTile implements Tile {
	
	private final char letter;
	private final int score;
	
	public PlainTile(char letter, int score) {
		this.letter = letter;
		this.score = score;
	}

	@Override
	public Character getLetterValue() {
		return letter;
	}

	@Override
	public int getScoreValue() {
		return score;
	}

}
