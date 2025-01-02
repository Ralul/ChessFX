package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite, ChessPieceType.QUEEN,true);
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
