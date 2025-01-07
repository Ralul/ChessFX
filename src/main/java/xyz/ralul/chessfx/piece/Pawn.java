package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameController;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite, ChessPieceType.PAWN, true);
    }

    @Override
    public List<Integer[]> getValidMoves(boolean kingIsCapture) {
        List<Integer[]> moves = new ArrayList<>();
        int rowDirection = this.isWhite() ? -1 : 1;

        int startRow = this.getRow();
        int startCol = this.getCol();

        if (GameController.getBoard().getPiece(startRow + rowDirection, startCol) == null) {
            moves.add(new Integer[]{startRow + rowDirection, startCol});
        }
        if(!isMoved()) {
            if ((isWhite() && startRow == 6) || (!isWhite() && startRow == 1)) {
                if (GameController.getBoard().getPiece(startRow + (rowDirection * 2), startCol) == null) {
                    moves.add(new Integer[]{startRow + (rowDirection * 2), startCol});
                }
            }
        }

        int[] colDirections = {1, -1};

        for (int direction : colDirections) {
            int catchRow = startRow + rowDirection;
            int catchCol = startCol + direction;
            Piece targetPiece = GameController.getBoard().getPiece(catchRow, catchCol);
            if (targetPiece != null && targetPiece.isCatchbleBy(this, kingIsCapture)) {
                moves.add(new Integer[]{catchRow, catchCol});
            }
        }
        return moves;
    }
}
