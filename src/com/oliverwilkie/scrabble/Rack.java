package com.oliverwilkie.scrabble;

import java.util.List;

public interface Rack {

	public List<Tile> getTiles();
	
	public void addTile(Tile t);
	public void addTiles(List<Tile> tiles);
	public Tile removeTile(Tile t);
	public void removeTiles(List<Tile> tiles);
	
	public int numOfTilesOnRack();
	public int numOfSpacesOnRack();
	
}
