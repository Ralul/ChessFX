package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(boolean isWhite,int row,int col) {
        super(isWhite,row,col, ChessPieceType.BISHOP,true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        return null;
    }
}
