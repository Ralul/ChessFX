package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite, ChessPieceType.KING,false);
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
