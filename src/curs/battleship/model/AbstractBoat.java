package curs.battleship.model;

import curs.battleship.model.PlayBoard.Cell;

public abstract class AbstractBoat {
  protected PlayBoard.Cell[] mCells;

  public Cell[] getCells() {
    return mCells;
  }

  public boolean isAlive() {
    for (PlayBoard.Cell cell : mCells) {
      if (cell.getPlayerStatus().equals(PlayerStatus.ALIVE)) {
        return true;
      }
    }
    return false;
  }

  public boolean isDead() {
    return !isAlive();
  }

  public boolean isLocationAvailable(int pX, int pY) {
    for (Cell cell : mCells) {
      if (pX == cell.getX() && pY == cell.getY()) {
        return false;
      }
      if (pX + 1 == cell.getX() && pY == cell.getY()) {
        return false;
      }
      if (pX == cell.getX() && pY + 1 == cell.getY()) {
        return false;
      }
      if (pX - 1 == cell.getX() && pY == cell.getY()) {
        return false;
      }
      if (pX == cell.getX() && pY - 1 == cell.getY()) {
        return false;
      }
    }
    return true;
  }

  public int getLen() {
    return mCells.length;
  }
}
