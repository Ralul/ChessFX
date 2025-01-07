package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Knight extends Piece {

    private int[][] directions = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

    public Knight(boolean isWhite) {
        super(isWhite, ChessPieceType.KNIGHT, true);
    }

    @Override
    public List<Integer[]> getValidMoves(boolean kingIsCapture) {
        List<Integer[]> PiecesInRange = getMovesByDirections(directions, false, kingIsCapture);

        return PiecesInRange;
    }
}
