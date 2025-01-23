package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.Position;

import java.util.List;

public class Knight extends Piece implements Cloneable{

    private int[][] directions = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

    public Knight(boolean isWhite) {
        super(isWhite, ChessPieceType.KNIGHT, true);
    }

    @Override
    public List<Position> getValidMoves(boolean kingIsCapture) {
        List<Position> PiecesInRange = getMovesByDirections(directions, false, kingIsCapture);

        return PiecesInRange;
    }

    @Override
    public Knight clone() {
        Knight clone = (Knight) super.clone();
        return clone;
    }
}
