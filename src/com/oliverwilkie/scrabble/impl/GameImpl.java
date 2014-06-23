package com.oliverwilkie.scrabble.impl;

import java.util.List;

import com.oliverwilkie.scrabble.Bag;
import com.oliverwilkie.scrabble.Board;
import com.oliverwilkie.scrabble.Game;
import com.oliverwilkie.scrabble.Player;
import com.oliverwilkie.scrabble.Rack;
import com.oliverwilkie.scrabble.Tile;

public class GameImpl implements Game {
	
	private Board board;
	private Bag bag;
	private Player[] players;
	private int[] playerScores;
	
	
	public GameImpl(int numOfPlayers) {
		
		// Init Board
		board = new BoardImpl();
		
		// Init Bag
		bag = new BagImpl();
		
		// Init Players
		if (numOfPlayers < 2) {
			throw new IllegalArgumentException();
		}
		players = new Player[numOfPlayers];
		playerScores = new int[numOfPlayers];
		for (int i = 0; i < numOfPlayers; i++) {
			players[i] = new PlayerImpl(bag);
			playerScores[i] = 0;
		}
		
	}

	@Override
	public void start() {
		
		// Take turns loop
		Integer currPlayerIdx = null;
		Player currPlayer = null;
		do {
			currPlayerIdx = (currPlayerIdx == null) ? 0 : (currPlayerIdx + 1) % players.length;
			currPlayer = players[currPlayerIdx];
			currPlayer.turn(board);
		} while (!currPlayer.hasNoTilesLeft());
	
		// Finally update scores of players
		for (int i = 0; i < players.length; i++) {
			Player player = players[i];
			Rack playerRack = player.getRack();
			List<Tile> remainingTiles = playerRack.getTiles();
			for (Tile t: remainingTiles) {
				// Remove tile value from player's score
				playerScores[i] = playerScores[i] - t.getScoreValue();
				// Add tile value to winner's score
				playerScores[currPlayerIdx] = playerScores[currPlayerIdx] + t.getScoreValue();
			}
		}
		
		// Print scores
		for (int i = 0; i < playerScores.length; i++) {
			System.out.println("Player "+i+" scored: "+playerScores[i]);
		}
	}
	

}
