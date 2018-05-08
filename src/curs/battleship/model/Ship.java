package curs.battleship.model;

import curs.battleship.model.PlayBoard.Cell;

public class Ship extends AbstractBoat {
  public Ship(Cell pCell1, Cell pCell2, Cell pCell3) {
    mCells = new Cell[] { pCell1, pCell2, pCell3 };
  }
}
