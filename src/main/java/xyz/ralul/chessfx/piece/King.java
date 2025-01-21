package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.Position;

import java.util.List;

public class King extends Piece implements Cloneable{

    static final int[][] DIRECTIONS = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};

    public King(boolean isWhite) {
        super(isWhite, ChessPieceType.KING, false,DIRECTIONS,false);
    }

    @Override
    public List<Position> getValidMoves(boolean kingIsCapture) {
        List<Position> PiecesInRange = getMovesByDirections(false, kingIsCapture);

        return PiecesInRange;
    }

    @Override
    public King clone() {
        King clone = (King) super.clone();
        return clone;
    }
}
