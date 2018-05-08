package curs.battleship.model;

import curs.battleship.model.PlayBoard.Cell;

public class Boat extends AbstractBoat {
  public Boat(Cell pCell) {
    mCells = new Cell[] { pCell };
  }
}
