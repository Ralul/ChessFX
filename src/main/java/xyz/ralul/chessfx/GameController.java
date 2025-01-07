package xyz.ralul.chessfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        validMoves.clear();
        int[] posKing = board.getKing(playerIsWhite);

        List<Integer[]> ownPiecesPosition = new ArrayList<>();
        ownPiecesPosition = board.getAllPieces(playerIsWhite);

        Map<Integer[] , List<Integer[]>> ownPiecesPositionWithMoves= new HashMap<Integer[], List<Integer[]>>();

        for(int i = 0; i < ownPiecesPosition.size(); i++) {
            ownPiecesPositionWithMoves.put(ownPiecesPosition.get(i),board.getPiece(ownPiecesPosition.get(i)[0], ownPiecesPosition.get(i)[1]).getValidMoves(false));
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
