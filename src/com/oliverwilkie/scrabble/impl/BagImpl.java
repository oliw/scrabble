package com.oliverwilkie.scrabble.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.oliverwilkie.scrabble.Bag;
import com.oliverwilkie.scrabble.Tile;
import com.oliverwilkie.scrabble.TooFewTilesInBagException;

public class BagImpl implements Bag {
	
	private List<Tile> tiles;
	
	public BagImpl() {
		tiles = new LinkedList<Tile>();
		tiles.addAll(generateBlankTiles(2));
		tiles.addAll(generateTiles('A', 1, 9));
		tiles.addAll(generateTiles('B', 3, 2));
		tiles.addAll(generateTiles('C', 3, 2));
		tiles.addAll(generateTiles('D', 2, 4));
		tiles.addAll(generateTiles('E', 1, 12));
		tiles.addAll(generateTiles('F', 4, 2));
		tiles.addAll(generateTiles('G', 2, 3));
		tiles.addAll(generateTiles('H', 4, 2));
		tiles.addAll(generateTiles('I', 1, 9));
		tiles.addAll(generateTiles('J', 8, 1));
		tiles.addAll(generateTiles('K', 5, 1));
		tiles.addAll(generateTiles('L', 1, 4));
		tiles.addAll(generateTiles('M', 3, 2));
		tiles.addAll(generateTiles('N', 1, 6));
		tiles.addAll(generateTiles('O', 1, 8));
		tiles.addAll(generateTiles('P', 3, 2));
		tiles.addAll(generateTiles('Q', 10, 1));
		tiles.addAll(generateTiles('R', 1, 6));
		tiles.addAll(generateTiles('S', 1, 4));
		tiles.addAll(generateTiles('T', 1, 6));
		tiles.addAll(generateTiles('U', 1, 4));
		tiles.addAll(generateTiles('V', 4, 2));
		tiles.addAll(generateTiles('W', 4, 2));
		tiles.addAll(generateTiles('X', 8, 1));
		tiles.addAll(generateTiles('Y', 4, 2));
		tiles.addAll(generateTiles('Z', 10, 1));
		// Shuffle
		Collections.shuffle(tiles);
	}
	
	private static List<Tile> generateBlankTiles(int count) {
		List<Tile> tiles = new LinkedList<Tile>();
		for (int i = 0; i < count; i++) {
			tiles.add(new BlankTile());
		}
		return tiles;
	}
	
	private static List<Tile> generateTiles(char value, int score, int count) {
		List<Tile> tiles = new LinkedList<Tile>();
		for (int i = 0; i < count; i++) {
			Tile t = new PlainTile(value, score);
			tiles.add(t);
		}
		return tiles;
	}
	

	@Override
	public List<Tile> swapTiles(List<Tile> tiles) throws TooFewTilesInBagException {
		int numOfTilesToSwap = tiles.size();
		if (getNumOfTilesInBag() < numOfTilesToSwap) {
			throw new TooFewTilesInBagException();
		} else {
			List<Tile> newTiles = takeTiles(numOfTilesToSwap);
			tiles.addAll(tiles);
			return newTiles;
		}
	}

	@Override
	public List<Tile> takeTiles(int numOfTiles) {
		numOfTiles = Math.min(numOfTiles, getNumOfTilesInBag());
		List<Tile> tiles = new LinkedList<Tile>();
		for (int i = 0; i < numOfTiles; i++) {
			tiles.add(this.tiles.remove(0));
		}
		return tiles;
	}

	@Override
	public int getNumOfTilesInBag() {
		return tiles.size();
	}

}
