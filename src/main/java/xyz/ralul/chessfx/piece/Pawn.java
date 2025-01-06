package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite, ChessPieceType.PAWN, true);
    }

    public List<Integer[]> getValidMoves() {
        List<Integer[]> moves = new ArrayList<>();
        int direction = this.isWhite() ? -1 : 1;

        int startRow = this.getRow();
        int startCol = this.getCol();

        if (GameLogic.getBoard().getPiece(startRow + direction, startCol) == null) {
            moves.add(new Integer[]{startRow + direction, startCol});
        }

        if ((isWhite() && startRow == 6) || (!isWhite() && startRow == 1)) {
            if (GameLogic.getBoard().getPiece(startRow + (direction * 2), startCol) == null) {
                moves.add(new Integer[]{startRow + (direction * 2), startCol});
            }
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
            Piece targetPiece = GameLogic.getBoard().getPiece(catchRow, catchCol);
            if (targetPiece != null && targetPiece.isCatchbleBy(this)) {
                catches.add(new Integer[]{catchRow, catchCol});
            }
        }
        return catches;
    }
}
