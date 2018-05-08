package curs.battleship.controller;

import java.util.Random;

import curs.battleship.model.AbstractBoat;
import curs.battleship.model.Boat;
import curs.battleship.model.Distroyer;
import curs.battleship.model.OpponentStatus;
import curs.battleship.model.PlayBoard;
import curs.battleship.model.PlayBoard.Cell;
import curs.battleship.model.PlayerStatus;
import curs.battleship.model.Ship;
import curs.battleship.model.Sub;
import curs.battleship.ui.Display;
import curs.battleship.ui.UserInterfaceCallback;

public class PlayController implements UserInterfaceCallback {
  public static final int DISTROYER_COUNT = 1;
  public static final int SUB_COUNT = 2;
  public static final int SHIP_COUNT = 3;
  public static final int BOAT_COUNT = 3;

  private PlayBoard mPlayboard;
  private Display mDisplay;
  private AbstractBoat[] mBoats;
  private Random mRandom = new Random();

  public PlayController(Display pDisplay) {
    mDisplay = pDisplay;
    initializePlayboard();
    mDisplay.show(mPlayboard, null);
  }

  private boolean checkIfLocationAvailable(Cell[][] pCellMatrix, int pX, int pY, int pLevel) {
    for (int i = pLevel - 1; i >= 0; i--) {
      if (pCellMatrix[i][0].sameCoordinates(pX, pY)) {
        return false;
      }
    }
    return true;
  }

  private void fillPossibleExtensionCells(Cell[][] pCellMatrix, int pLevel) {
    int idx = 0;
    Cell c = pCellMatrix[pLevel - 1][0];
    int x = c.getX();
    int y = c.getY();
    if (x > 0 && checkIfLocationAvailable(pCellMatrix, x - 1, y, pLevel) && checkBoatAcceptedLocation(x - 1, y)) {
      pCellMatrix[pLevel][idx] = mPlayboard.getCell(x - 1, y);
      idx = idx + 1;
    }
    if (y > 0 && checkIfLocationAvailable(pCellMatrix, x, y - 1, pLevel) && checkBoatAcceptedLocation(x, y - 1)) {
      pCellMatrix[pLevel][idx] = mPlayboard.getCell(x, y - 1);
      idx = idx + 1;
    }
    if (x < PlayBoard.BOARD_SIZE - 1 && checkIfLocationAvailable(pCellMatrix, x + 1, y, pLevel)
        && checkBoatAcceptedLocation(x + 1, y)) {
      pCellMatrix[pLevel][idx] = mPlayboard.getCell(x + 1, y);
      idx = idx + 1;
    }
    if (y < PlayBoard.BOARD_SIZE - 1 && checkIfLocationAvailable(pCellMatrix, x, y + 1, pLevel)
        && checkBoatAcceptedLocation(x, y + 1)) {
      pCellMatrix[pLevel][idx] = mPlayboard.getCell(x, y + 1);
      idx = idx + 1;
    }
  }

  private boolean allocateRandomCells(Cell[][] pCellMatrix, int pLevel, int pNum) {
    if (pLevel == pNum) {
      return true;
    }
    fillPossibleExtensionCells(pCellMatrix, pLevel);
    if (pCellMatrix[pLevel][0] == null) {
      return false;
    }
    int rndSel = mRandom.nextInt(7);
    Cell nextCell = null;
    for (int i = 0; i < pCellMatrix[pLevel].length; i++) {
      if (pCellMatrix[pLevel][i] != null) {
        if (rndSel == 0) {
          nextCell = pCellMatrix[pLevel][i];
          if (i != 0) {
            pCellMatrix[pLevel][i] = pCellMatrix[pLevel][0];
            pCellMatrix[pLevel][0] = nextCell;
          }
          break;
        } else {
          rndSel = rndSel - 1;
        }
      }
    }
    while (true) {
      if (allocateRandomCells(pCellMatrix, pLevel + 1, pNum)) {
        return true;
      } else {
        for (int i = 0; i < pCellMatrix[pLevel].length - 1; i++) {
          pCellMatrix[pLevel][i] = pCellMatrix[pLevel][i + 1];
        }
        if (pCellMatrix[pLevel][0] == null) {
          return false;
        }
      }
    }
  }

