package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.GameController;
import xyz.ralul.chessfx.Move;
import xyz.ralul.chessfx.Position;

public class Pawn extends Piece implements Cloneable {

    public Pawn(boolean isWhite) {
        super(isWhite, ChessPieceType.PAWN, true, false);
    }

    public void updateMoves() {
        moves.clear();
        int rowDirection = this.isWhite() ? -1 : 1;
        int[] colDirections = {1, -1};

        if (GameController.getBoard().getPiece(row, rowDirection) == null) {
            moves.add(new Move(new Position(row + rowDirection, col), Move.MoveType.NORMAL));
        }
        if (!isMoved) {
            if ((isWhite() && row == 6) || (!isWhite() && row == 1)) {
                if (GameController.getBoard().getPiece(row + (rowDirection * 2), col) == null) {
                    moves.add(new Move(new Position(row + rowDirection * 2, col), Move.MoveType.PAWN_SPRINT));
                }
            }
        }
        for (int direction : colDirections) {
            Piece targetPiece = GameController.getBoard().getPiece(row, col + direction);
            if (targetPiece != null) {
                if(targetPiece.isCatchableBy(this,false)) {
                    moves.add(new Move(new Position(row,col + direction), Move.MoveType.CAPTURE));
                } else if (targetPiece.isCatchableBy(this,true)) {
                    moves.add(new Move(new Position(row,col + direction), Move.MoveType.KING_CAPTURE));
                }
            }
        }
    }

    @Override
    public Pawn clone() {
        return (Pawn) super.clone();
    }
}
