package xyz.ralul.chessfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    private static Board board;
    private static BoardView boardView;
    private Position startPosition;
    private Position endPosition;
    private static boolean validStartPosition = false;
    private static boolean validEndPosition = false;
    private static boolean playerIsWhite = true;
    private static boolean playerBlackIsCheck = false;
    private static boolean playerWhiteIsCheck = false;
    private static SearchValidMoves searchValidMoves;

    private Map<Position, List<Position>> validEnds = new HashMap<>();
    private List<Position> validStarts = new ArrayList<>();

    public GameController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
        boardView.setCellClickListener(this::hadelCellClick);
        updateView();
        searchValidMoves = new SearchValidMoves(board, validEnds, validStarts);
        searchValidMoves.updateMoves();
    }

    public static Board getBoard() {
        return board;
    }

    public static boolean isPlayerIsWhite() {
        return playerIsWhite;
    }

    private void hadelCellClick(Position position) {

        System.out.println("Piece clicked at: " + position.getRow() + ", " + position.getCol());

        if ((board.getPiece(position) != null) && board.getPiece(position).isWhite() == playerIsWhite) {
            startPosition = position;
            validStartPosition = true;
            System.out.println("GameLogic valid starting position");
        } else if (validStartPosition) {
            endPosition = position;
            validEndPosition = true;
            System.out.println("GameLogic valid ending position");
        }

        if (validEndPosition && validStartPosition) {
            if(searchValidMoves.isMoveValid(startPosition, endPosition)) {
                movePiece();
            }
        }
        Position kingPos = board.getKing(playerIsWhite);
        System.out.println("row: " + kingPos.getRow() + "| col: " + kingPos.getCol());
    }

    private void movePiece() {
        board.movePiece(startPosition, endPosition);
        validStartPosition = false;
        validEndPosition = false;
        playerIsWhite = !playerIsWhite;
        updateView();
        searchValidMoves.updateMoves();
    }

    private void updateView() {
        boardView.renderBoard(board);
    }
}
