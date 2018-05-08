package curs.battleship.ui.console;

import java.util.Scanner;

import curs.battleship.ui.UserInterface;
import curs.battleship.ui.UserInterfaceCallback;

public class ConsoleUserInterface implements UserInterface {

  @Override
  public void start(UserInterfaceCallback pCallback) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("\nH-help,R-refresh,X-exit,S-restart,K-show board,[A-J][0-9] shoot\n>>");
      if (scanner.hasNext()) {
        String cmd = scanner.next();
        cmd = cmd.toUpperCase();
        if ("H".equals(cmd)) {
          pCallback.help();
        } else if ("R".equals(cmd)) {
          pCallback.refresh();
        } else if ("X".equals(cmd)) {
          pCallback.exitGame();
        } else if ("S".equals(cmd)) {
          pCallback.restartGame();
        } else if ("K".equals(cmd)) {
          pCallback.showHiddenBoard();
        } else {
          if (cmd.length() == 2) {
            char c0 = cmd.charAt(0);
            char c1 = cmd.charAt(1);
            if (c0 >= 'A' && c0 <= 'J') {
              if (c1 >= '0' && c1 <= '9') {
                pCallback.shoot(c1 - '0', c0 - 'A');
              }
            }
          }
        }
      }
    }

  }

}
