package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class King extends Piece {
    public King(boolean isWhite,int row,int col) {
        super(isWhite,row,col, ChessPieceType.KING,false);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        return null;
    }
}
