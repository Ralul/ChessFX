package xyz.ralul.chessfx;

public class Position {
    private int row;
    private int col;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public boolean equals(Position pos) {
        if(this.row == pos.row && this.col == pos.col) {
            return true;
        }
        return false;
    }
}
