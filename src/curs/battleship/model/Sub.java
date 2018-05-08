package curs.battleship.model;

import curs.battleship.model.PlayBoard.Cell;

public class Sub extends AbstractBoat {
  public Sub(Cell pCell1, Cell pCell2, Cell pCell3, Cell pCell4, Cell pCell5) {
    mCells = new Cell[] { pCell1, pCell2, pCell3, pCell4, pCell5 };
  }
}
