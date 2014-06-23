package com.oliverwilkie.scrabble;

public interface Player {
	
	public int turn(Board board);
	
	public Rack getRack();
	public boolean hasNoTilesLeft();

}
