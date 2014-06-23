package com.oliverwilkie.scrabble;

import java.util.List;

public interface Board {
	
	public enum State {READY, MID_TURN}
	
	/*
	 * Returns true IFF (x,y) is empty
	 */
	public boolean placeTile(Tile t, char col, int row);
	
	public Tile getTile(char col, int row);
	
	/*
	 * Removes and returns the tile at (x,y) if it is freshly placed
	 */
	public Tile recallTile(char col, int row);

	/*
	 * Returns the freshly placed tiles from the board
	 */
	public List<Tile> recallTiles();
	
	/*
	 * Commits the freshly placed tiles to the board if valid
	 * Returns the score if valid
	 */
	public int playTurn() throws InvalidTurnException;
	
}
