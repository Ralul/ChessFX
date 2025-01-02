package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite, ChessPieceType.KNIGHT,true);
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
