package com.oliverwilkie.scrabble.impl;

import com.oliverwilkie.scrabble.PlayerCommand;
import com.oliverwilkie.scrabble.Tile;

public class PlaceTileCommand extends PlayerCommand {
	
	Tile tile;
	int x;
	int y;
	
	public Tile getTile() {
		return tile;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}

}
