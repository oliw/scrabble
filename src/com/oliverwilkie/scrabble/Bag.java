package com.oliverwilkie.scrabble;

import java.util.List;

public interface Bag {
	
	public List<Tile> swapTiles(List<Tile> tiles) throws TooFewTilesInBagException;
	
	public List<Tile> takeTiles(int numOfTiles);
	
	public int getNumOfTilesInBag();

}
