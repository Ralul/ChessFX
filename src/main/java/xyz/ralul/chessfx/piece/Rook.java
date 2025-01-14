package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.Position;

import java.util.List;

public class Rook extends Piece implements Cloneable {

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public Rook(boolean isWhite) {
        super(isWhite, ChessPieceType.ROOK, true);
    }

    @Override
    public List<Position> getValidMoves(boolean kingIsCapture) {
        List<Position> PiecesInRange = getMovesByDirections(directions, true, kingIsCapture);

        return PiecesInRange;
    }

    @Override
    public Rook clone() {
        Rook clone = (Rook) super.clone();
        return clone;
    }
}
