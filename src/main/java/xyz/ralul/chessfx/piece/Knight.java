package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

public class Knight extends Piece {

    public Knight(boolean isWhite, int row, int col) {
        super(isWhite,row,col, ChessPieceType.KNIGHT,true);
    }

    @Override
    public int[][] getValidMoves() {
        return new int[0][];
    }
}
