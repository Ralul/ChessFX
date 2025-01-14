package xyz.ralul.chessfx;

import xyz.ralul.chessfx.piece.*;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;

public class Board implements Cloneable, Cleaner.Cleanable {
    private Piece[][] board = new Piece[8][8];

    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return null; // Out-of-bounds
        }
        return board[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
        if (piece != null) {
            piece.setRow(row);
            piece.setCol(col);
        }
    }

    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = getPiece(startRow, startCol);
        piece.setPos(endRow, endCol);
        piece.setMoved(true);
        setPiece(endRow, endCol, piece);
        setPiece(startRow, startCol, null);

    }

    public Position getKing(boolean isWhite) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (getPiece(row, col) != null) {
                    if (getPiece(row, col).isWhite() == isWhite && getPiece(row, col).getType() == ChessPieceType.KING) {
                        return new Position(row, col);
                    }
                }
            }
        }
        return null;
    }

    public List<Position> getAllPieces(boolean isWhite) {
        List<Position> allPieces = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (getPiece(row, col) != null) {
                    if (getPiece(row, col).isWhite() == isWhite) {
                        allPieces.add(new Position(row, col));
                    }
                }
            }
        }
        return allPieces;
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
                    getPiece(row, col).setMoved(false);
                }
                col++;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPiece(row, col);
                if (piece == null) {
                    System.out.print("*");
                } else if (piece.getType() == ChessPieceType.PAWN) {
                    if(piece.isWhite()) {
                        System.out.print("p");
                    }else {
                        System.out.print("P");
                    }
                } else if (piece.getType() == ChessPieceType.KING) {
                    if(piece.isWhite()) {
                        System.out.print("k");
                    } else {
                        System.out.print("K");
                    }
                } else if (piece.getType() == ChessPieceType.QUEEN) {
                    if(piece.isWhite()) {
                        System.out.print("q");
                    }else {
                        System.out.print("Q");
                    }
                } else if (piece.getType() == ChessPieceType.BISHOP) {
                    if(piece.isWhite()) {
                        System.out.print("b");
                    } else {
                        System.out.print("B");
                    }
                } else if (piece.getType() == ChessPieceType.KNIGHT) {
                    if(piece.isWhite()) {
                        System.out.print("n");
                    } else {
                        System.out.print("N");
                    }
                } else if (piece.getType() == ChessPieceType.ROOK) {
                    if(piece.isWhite()) {
                        System.out.print("r");
                    } else {
                        System.out.print("R");
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public Board clone() {
        try {
            Board boardClone = (Board) super.clone();
            boardClone.board = new Piece[8][8];
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (this.board[row][col] != null) {
                        boardClone.board[row][col] = this.board[row][col].clone();
                    }
                }
            }
            return boardClone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public void clean() {
        this.board = new Piece[8][8];
    }
}
