package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.Position;

import java.util.List;

public class Knight extends Piece implements Cloneable{
    static final int[][] DIRECTIONS = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

    public Knight(boolean isWhite) {
        super(isWhite, ChessPieceType.KNIGHT, true, DIRECTIONS);
    }

    @Override
    public List<Position> getValidMoves(boolean kingIsCapture) {
        List<Position> PiecesInRange = getMovesByDirections(false, kingIsCapture);

        return PiecesInRange;
    }

    @Override
    public Knight clone() {
        Knight clone = (Knight) super.clone();
        return clone;
    }
}
