package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameController;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private int[][] directions = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

    public Knight(boolean isWhite) {
        super(isWhite, ChessPieceType.KNIGHT, true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        List<Integer[]> moves = new ArrayList<>();

        int startRow = getRow();
        int startCol = getCol();

        for (int[] direction : directions) {
            int currentRow = startRow;
            int currentCol = startCol;

            currentRow += direction[0];
            currentCol += direction[1];

            if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                continue;
            }

            Piece targetPiece = GameController.getBoard().getPiece(currentRow, currentCol);
            if (targetPiece == null) {
                moves.add(new Integer[]{currentRow, currentCol});
            }
        }
        return moves;
    }

    @Override
    public List<Integer[]> getValidCatches() {
        List<Integer[]> catches = new ArrayList<>();

        int startRow = getRow();
        int startCol = getCol();

        for (int[] direction : directions) {
            int currentRow = startRow;
            int currentCol = startCol;

            currentRow += direction[0];
            currentCol += direction[1];

            if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                continue;
            }

            Piece targetPiece = GameController.getBoard().getPiece(currentRow, currentCol);
            if (targetPiece != null && targetPiece.isCatchbleBy(this)) {
                catches.add(new Integer[]{currentRow, currentCol});
            }
        }
        return catches;
    }
}
