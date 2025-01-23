package xyz.ralul.chessfx;

import xyz.ralul.chessfx.piece.*;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;

public class Board implements Cloneable, Cleaner.Cleanable {
    private Piece[][] board = new Piece[8][8];

    public Piece getPiece(Position position) {
        if (position.getRow() < 0 || position.getRow() >= 8 || position.getCol() < 0 || position.getCol() >= 8) {
            return null; // Out-of-bounds
        }
        return board[position.getRow()][position.getCol()];
    }

    public Piece getPiece(int row, int col) {
        Position pos = new Position(row, col);
        return getPiece(pos);
    }

    public void setPiece(Position position, Piece piece) {
        board[position.getRow()][position.getCol()] = piece;
        if (piece != null) {
            piece.setPosition(new Position(position.getRow(), position.getCol()));
        }
    }

    public void movePiece(Position startPosition, Position endPosition) {
        setPiece(endPosition, getPiece(startPosition));
        setPiece(startPosition, null);
        getPiece(endPosition).setMoved(true);
    }

    public Position getKing(boolean isWhite) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != null) {
                    if (board[row][col].isWhite() == isWhite && board[row][col].getType() == ChessPieceType.KING) {
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
                if (board[row][col] != null) {
                    if (board[row][col].isWhite() == isWhite) {
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
                    Position setPosition = new Position(row,col);
                    switch (fenChar) {
                        //Black Piece
                        case 'b':
                            setPiece(setPosition, new Bishop(false));
                            break;
                        case 'k':
                            setPiece(setPosition, new King(false));
                            break;
                        case 'n':
                            setPiece(setPosition, new Knight(false));
                            break;
                        case 'p':
                            setPiece(setPosition, new Pawn(false));
                            break;
                        case 'q':
                            setPiece(setPosition, new Queen(false));
                            break;
                        case 'r':
                            setPiece(setPosition, new Rook(false));
                            break;

                        //White Piece
                        case 'B':
                            setPiece(setPosition, new Bishop(true));
                            break;
                        case 'K':
                            setPiece(setPosition, new King(true));
                            break;
                        case 'N':
                            setPiece(setPosition, new Knight(true));
                            break;
                        case 'P':
                            setPiece(setPosition, new Pawn(true));
                            break;
                        case 'Q':
                            setPiece(setPosition, new Queen(true));
                            break;
                        case 'R':
                            setPiece(setPosition, new Rook(true));
                            break;
                    }
                    getPiece(setPosition).setMoved(false);
                }
                col++;
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
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
        System.out.println();
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
