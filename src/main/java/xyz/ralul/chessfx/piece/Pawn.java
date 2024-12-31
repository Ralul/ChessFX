package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

public class Pawn extends Piece {

    public Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col, ChessPieceType.PAWN,true);
    }

    @Override
    public int[][] getValidMoves() {

        int direction = this.isWhite() ? 1 : -1;

        int startCol = this.getCol();
        int startRow = this.getRow();

        int endCol = startCol;
        int endRow = startRow + direction;

        int [][] validMoves = {{endRow,endCol}};

        return validMoves;
    }
}
