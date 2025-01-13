package xyz.ralul.chessfx;

import java.util.*;

public class GameController {

    public static Board getBoard() {
        return board;
    }

    private static Board board;
    private static BoardView boardView;

    private static int startRow, startCol;
    private static int endRow, endCol;
    private static boolean validStartPosition  = false;
    private static boolean validEndPosition = false;
    private static boolean playerIsWhite = true;

    private static Map<Integer[],List<Integer[]>> validMoves = new HashMap<Integer[], List<Integer[]>>();
    private static boolean playerBlackIsCheck = false;
    private static boolean playerWhiteIsCheck = false;

    public GameController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;

        boardView.setCellClickListener(this::hadelCellClick);
        updateView();
    }

    public static void updateValidMoves() {

        /*
        How to find all valid Moves:
        1. Find All own pieces
        2. Calculate all possible moves without considering their legality
        3. Made All moves on a deep Copy of the board
        4. Calculate all possible catches for the other player
        5. If a catch matches with the position of the king the move is not legal
        */

        validMoves.clear();
        int[] posKing = board.getKing(playerIsWhite);
        List<Integer[]> ownPiecesPosition = ownPiecesPosition = board.getAllPieces(playerIsWhite);
        Map<Integer[] , List<Integer[]>> ownPiecesPositionWithMoves= new HashMap<Integer[], List<Integer[]>>();

        for(int i = 0; i < ownPiecesPosition.size(); i++) {
            ownPiecesPositionWithMoves.put(ownPiecesPosition.get(i),board.getPiece(ownPiecesPosition.get(i)[0], ownPiecesPosition.get(i)[1]).getValidMoves(false));
        }
        for(int i = 0; i < ownPiecesPositionWithMoves.size(); i++) {
            System.out.println(ownPiecesPositionWithMoves.get(i));
            for(int j = 0; j < ownPiecesPositionWithMoves.get(i).size(); j++) {
                System.out.println(Arrays.toString(ownPiecesPositionWithMoves.get(i).get(j)));
                Board tempBoard = board.clone();

                int startRow = ownPiecesPosition.get(j)[0];
                int startCol = ownPiecesPosition.get(j)[1];
                int endRow = ownPiecesPositionWithMoves.get(i).get(j)[0];
                int endCol = ownPiecesPositionWithMoves.get(i).get(j)[1];

                tempBoard.movePiece(startRow, startCol, endRow, endCol);

                List<Integer[]> otherPieces = tempBoard.getAllPieces(!playerIsWhite);

                for (int k = 0; k < otherPieces.size(); k++) {
                    List<Integer[]> posibelCatches = tempBoard.getPiece(otherPieces.get(k)[0], otherPieces.get(k)[1]).getValidMoves(true);
                    for(Integer[] posibelCatch : posibelCatches) {
                        if(posKing[0] == posibelCatch[0] && posKing[1] == posibelCatch[1]) {
                            ownPiecesPositionWithMoves.get(i).remove(j);
                            break;
                        }
                    }
                }
                
            }
        }

        //Move all pices and show if an enemy piec can capture the own king
        //But first create an copy of board to perform the move

        validMoves = ownPiecesPositionWithMoves;
    }

    private void hadelCellClick(int row, int col) {
        System.out.println("Piece clicked at: " + row + ", " + col);

        if ((board.getPiece(row, col) != null) && board.getPiece(row, col).isWhite() == playerIsWhite) {
            startRow = row;
            startCol = col;
            validStartPosition = true;
            System.out.println("GameLogic valid starting position");
        }
        else if (validStartPosition) {
            endRow = row;
            endCol = col;
            validEndPosition = true;
            System.out.println("GameLogic valid ending position");
        }

        if (validEndPosition && validStartPosition) {
            movePiece();
        }
        int[] kingPos = board.getKing(playerIsWhite);
        System.out.println("row: "+ kingPos[0]+ "| col: " + kingPos[1]);
    }

    private void movePiece(){
        if (board.getPiece(startRow, startCol).isValidMove(endRow, endCol,false)) {
            System.out.println("GameLogic valid ending position");
            board.movePiece(startRow, startCol, endRow, endCol);
            validStartPosition = false;
            validEndPosition = false;
            playerIsWhite = !playerIsWhite;

            updateValidMoves();
            updateView();
        }
    }

    private void updateView() {
        boardView.renderBoard(board);
    }
}
