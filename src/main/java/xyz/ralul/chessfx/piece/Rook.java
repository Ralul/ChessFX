package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

public class Rook extends Piece {

    public Rook(boolean isWhite, int row, int col, ChessPieceType type) {
        super(isWhite, row, col, ChessPieceType.ROOK,true);
    }

    @Override
    public int[][] getValidMoves() {
        return new int[0][];
    }

}
