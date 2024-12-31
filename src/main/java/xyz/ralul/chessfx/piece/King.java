package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

public class King extends Piece {
    public King(boolean isWhite,int row,int col) {
        super(isWhite,row,col, ChessPieceType.KING,false);
    }

    @Override
    public int[][] getValidMoves() {
        return new int[0][];
    }
}
