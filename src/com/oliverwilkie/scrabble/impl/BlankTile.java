package com.oliverwilkie.scrabble.impl;

import com.oliverwilkie.scrabble.InvalidTileValueException;
import com.oliverwilkie.scrabble.Tile;

public class BlankTile implements Tile {
	
	private Character letter;

	public void setLetterValue(Character c) throws InvalidTileValueException {
		c = Character.toUpperCase(c);
		if (c.charValue() <= 'A' || c.charValue() >= 'Z') {
			throw new InvalidTileValueException();
		}
		letter = c;
	}
	
	@Override
	public Character getLetterValue() {
		return letter;
	}

	@Override
	public int getScoreValue() {
		return 0;
	}
	
	

}
