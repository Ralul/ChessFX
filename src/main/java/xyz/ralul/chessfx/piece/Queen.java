package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

public class Queen extends Piece {
    public Queen(boolean isWhite, int row, int col) {
        super(isWhite,row,col, ChessPieceType.QUEEN,true);
    }

    @Override
    public int[][] getValidMoves() {
        return new int[0][];
    }
}
