package curs.battleship.ui;

public interface UserInterfaceCallback {
  void shoot(int pX, int pY);

  void showHiddenBoard();

  void restartGame();

  void refresh();

  void help();

  void exitGame();
}
