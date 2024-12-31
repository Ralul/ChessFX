package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

public class Bishop extends Piece {

    public Bishop(boolean isWhite,int row,int col) {
        super(isWhite,row,col, ChessPieceType.BISHOP,true);
    }

    @Override
    public int[][] getValidMoves() {
        return new int[0][];
    }
}
