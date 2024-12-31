package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Knight extends Piece {

    public Knight(boolean isWhite, int row, int col) {
        super(isWhite,row,col, ChessPieceType.KNIGHT,true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        return null;
    }
}
