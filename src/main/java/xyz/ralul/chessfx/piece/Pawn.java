package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite, ChessPieceType.PAWN, true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        List<Integer[]> moves = new ArrayList<>();
        int direction = this.isWhite() ? -1 : 1;

        int startRow = this.getRow();
        int startCol = this.getCol();

        moves.add(new Integer[]{startRow + direction, startCol});

        if ((isWhite() && startRow == 6) || (!isWhite() && startRow == 1)) {
            moves.add(new Integer[]{startRow + (direction * 2), startCol});
        }

        return moves;
    }

    public List<Integer[]> getValidCatches() {
        List<Integer[]> catches = new ArrayList<>();
        int rowDirection = this.isWhite() ? -1 : 1;
        int[] colDirections = {1, -1};

        int startRow = this.getRow();
        int startCol = this.getCol();

        for (int direction : colDirections) {
            int catchRow = startRow + rowDirection;
            int catchCol = startCol + direction;
            if (GameLogic.getBoard().getPiece(catchRow, catchCol) != null) {
                catches.add(new Integer[]{catchRow, catchCol});
            }
        }
        return catches;
    }
}
