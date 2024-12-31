package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite, ChessPieceType.PAWN, true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        List<Integer[]> moves = new ArrayList<>();
        int direction = this.isWhite() ? 1 : -1;

        int startCol = this.getCol();
        int startRow = this.getRow();

        moves.add(new Integer[]{startRow + direction, startCol});

        if ((isWhite() && startRow == 1) || (!isWhite() && startRow == 6)) {
            moves.add(new Integer[]{startRow + (direction * 2), startCol});
        }

        return moves;
    }
}