  private Cell[] allocateRandomCells(int pNum) {
    int tryCount = 0;
    while (tryCount < 100) {
      int col = mRandom.nextInt(10);
      int row = mRandom.nextInt(10);
      while (!checkBoatAcceptedLocation(col, row)) {
        col = mRandom.nextInt(10);
        row = mRandom.nextInt(10);
      }
      // valid col & row values
      Cell[][] cellMatrix = new Cell[pNum][];
      cellMatrix[0] = new Cell[] { mPlayboard.getCell(col, row) };
      if (pNum > 1) {
        cellMatrix[1] = new Cell[4];
        for (int i = 2; i < pNum; i++) {
          cellMatrix[i] = new Cell[3];
        }
      }
      if (allocateRandomCells(cellMatrix, 1, pNum)) {
        Cell[] result = new Cell[pNum];
        for (int i = 0; i < pNum; i++) {
          result[i] = cellMatrix[i][0];
          result[i].setPlayerStatus(PlayerStatus.ALIVE);
        }
        return result;
      }
      tryCount = tryCount + 1;
    }
    System.out.println("Try count limit exceded");
    return null;
  }

  private boolean checkBoatAcceptedLocation(int pX, int pY) {
    for (int i = 0; i < mBoats.length; i++) {
      if (mBoats[i] == null) {
        break;
      }
      if (!mBoats[i].isLocationAvailable(pX, pY)) {
        return false;
      }
    }
    return true;
  }

  private void initializePlayboard() {
    mPlayboard = new PlayBoard();
    mBoats = new AbstractBoat[9];
    Cell[] cells = allocateRandomCells(6);
    mBoats[0] = new Distroyer(cells[0], cells[1], cells[2], cells[3], cells[4], cells[5]);
    for (Cell c : cells) {
      c.setBoat(mBoats[0]);
    }
    for (int i = 0; i < SUB_COUNT; i++) {
      cells = allocateRandomCells(5);
      mBoats[DISTROYER_COUNT + i] = new Sub(cells[0], cells[1], cells[2], cells[3], cells[4]);
      for (Cell c : cells) {
        c.setBoat(mBoats[DISTROYER_COUNT + i]);
      }
    }
    for (int i = 0; i < SHIP_COUNT; i++) {
      cells = allocateRandomCells(3);
      mBoats[DISTROYER_COUNT + SUB_COUNT + i] = new Ship(cells[0], cells[1], cells[2]);
      for (Cell c : cells) {
        c.setBoat(mBoats[DISTROYER_COUNT + SUB_COUNT + i]);
      }
    }
    for (int i = 0; i < BOAT_COUNT; i++) {
      cells = allocateRandomCells(1);
      mBoats[DISTROYER_COUNT + SUB_COUNT + SHIP_COUNT + i] = new Boat(cells[0]);
      for (Cell c : cells) {
        c.setBoat(mBoats[DISTROYER_COUNT + SUB_COUNT + SHIP_COUNT + i]);
      }
    }
  }

  @Override
  public void shoot(int pX, int pY) {
    Cell[] modifiedCells = null;
    Cell currentCell = mPlayboard.getCell(pX, pY);
    if (currentCell.getPlayerStatus().equals(PlayerStatus.DONT_CARE)) {
      currentCell.setOponentStatus(OpponentStatus.MISS);
      modifiedCells = new Cell[] { currentCell };
      mDisplay.notifyMiss();
    } else if (currentCell.getPlayerStatus().equals(PlayerStatus.ALIVE)) {
      currentCell.setPlayerStatus(PlayerStatus.HIT);
      if (currentCell.getBoat().isAlive()) {
        currentCell.setOponentStatus(OpponentStatus.HIT);
        modifiedCells = new Cell[] { currentCell };
        mDisplay.notifyHit();
      } else {
        for (Cell c : currentCell.getBoat().getCells()) {
          c.setOponentStatus(OpponentStatus.DEAD);
        }
        modifiedCells = currentCell.getBoat().getCells();
        mDisplay.notifyDestroy();
      }
    }
    mDisplay.show(mPlayboard, modifiedCells);
  }

  @Override
  public void showHiddenBoard() {
    mDisplay.showHiddenBoard(mPlayboard);
  }

  @Override
  public void restartGame() {
    initializePlayboard();
    mDisplay.show(mPlayboard, null);
  }

  @Override
  public void exitGame() {
    System.exit(0);
  }

  @Override
  public void refresh() {
    mDisplay.show(mPlayboard, null);
  }

  @Override
  public void help() {
    mDisplay.help();

  }

  ///////////////////////////////////////////
  // Static methods
  ///////////////////////////////////////////
  public static String rowToString(int pRow) {
    char charCode = (char) ('A' + pRow);
    return "" + new Character(charCode);
  }
}
