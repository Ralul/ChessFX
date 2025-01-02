package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite, ChessPieceType.BISHOP, true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        return null;
    }

    @Override
    public List<Integer[]> getValidCatches() {
        return List.of();
    }
}
