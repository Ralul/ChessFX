package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameController;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // Diagonal directions

    public Bishop(boolean isWhite) {
        super(isWhite, ChessPieceType.BISHOP, true);
    }

    @Override
    public List<Integer[]> getValidMoves(boolean kingIsCapture) {
        List<Integer[]> PiecesInRange = getMovesByDirections(directions, kingIsCapture);

        return PiecesInRange;
    }
}
