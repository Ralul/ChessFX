package xyz.ralul.chessfx.piece;

import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite, ChessPieceType.ROOK, true);
    }

    @Override
    public List<Integer[]> getValidMoves() {
        List<Integer[]> moves = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Vertical and Horizontal Directions

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

                Piece targetPiece = GameLogic.getBoard().getPiece(currentRow, currentCol);
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
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Vertical and Horizontal Directions

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

                Piece targetPiece = GameLogic.getBoard().getPiece(currentRow, currentCol);
                if (targetPiece != null && targetPiece.isCatchbleBy(this)) {
                    catches.add(new Integer[]{currentRow, currentCol});
                }
            }
        }
        return catches;
    }
}
