package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Queen extends Piece {
    public Queen(boolean isWhite, int row, int col) {
        super(isWhite,row,col, ChessPieceType.QUEEN,true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        return null;
    }
}
