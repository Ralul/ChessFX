package xyz.ralul.chessfx;

import xyz.ralul.chessfx.piece.Pawn;
import xyz.ralul.chessfx.piece.Piece;

public class Board {
    private Piece[][] board = new Piece[8][8];

    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return null; // Out-of-bounds
        }
        return board[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = getPiece(startRow, startCol);
        piece.setPos(endRow, endCol);
        setPiece(endRow, endCol, piece);
        setPiece(startRow, startCol, null);
    }
}
