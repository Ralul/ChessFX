package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.GameController;
import xyz.ralul.chessfx.Move;
import xyz.ralul.chessfx.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece implements Cloneable {

    public Pawn(boolean isWhite) {
        super(isWhite, ChessPieceType.PAWN, true,false);
    }


    public void updateMoves() {
        moves.clear();
        int rowDirection = this.isWhite() ? -1 : 1;

        if (GameController.getBoard().getPiece(row,rowDirection) == null) {
            moves.add(new Move())
        }
    }

    @Override
    public List<Position> getValidMoves(boolean kingIsCapture) {
        List<Position> moves = new ArrayList<>();
        int rowDirection = this.isWhite() ? -1 : 1;

        int startRow = this.getRow();
        int startCol = this.getCol();

        if (GameController.getBoard().getPiece(startRow + rowDirection, startCol) == null) {
            moves.add(new Position(startRow + rowDirection, startCol));
        }
        if (!isMoved()) {
            if ((isWhite() && startRow == 6) || (!isWhite() && startRow == 1)) {
                if (GameController.getBoard().getPiece(startRow + (rowDirection * 2), startCol) == null) {
                    moves.add(new Position(startRow + (rowDirection * 2), startCol));
                }
            }
        }

        int[] colDirections = {1, -1};

        for (int direction : colDirections) {
            int catchRow = startRow + rowDirection;
            int catchCol = startCol + direction;
            Piece targetPiece = GameController.getBoard().getPiece(catchRow, catchCol);
            if (targetPiece != null && targetPiece.isCatchbleBy(this, kingIsCapture)) {
                moves.add(new Position(catchRow, catchCol));
            }
        }
        return moves;
    }

    @Override
    public Pawn clone() {
        return (Pawn) super.clone();
    }
}
