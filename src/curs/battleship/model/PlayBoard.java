package curs.battleship.model;

public class PlayBoard {
  public static final int BOARD_SIZE = 10;

  public static class Cell {
    private int mX;
    private int mY;
    private PlayerStatus mPlayerStatus = PlayerStatus.DONT_CARE;
    private OpponentStatus mOponentStatus = OpponentStatus.DONT_KNOW;
    private AbstractBoat mBoat = null;

    public Cell(int pX, int pY) {
      mX = pX;
      mY = pY;
    }

    public int getX() {
      return mX;
    }

    public void setX(int pX) {
      mX = pX;
    }

    public int getY() {
      return mY;
    }

    public void setY(int pY) {
      mY = pY;
    }

    public PlayerStatus getPlayerStatus() {
      return mPlayerStatus;
    }

    public void setPlayerStatus(PlayerStatus pPlayerStatus) {
      mPlayerStatus = pPlayerStatus;
    }

    public OpponentStatus getOponentStatus() {
      return mOponentStatus;
    }

    public void setOponentStatus(OpponentStatus pOponentStatus) {
      mOponentStatus = pOponentStatus;
    }

    public AbstractBoat getBoat() {
      return mBoat;
    }

    public void setBoat(AbstractBoat pBoat) {
      mBoat = pBoat;
    }

    public boolean sameCoordinates(int pX, int pY) {
      return mX == pX && mY == pY;
    }
  }

  private Cell[][] mCells;

  public PlayBoard() {
    mCells = new Cell[10][10];
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 10; y++) {
        mCells[x][y] = new Cell(x, y);
      }
    }
  }

  public Cell getCell(int pX, int pY) {
    return mCells[pX][pY];
  }

}
