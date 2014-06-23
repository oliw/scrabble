package com.oliverwilkie.scrabble.impl;

import com.oliverwilkie.scrabble.Board;
import com.oliverwilkie.scrabble.Display;
import com.oliverwilkie.scrabble.Rack;
import com.oliverwilkie.scrabble.Tile;

public class ConsoleDisplay implements Display {

	@Override
	public void displayBoard(Board board) {
		
		StringBuilder sb = new StringBuilder();
	
		// Print column numbers
		sb.append("    A B C D E F G H I J K L M N O\n\n");
		for (int row = 0; row < 15; row++) {
			int rowHuman = row+1;
			if (rowHuman <  10) {
				sb.append("0"+rowHuman+"  ");
			} else {
				sb.append(rowHuman+"  ");
			}
			for (char col = 'A'; col <= 'O'; col++) {
				Tile t = board.getTile(col, row);
				if (t == null) {
					sb.append("  ");
				} else {
					sb.append(t.getLetterValue()+" ");
				}
			}
			sb.append("/n");
		}
		sb.append("/n");
		
		System.out.println(sb.toString());

	}

	@Override
	public void displayRack(Rack rack) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n");

		for (Tile t: rack.getTiles()) {
			if (t instanceof BlankTile) {
				sb.append("*");
			} else {
				sb.append(t.getLetterValue()+" ");
			}
		}
		
		sb.append("\n");
		
		System.out.println(sb.toString());
	}

}
