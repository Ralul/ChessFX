package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Rook extends Piece {

    public Rook(boolean isWhite, int row, int col, ChessPieceType type) {
        super(isWhite, row, col, ChessPieceType.ROOK,true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        return null;
    }

}
