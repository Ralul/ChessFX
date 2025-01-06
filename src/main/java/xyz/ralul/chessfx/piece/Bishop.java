package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameController;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite, ChessPieceType.BISHOP, true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        List<Integer[]> moves = new ArrayList<>();
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // Diagonal directions

        int startRow = getRow();
        int startCol = getCol();

        for (int[] direction : directions) {
            int currentRow = startRow;
            int currentCol = startCol;

            while (true) {
                currentRow += direction[0];
                currentCol += direction[1];

                if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                    break;
                }

                Piece targetPiece = GameController.getBoard().getPiece(currentRow, currentCol);
                if (targetPiece == null) {
                    moves.add(new Integer[]{currentRow, currentCol});
                } else {
                    break;
                }
            }
        }
        return moves;
    }

    @Override
    public List<Integer[]> getValidCatches() {
        List<Integer[]> catches = new ArrayList<>();
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // Diagonal directions

        int startRow = getRow();
        int startCol = getCol();

        for (int[] direction : directions) {
            int currentRow = startRow;
            int currentCol = startCol;

            boolean valid = true;
            while (valid) {
                currentRow += direction[0];
                currentCol += direction[1];

                if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                    valid = false;
                }

                Piece targetPiece = GameController.getBoard().getPiece(currentRow, currentCol);
                if (targetPiece != null && targetPiece.isCatchbleBy(this)) {
                    catches.add(new Integer[]{currentRow, currentCol});
                }
            }
        }
        return catches;
    }
}
