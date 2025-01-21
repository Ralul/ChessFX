package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.Position;

import java.util.List;

public class Queen extends Piece implements Cloneable {

    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public Queen(boolean isWhite) {
        super(isWhite, ChessPieceType.QUEEN, true,DIRECTIONS,true);
    }

    @Override
    public List<Position> getValidMoves(boolean kingIsCapture) {
        List<Position> PiecesInRange = getMovesByDirections(true, kingIsCapture);

        return PiecesInRange;
    }

    @Override
    public Queen clone() {
        Queen clone = (Queen) super.clone();
        return clone;
    }
}
