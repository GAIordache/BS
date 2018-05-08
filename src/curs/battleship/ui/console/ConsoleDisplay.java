package curs.battleship.ui.console;

import curs.battleship.controller.PlayController;
import curs.battleship.model.AbstractBoat;
import curs.battleship.model.Boat;
import curs.battleship.model.Distroyer;
import curs.battleship.model.PlayBoard;
import curs.battleship.model.Ship;
import curs.battleship.model.Sub;
import curs.battleship.model.PlayBoard.Cell;
import curs.battleship.ui.Display;

public class ConsoleDisplay implements Display {

	@Override
	public void show(PlayBoard pBoard, Cell[] pChanged) {
		System.out.println("  1234567890");
		System.out.println("++++++++++++");
		for (int row = 0; row < PlayBoard.BOARD_SIZE; row++) {
			String rowString = PlayController.rowToString(row) + "|";
			for (int col = 0; col < PlayBoard.BOARD_SIZE; col++) {
				switch (pBoard.getCell(col, row).getOponentStatus()) {
				case DONT_KNOW:
					rowString += "?";
					break;
				case MISS:
					rowString += "x";
					break;
				case HIT:
					rowString += "V";
					break;
				case DEAD:
					AbstractBoat ab = pBoard.getCell(col, row).getBoat();
					if (ab instanceof Boat) {
						rowString += "B";
					} else if (ab instanceof Ship) {
						rowString += "S";
					} else if (ab instanceof Sub) {
						rowString += "U";
					} else if (ab instanceof Distroyer) {
						rowString += "D";
					}
					break;
				}
			}
			System.out.println(rowString + "|");
		}
		System.out.println("++++++++++++");
	}

	@Override
	public void showHiddenBoard(PlayBoard pBoard) {
		System.out.println("  1234567890");
		System.out.println("++++++++++++");
		for (int row = 0; row < PlayBoard.BOARD_SIZE; row++) {
			String rowString = PlayController.rowToString(row) + "|";
			for (int col = 0; col < PlayBoard.BOARD_SIZE; col++) {
				switch (pBoard.getCell(col, row).getPlayerStatus()) {
				case ALIVE:
				case HIT:
					AbstractBoat ab = pBoard.getCell(col, row).getBoat();
					if (ab instanceof Boat) {
						rowString += "B";
					} else if (ab instanceof Ship) {
						rowString += "S";
					} else if (ab instanceof Sub) {
						rowString += "U";
					} else if (ab instanceof Distroyer) {
						rowString += "D";
					}
					break;
				default:
					rowString += "#";
					break;
				}
			}
			System.out.println(rowString + "|");
		}
		System.out.println("++++++++++++");
	}

	@Override
	public void help() {
		System.out.println("Aici urmeaza helpul");
		System.out.println("SAMD");
	}

	@Override
	public void notifyMiss() {
		System.out.println("MISS\007");

	}

	@Override
	public void notifyHit() {
		System.out.println("HIT\007\007\007");

	}

	@Override
	public void notifyDestroy() {
		System.out.println("DESTROY\007\007\007\007\007");

	}

}
