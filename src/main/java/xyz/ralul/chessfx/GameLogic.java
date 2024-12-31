package xyz.ralul.chessfx;

import xyz.ralul.chessfx.piece.Pawn;
import xyz.ralul.chessfx.piece.Piece;

public class GameLogic {
    private static Board board;

    private static int startRow, startCol;
    private static int endRow, endCol;
    private static boolean validStartPosition, validEndPosition;
    private static boolean playerIsWhite = true;

    public static void initializeGame() {
        board = new Board();

        board.setPiece(3, 3, new Pawn(true,3,3));
        board.setPiece(2, 4, new Pawn(false,2,4));

        //Todo add a fen loader

    }

    public static void handleCellClick(int row, int col) {
        System.out.println("GameLogic received click at: (" + row + ", " + col + ")");

        if((getBoard().getPiece(row, col) != null) && getBoard().getPiece(row, col).isWhite() == playerIsWhite) {
            startRow = row;
            startCol = col;
            validStartPosition = true;
            System.out.println("GameLogic valid starting position");
        }
        else if (validStartPosition) {
            endRow = row;
            endCol = col;
            validEndPosition = true;
        }

        if (validEndPosition && validStartPosition) {
            if (getBoard().getPiece(startRow, startCol).isValidMove(endRow, endCol)) {
                System.out.println("GameLogic valid ending position");
                movePiece();
            }
            validEndPosition = false;
        }
    }

    public static Board getBoard() {
        return board;
    }

    private static void movePiece() {
        board.movePiece(startRow, startCol, endRow, endCol);

        // Switch player after move
        validStartPosition = false;
        validEndPosition = false;
        playerIsWhite = !playerIsWhite;
    }



}