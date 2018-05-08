package curs.battleship;

import curs.battleship.controller.PlayController;
import curs.battleship.model.AbstractBoat;
import curs.battleship.model.Boat;
import curs.battleship.model.Distroyer;
import curs.battleship.model.PlayBoard;
import curs.battleship.model.Ship;
import curs.battleship.model.Sub;
import curs.battleship.model.PlayBoard.Cell;
import curs.battleship.ui.Display;
import curs.battleship.ui.UserInterface;
import curs.battleship.ui.console.ConsoleDisplay;
import curs.battleship.ui.console.ConsoleUserInterface;

public class Main {

  public static void main(String[] args) {
    Display display = new ConsoleDisplay();
    PlayController pc = new PlayController(display);
    UserInterface ui = new ConsoleUserInterface();
    ui.start(pc);
  }
}
