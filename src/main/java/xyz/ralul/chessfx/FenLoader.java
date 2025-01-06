package xyz.ralul.chessfx;

import xyz.ralul.chessfx.piece.*;

public class FenLoader {
    public static Board loadFen(Board board, String fen) {
        String[] fenParts = fen.split("/");

        for (int row = 0; row < 8; row++) {
            int col = 0;
            for (int i = 0; i < fenParts[row].length(); i++) {
                char fenChar = fenParts[row].charAt(i);
                if (Character.isDigit(fenChar)) {
                    col += fenChar - '0' -1;
                }
                else {
                    switch (fenChar) {
                        //Black Piece
                        case 'b':
                            board.setPiece(row,col, new Bishop(false));
                            break;
                        case 'k':
                            board.setPiece(row,col, new King(false));
                            break;
                        case 'n':
                            board.setPiece(row,col, new Knight(false));
                            break;
                        case 'p':
                            board.setPiece(row,col, new Pawn(false));
                            break;
                        case 'q':
                            board.setPiece(row,col, new Queen(false));
                            break;
                        case 'r':
                            board.setPiece(row,col, new Rook(false));
                            break;

                        //White Piece
                        case 'B':
                            board.setPiece(row,col, new Bishop(true));
                            break;
                        case 'K':
                            board.setPiece(row,col, new King(true));
                            break;
                        case 'N':
                            board.setPiece(row,col, new Knight(true));
                            break;
                        case 'P':
                            board.setPiece(row,col, new Pawn(true));
                            break;
                        case 'Q':
                            board.setPiece(row,col, new Queen(true));
                            break;
                        case 'R':
                            board.setPiece(row,col, new Rook(true));
                            break;
                    }
                }
                col++;
            }
        }
        return board;
    }
}
