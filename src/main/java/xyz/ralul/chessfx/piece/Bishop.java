package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.Position;

import java.util.List;

public class Bishop extends Piece implements Cloneable {

    private int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // Diagonal directions

    public Bishop(boolean isWhite) {
        super(isWhite, ChessPieceType.BISHOP, true);
    }

    @Override
    public List<Position> getValidMoves(boolean kingIsCapture) {
        List<Position> PiecesInRange = getMovesByDirections(directions, true, kingIsCapture);

        return PiecesInRange;
    }

    @Override
    public Bishop clone() {
        Bishop clone = (Bishop) super.clone();
        return clone;
    }
}
