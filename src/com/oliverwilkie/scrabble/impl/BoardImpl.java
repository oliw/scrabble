package com.oliverwilkie.scrabble.impl;

import java.util.LinkedList;
import java.util.List;

import com.oliverwilkie.scrabble.Board;
import com.oliverwilkie.scrabble.InvalidTurnException;
import com.oliverwilkie.scrabble.Tile;

public class BoardImpl implements Board {
	
	Tile[][] board;
	boolean[][] tempMask;
	boolean emptyBoard;
	
	private int toRow(int row) {
		return row-1;
	}
	
	private int toCol(char col) {
		return col - 'A';
	}
	
	public BoardImpl() {
		board = new Tile[15][15];
		tempMask = new boolean[15][15];
		emptyBoard = true;
	}

	@Override
	public boolean placeTile(Tile t, char col, int row) {
		if (board[toRow(row)][toCol(col)] != null) {
			return false;
		} else {
			board[toRow(row)][toCol(col)] = t;
			tempMask[toRow(row)][toCol(col)] = true;
			return true;
		}
	}

	@Override
	public Tile recallTile(char col, int row) {
		if (tempMask[toRow(row)][toCol(col)]) {
			Tile t = board[toRow(row)][toCol(col)];
			board[toRow(row)][toCol(col)] = null;
			tempMask[toRow(row)][toCol(col)] = false;
			return t;
		} else {
			return null;
		}
	}

	@Override
	public List<Tile> recallTiles() {
		List<Tile> tiles = new LinkedList<Tile>();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (tempMask[i][j]) {
					Tile t = board[i][j];
					board[i][j] = null;
					tempMask[i][j] = false;
					tiles.add(t);
				}
			}
		}
		return tiles;
	}

	@Override
	public int playTurn() throws InvalidTurnException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private boolean isMoveValid() {
		
		if (emptyBoard) {
			// Must use middle board
			if (board[toRow(8)][toCol('H')] == null) {
				return false;
			}
		}
		
		// All temp tiles should lie in the same horizontal or vertical line
		int rowsWithContent = 0;
		int lastRowWithContent = -1;
		for (int row = 0; row < 15; row++) {
			int cellsInRow = 0;
			for (int col = 0; col < 15; col++) {
				if (tempMask[row][col]) {
					cellsInRow++;
				}
			}
			if (cellsInRow > 0) {
				rowsWithContent++;
				lastRowWithContent = row;
			}
		}
		
		int colsWithContent = 0;
		int lastColWithContent = -1;
		for (int col = 0; col < 15; col++) {
			int cellsInCol = 0;
			for (int row = 0; row < 15; row++) {
				if (tempMask[row][col]) {
					cellsInCol++;
				}
			}
			if (cellsInCol > 0) {
				colsWithContent++;
				lastColWithContent = col;
			}
		}
		
		// At least the hortizontal or vertical must be all in the same row/col
		if (rowsWithContent != 1 && colsWithContent != 1) {
			return false;
		}
		
		boolean newHorizontalLine = rowsWithContent == 1;
		boolean newVerticalLine = colsWithContent == 1;
		boolean horizontalOK = true;
		boolean verticalOK = true;
		
		// In that new line, it must be continuous
		if (newHorizontalLine) {
			int start = -1;
			int end = 16;
			while (!tempMask[lastRowWithContent][start+1]) {
				start++;
			}
			while (!tempMask[lastRowWithContent][end-1]) {
				end--;
			}
			boolean containsPrexisting = false;
			boolean containsGap = false;
			int curr = start;
			while (curr != end) {
				containsGap = containsGap || (!tempMask[lastRowWithContent][curr] && board[lastRowWithContent][curr]==null);
				containsPrexisting = containsPrexisting || board[lastRowWithContent][curr]!=null;
				curr++;
			}
			if (containsGap) {
				horizontalOK = horizontalOK && false;
			}
			if (!containsPrexisting) {
				boolean preexistingAtExtremeEnds = false;
				if (start > 0) {
					preexistingAtExtremeEnds = preexistingAtExtremeEnds || board[lastRowWithContent][start-1]!=null;
				}
				if (end < 15) {
					preexistingAtExtremeEnds = preexistingAtExtremeEnds || board[lastRowWithContent][end+1]!=null;					
				}
				if (!preexistingAtExtremeEnds) {
					horizontalOK = horizontalOK && false;
				}
			}
		} 
		
		if (newVerticalLine) {
			int start = -1;
			int end = 16;
			while (!tempMask[start+1][lastColWithContent]) {
				start++;
			}
			while (!tempMask[end-1][lastColWithContent]) {
				end--;
			}
			boolean containsPrexisting = false;
			boolean containsGap = false;
			int curr = start;
			while (curr != end) {
				containsGap = containsGap || (!tempMask[curr][lastColWithContent] && board[curr][lastColWithContent]==null);
				containsPrexisting = containsPrexisting || board[curr][lastColWithContent]!=null;
				curr++;
			}
			if (containsGap) {
				verticalOK = verticalOK && false;
			}
			if (!containsPrexisting) {
				boolean preexistingAtExtremeEnds = false;
				if (start > 0) {
					preexistingAtExtremeEnds = preexistingAtExtremeEnds || board[start-1][lastColWithContent]!=null;
				}
				if (end < 15) {
					preexistingAtExtremeEnds = preexistingAtExtremeEnds || board[end+1][lastColWithContent]!=null;					
				}
				if (!preexistingAtExtremeEnds) {
					verticalOK = verticalOK && false;
				}
			}			
		}
		
		return horizontalOK || verticalOK;
		
	}

	@Override
	public Tile getTile(char col, int row) {
		return board[toRow(row)][toCol(col)];
	}

}
