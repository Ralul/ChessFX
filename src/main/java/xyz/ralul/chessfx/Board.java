package xyz.ralul.chessfx;

import xyz.ralul.chessfx.piece.*;

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
        if(piece != null) {
            piece.setRow(row);
            piece.setCol(col);
        }
    }

    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = getPiece(startRow, startCol);
        piece.setPos(endRow, endCol);
        setPiece(endRow, endCol, piece);
        setPiece(startRow, startCol, null);
    }

    public void loadFen(String fen) {
        String[] fenParts = fen.split("/");

        for (int row = 0; row < 8; row++) {
            int col = 0;
            for (int i = 0; i < fenParts[row].length(); i++) {
                char fenChar = fenParts[row].charAt(i);
                if (Character.isDigit(fenChar)) {
                    col += fenChar - '0' - 1;
                } else {
                    switch (fenChar) {
                        //Black Piece
                        case 'b':
                            setPiece(row, col, new Bishop(false));
                            break;
                        case 'k':
                            setPiece(row, col, new King(false));
                            break;
                        case 'n':
                            setPiece(row, col, new Knight(false));
                            break;
                        case 'p':
                            setPiece(row, col, new Pawn(false));
                            break;
                        case 'q':
                            setPiece(row, col, new Queen(false));
                            break;
                        case 'r':
                            setPiece(row, col, new Rook(false));
                            break;

                        //White Piece
                        case 'B':
                            setPiece(row, col, new Bishop(true));
                            break;
                        case 'K':
                            setPiece(row, col, new King(true));
                            break;
                        case 'N':
                            setPiece(row, col, new Knight(true));
                            break;
                        case 'P':
                            setPiece(row, col, new Pawn(true));
                            break;
                        case 'Q':
                            setPiece(row, col, new Queen(true));
                            break;
                        case 'R':
                            setPiece(row, col, new Rook(true));
                            break;
                    }
                }
                col++;
            }
        }
    }
}
