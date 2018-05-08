package curs.battleship.ui;

import curs.battleship.model.PlayBoard;

public interface Display {
	public void show(PlayBoard pBoard,PlayBoard.Cell[] pChanged);
	public void showHiddenBoard(PlayBoard pBoard);
	void help();
	//
	void notifyMiss();
	void notifyHit();
	void notifyDestroy();
}
