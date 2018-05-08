package curs.battleship.model;

import curs.battleship.model.PlayBoard.Cell;

public class Distroyer extends AbstractBoat {
  public Distroyer(Cell pCell1, Cell pCell2, Cell pCell3, Cell pCell4, Cell pCell5, Cell pCell6) {
    mCells = new Cell[] { pCell1, pCell2, pCell3, pCell4, pCell5, pCell6 };
  }
}
