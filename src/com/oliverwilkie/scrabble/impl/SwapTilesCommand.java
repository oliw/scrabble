package com.oliverwilkie.scrabble.impl;

import java.util.List;

import com.oliverwilkie.scrabble.PlayerCommand;
import com.oliverwilkie.scrabble.Tile;

public class SwapTilesCommand extends PlayerCommand {
	
	List<Tile> tiles;
	
	public List<Tile> getTiles() {
		return tiles;
	}

}
