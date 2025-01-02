package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite, ChessPieceType.ROOK, true);
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
