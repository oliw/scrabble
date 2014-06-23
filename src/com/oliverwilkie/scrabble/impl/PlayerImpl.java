package com.oliverwilkie.scrabble.impl;

import java.util.List;

import com.oliverwilkie.scrabble.Bag;
import com.oliverwilkie.scrabble.Board;
import com.oliverwilkie.scrabble.InvalidTurnException;
import com.oliverwilkie.scrabble.Player;
import com.oliverwilkie.scrabble.PlayerCommand;
import com.oliverwilkie.scrabble.Rack;
import com.oliverwilkie.scrabble.Tile;
import com.oliverwilkie.scrabble.TooFewTilesInBagException;

public class PlayerImpl implements Player {
	
	private final Bag bag;
	private final Rack rack;
	
	public PlayerImpl(Bag bag) {
		this.bag = bag;
		rack = new RackImpl();
		// Fill rack
		rack.addTiles(bag.takeTiles(7));
	}

	@Override
	public int turn(Board board) {

		int score = 0;

		while (true) {
			PlayerCommand command = getCommand();
			
			if (command instanceof PassTurnCommand) {
				// Recall all tiles from board
				List<Tile> tiles = board.recallTiles();
				rack.addTiles(tiles);
				// break
				break;
			} else if (command instanceof PlaceTileCommand) {
				PlaceTileCommand placeTileCommand = (PlaceTileCommand) command;
				// Remove tile from rack
				Tile tile = rack.removeTile(placeTileCommand.getTile());
				// Place on board
				boolean placed = board.placeTile(tile, placeTileCommand.x(), placeTileCommand.y());
				if (!placed) {
					rack.addTile(tile);
				}
			} else if (command instanceof SwapTilesCommand) {
				SwapTilesCommand swapCommand = (SwapTilesCommand) command;
				// Remove tiles from rack
				List<Tile> tilesToSwap = swapCommand.getTiles();
				rack.removeTiles(tilesToSwap);
				// Swap with tiles in bag
				try {
					List<Tile> newTiles = bag.swapTiles(tilesToSwap);
					rack.addTiles(newTiles);
				} catch (TooFewTilesInBagException e) {
					rack.addTiles(tilesToSwap);
				}
				// break
				break;
			} else if (command instanceof PlayTurnCommand) {
				try {
					// Check board
					score = board.playTurn();
					break;
				} catch (InvalidTurnException e) {
					rack.addTiles(board.recallTiles());
				}
			}
		}
		
		// Fill rack with tiles from bag if possible
		rack.addTiles(bag.takeTiles(rack.numOfSpacesOnRack()));
	
		return score;
	}
	
	private PlayerCommand getCommand() {
		return new PassTurnCommand();
	}

	@Override
	public boolean hasNoTilesLeft() {
		return rack.numOfTilesOnRack() == 0;
	}

	@Override
	public Rack getRack() {
		return rack;
	}

}
