package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public class Queen extends Piece {

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public Queen(boolean isWhite) {
        super(isWhite, ChessPieceType.QUEEN, true);
    }

    @Override
    public List<Integer[]> getValidMoves(boolean kingIsCapture) {
        List<Integer[]> PiecesInRange = getMovesByDirections(directions, true, kingIsCapture);

        return PiecesInRange;
    }
}
