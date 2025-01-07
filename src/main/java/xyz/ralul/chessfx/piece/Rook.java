package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameController;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public Rook(boolean isWhite) {
        super(isWhite, ChessPieceType.ROOK, true);
    }

    @Override
    public List<Integer[]> getValidMoves(boolean kingIsCapture) {
        List<Integer[]> PiecesInRange = getMovesByDirections(directions, true, kingIsCapture);

        return PiecesInRange;
    }
}
